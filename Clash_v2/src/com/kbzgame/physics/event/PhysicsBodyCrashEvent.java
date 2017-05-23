package com.kbzgame.physics.event;

import com.kbzgame.physics.elements.Body;

public class PhysicsBodyCrashEvent extends PhysicsEvent{
	private Body bodyA;
	private Body bodyB;
	public PhysicsBodyCrashEvent(Body bodyA,Body bodyB){
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
