package com.kbzgame.physics.event;

import com.kbzgame.physics.elements.Area;
import com.kbzgame.physics.elements.Body;

public class PhysicsInAreaEvent extends PhysicsEvent{
	private Body body;
	private Area area;
	
	public PhysicsInAreaEvent(Body body,Area area){
		this.body = body;
		this.area = area;
	}
	public Body getBody(){
		return body;
	}
	public Area getArea(){
		return area;
	}
	
}
