package com.kbzgame.physics.elements;

import com.kbzgame.physics.shape.Shape;

public class Area extends PhysicsElements {
	private Shape shape;
	public Area(Shape shape){
		this.shape = shape;
	}
	public Shape getShape(){
		return shape;
	}
}
