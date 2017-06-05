package com.kbzgame.physics.elements;

import java.util.ArrayList;
import java.util.List;

import com.kbzgame.physics.event.PhysicsBodyCrashEvent;
import com.kbzgame.physics.shape.ShapeCrashShapeDetection;
import com.kbzgame.physics.shape.TestResult;
import com.kbzgame.physics.shape.Vector;

public class BodyCrashWithBodyDetection {
	private ShapeCrashShapeDetection shapeDetection = ShapeCrashShapeDetection.create();
	public synchronized void test(List<Body> bodyList,Contact contact){
		//get a array from a list
//		int bodySize = bodyList.size();
//		Body[] bodys = new Body[bodySize];
//		bodyList.toArray(bodys);
//		for(int i = 0;i<bodySize-1;i++){
//			Body bodyA = bodys[i];
//			for(int j = i+1;j<bodySize;j++){
//				Body bodyB = bodys[j];
//				TestResult result = shapeDetection.shapeCrashWithShape(bodyA.getShape(),bodyB.getShape());
//				if(result.isHappened()){
//					//do some thing
//					Vector backVector = result.getBackVector();
//					if(bodyA.isMovable()){
//						bodyA.changePositionBy(backVector.getComponentX(),backVector.getComponentY());
//					}
//					else if(bodyB.isMovable()){
//						bodyB.changePositionBy(-backVector.getComponentX(),-backVector.getComponentY());
//					}
//					PhysicsBodyCrashEvent event = new PhysicsBodyCrashEvent(bodyA,bodyB);
//					contact.sendPhysicsEvent(event);
//				}
//			}
//		}
		List<Body> returnObjects=new ArrayList<Body>();
		for (int i = 0; i < bodyList.size(); i++) {
			  returnObjects.clear();
			  returnObjects= PhysicsWorld.getQuad().retrieve( bodyList.get(i));
			  returnObjects.remove(bodyList.get(i));
			  for (int j = 0; j < returnObjects.size(); j++) {
				  TestResult result = shapeDetection.shapeCrashWithShape(bodyList.get(i).getShape(),returnObjects.get(j).getShape());
				  if(result.isHappened()){
						//do some thing
					  Vector backVector = result.getBackVector();
					  if(bodyList.get(i).isMovable()){
							bodyList.get(i).changePositionBy(backVector.getComponentX(),backVector.getComponentY());
					  	}
					
						PhysicsBodyCrashEvent event = new PhysicsBodyCrashEvent(bodyList.get(i),returnObjects.get(j));
						contact.sendPhysicsEvent(event);
				  }
			  }
		}
	}
	public void setShapeCrashShapeDetection(ShapeCrashShapeDetection shapeDetection){
		this.shapeDetection = shapeDetection;
	}
}
