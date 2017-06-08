package com.kbzgame.physics.elements;

import com.kbzgame.physics.shape.Shape;

public class Area extends PhysicsElements {
	private Shape shape;
	private PhysicsWorld world;
	public Area(Shape shape){
		this.shape = shape;
		
	}
	public Shape getShape(){
		return shape;
	}
	@Override
	public void addToWorld(PhysicsWorld world) {
		// TODO Auto-generated method stub
		this.world = world;
		this.world.addArea(this);
	}
	@Override
	public void quitWorld() {
		// TODO Auto-generated method stub
		world.removeArea(this);
		world = null;
	}
}
