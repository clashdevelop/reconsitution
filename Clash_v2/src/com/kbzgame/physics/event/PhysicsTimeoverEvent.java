package com.kbzgame.physics.event;

import com.kbzgame.physics.elements.Timer;

public class PhysicsTimeoverEvent extends PhysicsEvent{
	private Timer timer;
	public PhysicsTimeoverEvent(Timer timer){
		this.timer = timer;
	}
	public Timer getTimer(){
		return timer;
	}
}
