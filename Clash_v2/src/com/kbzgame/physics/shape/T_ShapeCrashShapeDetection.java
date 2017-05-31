package com.kbzgame.physics.shape;

public class T_ShapeCrashShapeDetection extends ShapeCrashShapeDetection{
	
	private Vector backVector=new Vector(0,0);
	public boolean shapeCrashWithShape(Shape shapeA,Shape shapeB)
	{	
		if((int)(Math.random()*10)%2==0){
			backVector=new Vector(1,1);
			return true;
		}
		else return false;
	}
	public Vector getBackVector(Shape shapeA,Shape shapeB){
		return backVector;
	}

}
