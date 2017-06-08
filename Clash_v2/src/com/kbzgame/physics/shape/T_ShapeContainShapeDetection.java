package com.kbzgame.physics.shape;

public class T_ShapeContainShapeDetection extends ShapeContainShapeDetection{
	
	public boolean shapeContainShape(Shape borderShape,Shape shape){
		if((int)(Math.random()*10)%2==0){
			return true;
		}
		else return false;
	}
}
