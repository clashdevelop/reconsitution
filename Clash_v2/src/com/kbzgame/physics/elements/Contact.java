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
		if(dispatcher!=null)
			dispatcher.notify(physicsBodyCrashEvent);
		//System.out.println("physicsBodyCrashEvent");
	}
	public void sendPhysicsEvent(PhysicsInAreaEvent physicsInAreaEvent){
		if(dispatcher!=null)
			dispatcher.notify(physicsInAreaEvent);
		//System.out.println("physicsInAreaEvent");
	}
	public void sendPhysicsEvent(PhysicsOutsideEvent physicsOutsideEvent){
		if(dispatcher!=null)
			dispatcher.notify(physicsOutsideEvent);
		//System.out.println("physicsOutsideEvent");
	}
	public void sendPhysicsEvent(PhysicsTimeoverEvent physicsTimeoverEvent){
		//just send time over event
		if(dispatcher!=null)
			dispatcher.notify(physicsTimeoverEvent);
		//System.out.println("physicsTimeoverEvent");
	}
	
	public void setEventDispatcher(EventDispatcher dispatcher){
		this.dispatcher = dispatcher;
	}
	public EventDispatcher getEventDispatcher(){
		return dispatcher;
	}
}
