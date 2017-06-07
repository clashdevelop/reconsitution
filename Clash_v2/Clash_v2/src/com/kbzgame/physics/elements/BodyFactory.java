package com.kbzgame.physics.elements;

import com.kbzgame.physics.shape.Shape;
import com.kbzgame.physics.shape.ShapeFactory;

public class BodyFactory {
	public static Body createBody(double x,double y,double r){
		Shape shape = ShapeFactory.createCircle(x, y, r);
		return new Body(shape);
	}
}
