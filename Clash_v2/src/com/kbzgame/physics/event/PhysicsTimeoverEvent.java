package com.kbzgame.physics.event;

import com.kbzgame.physics.elements.Timer;

public class PhysicsTimeoverEvent extends PhysicsEvent{
	public PhysicsTimeoverEvent(Timer timer){
		setElement(timer);
	}
}
