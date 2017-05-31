package com.kbzgame.physics.elements;

import com.kbzgame.game.elements.EventDispatcher;
import com.kbzgame.game.elements.GameEvent;
import com.kbzgame.game.elements.GameRuler;
import com.kbzgame.physics.event.PhysicsBodyCrashEvent;
import com.kbzgame.physics.event.PhysicsInAreaEvent;
import com.kbzgame.physics.event.PhysicsOutsideEvent;
import com.kbzgame.physics.event.PhysicsTimeoverEvent;

public class T_Contact extends Contact{
	
	public void sendPhysicsEvent(PhysicsBodyCrashEvent physicsBodyCrashEvent){
		System.out.println("Accept a physicsBodyCrashEvent ");
	}
	public void sendPhysicsEvent(PhysicsInAreaEvent physicsInAreaEvent){
		System.out.println("Accept a physicsInAreaEvent ");
	}
	public void sendPhysicsEvent(PhysicsOutsideEvent physicsOutsideEvent){
		System.out.println("Accept a physicsOutsideEvent ");
	}
	public void sendPhysicsEvent(PhysicsTimeoverEvent physicsTimeoverEvent){
		//just send time over event
		System.out.println("Accept a physicsTimeoverEvent ");

	}
}
