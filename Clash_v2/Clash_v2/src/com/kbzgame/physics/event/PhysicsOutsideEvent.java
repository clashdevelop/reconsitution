package com.kbzgame.physics.event;

import com.kbzgame.physics.elements.Body;

public class PhysicsOutsideEvent extends PhysicsEvent{
	public PhysicsOutsideEvent(Body body){
		setElement(body);
	}
}
