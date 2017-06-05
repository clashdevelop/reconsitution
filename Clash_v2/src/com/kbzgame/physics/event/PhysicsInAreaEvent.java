package com.kbzgame.physics.event;

import com.kbzgame.physics.elements.Area;
import com.kbzgame.physics.elements.Body;

public class PhysicsInAreaEvent extends PhysicsEvent{
	
	public PhysicsInAreaEvent(Body body,Area area){
		setElementA(body);
		setElementB(area);
	}
}
