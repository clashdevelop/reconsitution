package com.kbzgame.physics.event;

import com.kbzgame.physics.elements.Body;

public class PhysicsCrashEvent {
	private Body bodyA;
	private Body bodyB;
	public PhysicsCrashEvent(Body bodyA,Body bodyB){
		this.bodyA = bodyA;
		this.bodyB = bodyB;
	}
	public Body getBodyA(){
		return bodyA;
	}
	public Body getBodyB(){
		return bodyB;
	}
}
