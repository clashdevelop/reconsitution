package com.kbzgame.physics.event;

import com.kbzgame.physics.elements.Body;

public class PhysicsBodyCrashEvent extends PhysicsEvent{
	public PhysicsBodyCrashEvent(Body bodyA,Body bodyB){
		setElementA(bodyA);
		setElementB(bodyB);
	}
}
