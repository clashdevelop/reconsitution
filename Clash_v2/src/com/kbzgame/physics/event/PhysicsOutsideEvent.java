package com.kbzgame.physics.event;

import com.kbzgame.physics.elements.Body;

public class PhysicsOutsideEvent {
	private Body body;
	public PhysicsOutsideEvent(Body body){
		this.body = body;
	}
	public Body getBody(){
		return body;
	}
}
