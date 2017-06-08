package com.kbzgame.physics.elements;

import java.util.List;

import com.kbzgame.physics.event.PhysicsBodyCrashEvent;
import com.kbzgame.physics.shape.ShapeCrashShapeDetection;
import com.kbzgame.physics.shape.TestResult;
import com.kbzgame.physics.shape.Vector;

public class BodyCrashWithBodyDetection {
	private ShapeCrashShapeDetection shapeDetection = ShapeCrashShapeDetection.create();
	public void test(List<Body> bodyList,Contact contact){
		//get a array from a list
		int bodySize = bodyList.size();
		Body[] bodys = new Body[bodySize];
		bodyList.toArray(bodys);
		for(int i = 0;i<bodySize-1;i++){
			Body bodyA = bodys[i];
			for(int j = i+1;j<bodySize;j++){
				Body bodyB = bodys[j];
				TestResult result = shapeDetection.shapeCrashWithShape(bodyA.getShape(),bodyB.getShape());
				if(result.isHappened()){
					//do some thing
					Vector backVector = result.getBackVector();
					if(bodyA.isMovable()){
						bodyA.changePositionBy(backVector.getComponentX(),backVector.getComponentY());
					}
					else if(bodyB.isMovable()){
						bodyB.changePositionBy(-backVector.getComponentX(),-backVector.getComponentY());
					}
					PhysicsBodyCrashEvent event = new PhysicsBodyCrashEvent(bodyA,bodyB);
					contact.sendPhysicsEvent(event);
				}
			}
		}
	}
	public void setShapeCrashShapeDetection(ShapeCrashShapeDetection shapeDetection){
		this.shapeDetection = shapeDetection;
	}
}
