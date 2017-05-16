package com.kbzgame.physics.elements;

import com.kbzgame.physics.shape.Shape;

public class Body {
	private Shape shape;
	public Body(Shape shape){
		this.shape = shape;
	}
	public Shape getShape(){
		return shape;
	}
}
