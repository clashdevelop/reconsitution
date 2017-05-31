package com.kbzgame.physics.elements;

import java.util.List;

import com.kbzgame.physics.event.PhysicsBodyCrashEvent;
import com.kbzgame.physics.shape.ShapeCrashShapeDetection;
import com.kbzgame.physics.shape.Vector;

public class BodyCrashWithBodyDetection {
	private Contact contact;
	private ShapeCrashShapeDetection shapeDetection = new ShapeCrashShapeDetection();
	public BodyCrashWithBodyDetection(Contact contact){
		this.contact = contact;
	}
	public void test(List<Body> bodyList){
		int bodySize = bodyList.size();
		Body[] bodys = new Body[bodySize];
		bodyList.toArray(bodys);
		for(int i = 0;i<bodySize-1;i++){
			Body bodyA = bodys[i];
			for(int j = i+1;j<bodySize;j++){
				Body bodyB = bodys[j];
				if(shapeDetection.shapeCrashWithShape(bodyA.getShape(),bodyB.getShape())){
					//do some thing
					Vector backVector = shapeDetection.getBackVector(bodyA.getShape(), bodyB.getShape());
					bodyA.changePositionBy(backVector.getComponentX(),backVector.getComponentY());
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
