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
		//����ƽ�������
		Quadtree bodyTree = new Quadtree(0,borderShape.getBorder());
		bodyTree.insert(bodyList);
		//test body crash with body and outside
		Iterator<Body> bodyIter = bodyList.iterator();
		while(bodyIter.hasNext()){
			Body bodyA = bodyIter.next();
			//test body outside �������
			TestResult bordeCotainResult = containDetection.shapeContainShapeWithBack(borderShape, bodyA.getShape());
			if(!bordeCotainResult.isHappened()){
				Vector backVector = bordeCotainResult.getBackVector();
				//����������
				bodyA.changePositionBy(backVector.getComponentX()+0.1,backVector.getComponentY()+0.1);
				//�߽���ײ����
				//double edgeAgle = backVector.getAngle()-Math.PI/2;//���㷨����Ϊ��������pI/2
				//double afterAngle = 2*edgeAgle-bodyA.getV().getAngle();//�����=�ߵĽǶ�*2-�ٶȽǶ�
				//double afterSize = bodyA.getV().getSize()*bodyA.getk_factor();//�����Ĵ�С
				//Vector afterV  = new Vector(afterSize,afterAngle);
				//bodyA.setV(afterV);
				//send outside event
				PhysicsOutsideEvent event = new PhysicsOutsideEvent(bodyA);
				contact.sendPhysicsEvent(event);
			}
			//test body crash
			//ͨ���Ĳ����õ���body��ͬһ��ײ�����body
			List<Body> testBodys = bodyTree.retrieve(bodyA.getShape());
			testBodys.remove(bodyA);//ȥ������
			Iterator<Body> testBodyIter = testBodys.iterator();
			while(testBodyIter.hasNext()){
				Body bodyB = testBodyIter.next();
				TestResult crashResult = crashDetection.shapeCrashWithShape(bodyA.getShape(),bodyB.getShape());
				if(crashResult.isHappened()){
					Vector backVector = crashResult.getBackVector();
					//�������ص�
					if(bodyB.isMovable()){
						bodyB.changePositionBy(-backVector.getComponentX()-0.1,-backVector.getComponentY()-0.1);
						if(!containDetection.shapeContainShape(borderShape, bodyB.getShape())){
							//�ƶ�����磬B����
							bodyB.changePositionBy(backVector.getComponentX()+0.1,backVector.getComponentY()+0.1);
							//A�ƶ������ƶ���a��b��������������b��a�������ظ����
							if(bodyA.isMovable())
								bodyA.changePositionBy(backVector.getComponentX()+0.1,backVector.getComponentY()+0.1);	
						}
					}else if(bodyA.isMovable()){
						bodyA.changePositionBy(backVector.getComponentX()+0.1,backVector.getComponentY()+0.1);
					}
					//������ײ����
					/*if(!bodyA.isMovable() && bodyB.isMovable()){
						double edgeAgle = backVector.getAngle()-Math.PI/2;//���㷨����Ϊ��������pI/2
						double afterAngle = 2*edgeAgle-bodyB.getV().getAngle();//�����=�ߵĽǶ�*2-�ٶȽǶ�
						double afterSize = bodyB.getV().getSize()*bodyB.getk_factor();//�����Ĵ�С
						Vector afterV  = new Vector(afterSize,afterAngle);
						bodyA.setV(afterV);
					}
					if(!bodyB.isMovable() && bodyA.isMovable()){
						double edgeAgle = backVector.getAngle()-Math.PI/2;//���㷨����Ϊ��������pI/2
						double afterAngle = 2*edgeAgle-bodyA.getV().getAngle();//�����=�ߵĽǶ�*2-�ٶȽǶ�
						double afterSize = bodyA.getV().getSize()*bodyA.getk_factor();//�����Ĵ�С
						Vector afterV  = new Vector(afterSize,afterAngle);
						bodyA.setV(afterV);
					}*/
					if(bodyA.isMovable() && bodyB.isMovable()){
						Vector crashDirectionAtoB = new Vector(1,backVector.getAngle()+Math.PI);
						Vector va = bodyA.getV();
						//��A���ٶȽ�������ϵת������ײ������Ϊx��������
						Vector convertVa = Vector.convertVectorToReferenceFrame(va,crashDirectionAtoB);
						//�õ�A��ײ���������Ч�ٶȴ�С����ֵ˵����Bû��ײ������
						double va_crashComponent = convertVa.getComponentX();
						if(va_crashComponent>0){
							//A��ײ�����Ĵ�С
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
