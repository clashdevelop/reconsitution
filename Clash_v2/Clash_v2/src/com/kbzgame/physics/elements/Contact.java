package com.kbzgame.physics.elements;

import com.kbzgame.physics.event.EventDispatcher;
import com.kbzgame.physics.event.PhysicsBodyCrashEvent;

import com.kbzgame.physics.event.PhysicsInAreaEvent;
import com.kbzgame.physics.event.PhysicsOutsideEvent;
import com.kbzgame.physics.event.PhysicsTimeoverEvent;

public class Contact {
	//list����ruler�������������ͬ��ruler����
	//���Կ���ʹ��set���洢ruler
	private EventDispatcher dispatcher=null;
	public void sendPhysicsEvent(PhysicsBodyCrashEvent physicsBodyCrashEvent){
		if(dispatcher!=null)
			dispatcher.notify(physicsBodyCrashEvent);	
	}
	public void sendPhysicsEvent(PhysicsInAreaEvent physicsInAreaEvent){
		if(dispatcher!=null)
			dispatcher.notify(physicsInAreaEvent);
	}
	public void sendPhysicsEvent(PhysicsOutsideEvent physicsOutsideEvent){
		if(dispatcher!=null)
			dispatcher.notify(physicsOutsideEvent);
	}
	public void sendPhysicsEvent(PhysicsTimeoverEvent physicsTimeoverEvent){
		//just send time over event
		if(dispatcher!=null)
			dispatcher.notify(physicsTimeoverEvent);
	}
	
	public void setEventDispatcher(EventDispatcher dispatcher){
		this.dispatcher = dispatcher;
	}
	public EventDispatcher getEventDispatcher(){
		return dispatcher;
	}
}
