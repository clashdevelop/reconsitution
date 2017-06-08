package com.kbzgame.physics.elements;

import java.util.Iterator;
import java.util.List;

import com.kbzgame.physics.event.PhysicsBodyCrashEvent;
import com.kbzgame.physics.event.PhysicsInAreaEvent;
import com.kbzgame.physics.event.PhysicsOutsideEvent;
import com.kbzgame.physics.shape.Shape;
import com.kbzgame.physics.shape.ShapeContainShapeDetection;
import com.kbzgame.physics.shape.ShapeCrashShapeDetection;
import com.kbzgame.physics.shape.TestResult;
import com.kbzgame.physics.shape.Vector;
import com.kbzgame.quadtree.Quadtree;

public class BodyMoveDetection {
	private ShapeContainShapeDetection containDetection= new ShapeContainShapeDetection();
	private ShapeCrashShapeDetection crashDetection = ShapeCrashShapeDetection.create();
	public void test(Shape borderShape,List<Body> bodyList,List<Area> areaList,Contact contact){
		//建立平衡四杈树
		Quadtree bodyTree = new Quadtree(0,borderShape.getBorder());
		bodyTree.insert(bodyList);
		//test body crash with body and outside
		Iterator<Body> bodyIter = bodyList.iterator();
		while(bodyIter.hasNext()){
			Body bodyA = bodyIter.next();
			//test body outside 出界测试
			TestResult bordeCotainResult = containDetection.shapeContainShapeWithBack(borderShape, bodyA.getShape());
			if(!bordeCotainResult.isHappened()){
				Vector backVector = bordeCotainResult.getBackVector();
				//调整不出界
				bodyA.changePositionBy(backVector.getComponentX()+0.1,backVector.getComponentY()+0.1);
				//边界碰撞反弹
				//double edgeAgle = backVector.getAngle()-Math.PI/2;//计算法向量为边向量加pI/2
				//double afterAngle = 2*edgeAgle-bodyA.getV().getAngle();//反射角=边的角度*2-速度角度
				//double afterSize = bodyA.getV().getSize()*bodyA.getk_factor();//反射后的大小
				//Vector afterV  = new Vector(afterSize,afterAngle);
				//bodyA.setV(afterV);
				//send outside event
				PhysicsOutsideEvent event = new PhysicsOutsideEvent(bodyA);
				contact.sendPhysicsEvent(event);
			}
			//test body crash
			//通过四岔树得到与body在同一碰撞区域的body
			List<Body> testBodys = bodyTree.retrieve(bodyA.getShape());
			testBodys.remove(bodyA);//去除本身
			Iterator<Body> testBodyIter = testBodys.iterator();
			while(testBodyIter.hasNext()){
				Body bodyB = testBodyIter.next();
				TestResult crashResult = crashDetection.shapeCrashWithShape(bodyA.getShape(),bodyB.getShape());
				if(crashResult.isHappened()){
					Vector backVector = crashResult.getBackVector();
					//调整不重叠
					if(bodyB.isMovable()){
						bodyB.changePositionBy(-backVector.getComponentX()-0.1,-backVector.getComponentY()-0.1);
						if(!containDetection.shapeContainShape(borderShape, bodyB.getShape())){
							//移动后出界，B回退
							bodyB.changePositionBy(backVector.getComponentX()+0.1,backVector.getComponentY()+0.1);
							//A移动，并移动到a和b不会相碰，避免b和a检测出现重复结果
							if(bodyA.isMovable())
								bodyA.changePositionBy(backVector.getComponentX()+0.1,backVector.getComponentY()+0.1);	
						}
					}else if(bodyA.isMovable()){
						bodyA.changePositionBy(backVector.getComponentX()+0.1,backVector.getComponentY()+0.1);
					}
					//增加碰撞反弹
					/*if(!bodyA.isMovable() && bodyB.isMovable()){
						double edgeAgle = backVector.getAngle()-Math.PI/2;//计算法向量为边向量加pI/2
						double afterAngle = 2*edgeAgle-bodyB.getV().getAngle();//反射角=边的角度*2-速度角度
						double afterSize = bodyB.getV().getSize()*bodyB.getk_factor();//反射后的大小
						Vector afterV  = new Vector(afterSize,afterAngle);
						bodyA.setV(afterV);
					}
					if(!bodyB.isMovable() && bodyA.isMovable()){
						double edgeAgle = backVector.getAngle()-Math.PI/2;//计算法向量为边向量加pI/2
						double afterAngle = 2*edgeAgle-bodyA.getV().getAngle();//反射角=边的角度*2-速度角度
						double afterSize = bodyA.getV().getSize()*bodyA.getk_factor();//反射后的大小
						Vector afterV  = new Vector(afterSize,afterAngle);
						bodyA.setV(afterV);
					}*/
					if(bodyA.isMovable() && bodyB.isMovable()){
						Vector crashDirectionAtoB = new Vector(1,backVector.getAngle()+Math.PI);
						Vector va = bodyA.getV();
						//将A的速度进行坐标系转换，以撞击方向为x轴正方向
						Vector convertVa = Vector.convertVectorToReferenceFrame(va,crashDirectionAtoB);
						//得到A在撞击方向的有效速度大小，负值说明对B没有撞击作用
						double va_crashComponent = convertVa.getComponentX();
						if(va_crashComponent>0){
							//A的撞击力的大小
							double crashfSize = bodyA.getM()*va_crashComponent*0.05;
							Vector crashFa = new Vector(crashfSize,crashDirectionAtoB.getAngle());
							bodyB.addF(crashFa);
						}
						
						Vector crashDirectionBtoA = backVector;
						Vector vb = bodyB.getV();
						Vector convertVb = Vector.convertVectorToReferenceFrame(vb,crashDirectionBtoA);
						double vb_crashComponent = convertVb.getComponentX();
						if(vb_crashComponent>0){
							double crashfSize = bodyB.getM()*vb_crashComponent*0.05;
							Vector crashFb = new Vector(crashfSize,crashDirectionBtoA.getAngle());
							bodyA.addF(crashFb);
						}
					}
					//event send
					PhysicsBodyCrashEvent event = new PhysicsBodyCrashEvent(bodyA,bodyB);
					contact.sendPhysicsEvent(event);
				}
			}

		}
		//test body with area
		Iterator<Area> areaIter = areaList.iterator();
		while(areaIter.hasNext()){
			Area area = areaIter.next();
			List<Body> testBodys = bodyTree.retrieve(area.getShape());
			Iterator<Body> testBodyIter = testBodys.iterator();
			while(testBodyIter.hasNext()){
				Body body = testBodyIter.next();
				boolean happened = containDetection.shapeContainShape(area.getShape(),body.getShape());
				if(happened){
					//send event
					PhysicsInAreaEvent event = new PhysicsInAreaEvent(body, area);
					contact.sendPhysicsEvent(event);
				}
			}

		}
	}
}
