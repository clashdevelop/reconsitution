package com.kbzgame.physics.elements;

import java.util.List;

import com.kbzgame.physics.shape.ShapeCrashShapeDetection;

public class BodyCrashWithBodyDetection {
	private ShapeCrashShapeDetection shapeDetection = new ShapeCrashShapeDetection();
	public void test(List<Body> bodyList){
		int bodySize = bodyList.size();
		Body[] bodys = (Body[])bodyList.toArray();
		for(int i = 0;i<bodySize-1;i++){
			Body bodyA = bodys[i];
			for(int j = 0;j<bodySize;j++){
				Body bodyB = bodys[j];
				if(shapeDetection.shapeCrashWithShape(bodyA.getShape(),bodyB.getShape())){
					//do some thing
				}
			}
		}
	}
	public void setShapeCrashShapeDetection(ShapeCrashShapeDetection shapeDetection){
		this.shapeDetection = shapeDetection;
	}
}
