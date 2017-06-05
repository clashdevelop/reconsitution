package com.kbzgame.game.elements;

import java.util.ArrayList;
import java.util.List;

import com.kbzgame.game.event.GameEvent;
import com.kbzgame.physics.elements.Contact;
import com.kbzgame.physics.elements.PhysicsElements;
import com.kbzgame.physics.event.PhysicsBodyCrashEvent;
import com.kbzgame.physics.event.PhysicsInAreaEvent;
import com.kbzgame.physics.event.PhysicsOutsideEvent;

public class RulerContact extends Contact{
	private List<Ruler> rulerList = new ArrayList<Ruler>();
	public void sendPhysicsEvent(PhysicsBodyCrashEvent physicsBodyCrashEvent){
		PhysicsElements bodyA = physicsBodyCrashEvent.getElementA();
		PhysicsElements bodyB = physicsBodyCrashEvent.getElementB();
		GameEvent gameEvent = null;
		for(Ruler ruler:rulerList){
			//test every ruler
			gameEvent = ruler.produceGameEvent(bodyA,bodyB,physicsBodyCrashEvent);
			if(gameEvent != null){
				//if find a game event that can be produce by ruler,send it
				if(getEventDispatcher()!=null){
					getEventDispatcher().addEvent(gameEvent);
				}
				
			}
		}
		super.sendPhysicsEvent(physicsBodyCrashEvent);
	}
	public void sendPhysicsEvent(PhysicsInAreaEvent physicsInAreaEvent){
		PhysicsElements body = physicsInAreaEvent.getElementA();
		PhysicsElements area = physicsInAreaEvent.getElementB();
		GameEvent gameEvent = null;
		for(Ruler ruler:rulerList){
			//test every ruler
			gameEvent = ruler.produceGameEvent(body,area,physicsInAreaEvent);
			if(gameEvent != null){
				//if find a game event that can be produce by ruler,send it
				if(getEventDispatcher()!=null){
					getEventDispatcher().addEvent(gameEvent);
				}
			}
		}
		super.sendPhysicsEvent(physicsInAreaEvent);
	}
	public void sendPhysicsEvent(PhysicsOutsideEvent physicsOutsideEvent){
		PhysicsElements body = physicsOutsideEvent.getElement();
		GameEvent gameEvent = null;
		for(Ruler ruler:rulerList){
			//test every ruler
			gameEvent = ruler.produceGameEvent(body,physicsOutsideEvent);
			if(gameEvent != null){
				//if find a game event that can be produce by ruler,send it
				if(getEventDispatcher()!=null){
					getEventDispatcher().addEvent(gameEvent);
				}
			}
		}
		super.sendPhysicsEvent(physicsOutsideEvent);
	}
	public void addGameRuler(Ruler ruler){
		rulerList.add(ruler);
	}
}
