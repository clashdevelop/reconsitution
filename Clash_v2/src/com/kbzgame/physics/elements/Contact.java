package com.kbzgame.physics.elements;

import com.kbzgame.physics.event.EventDispatcher;
import com.kbzgame.physics.event.PhysicsBodyCrashEvent;

import com.kbzgame.physics.event.PhysicsInAreaEvent;
import com.kbzgame.physics.event.PhysicsOutsideEvent;
import com.kbzgame.physics.event.PhysicsTimeoverEvent;

public class Contact {
	//list假设ruler不会出现两次相同的ruler对象
	//可以考虑使用set来存储ruler
	private EventDispatcher dispatcher=null;
	public void sendPhysicsEvent(PhysicsBodyCrashEvent physicsBodyCrashEvent){
		dispatcher.addEvent(physicsBodyCrashEvent);	
	}
	public void sendPhysicsEvent(PhysicsInAreaEvent physicsInAreaEvent){
		dispatcher.addEvent(physicsInAreaEvent);
	}
	public void sendPhysicsEvent(PhysicsOutsideEvent physicsOutsideEvent){
		dispatcher.addEvent(physicsOutsideEvent);
	}
	public void sendPhysicsEvent(PhysicsTimeoverEvent physicsTimeoverEvent){
		//just send time over event
		dispatcher.addEvent(physicsTimeoverEvent);
	}
	
	public void setEventDispatcher(EventDispatcher dispatcher){
		this.dispatcher = dispatcher;
	}
	public EventDispatcher getEventDispatcher(){
		return dispatcher;
	}
}
