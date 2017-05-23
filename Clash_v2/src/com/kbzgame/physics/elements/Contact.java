package com.kbzgame.physics.elements;

import java.util.ArrayList;
import java.util.List;

import com.kbzgame.game.elements.EventDispatcher;
import com.kbzgame.game.elements.GameEvent;
import com.kbzgame.game.elements.GameRuler;
import com.kbzgame.physics.event.PhysicsBodyCrashEvent;
import com.kbzgame.physics.event.PhysicsInAreaEvent;
import com.kbzgame.physics.event.PhysicsOutsideEvent;
import com.kbzgame.physics.event.PhysicsTimeoverEvent;

public class Contact {
	//list假设ruler不会出现两次相同的ruler对象
	//可以考虑使用set来存储ruler
	private List<GameRuler> rulerList = new ArrayList<GameRuler>();
	private EventDispatcher dispatcher=null;
	public void sendPhysicsEvent(PhysicsBodyCrashEvent physicsBodyCrashEvent){
		Body bodyA = physicsBodyCrashEvent.getBodyA();
		Body bodyB = physicsBodyCrashEvent.getBodyB();
		GameEvent gameEvent = null;
		for(GameRuler ruler:rulerList){
			//test every ruler
			gameEvent = ruler.produceGameEvent(bodyA.getGameElement(),bodyB.getGameElement(),physicsBodyCrashEvent);
			if(gameEvent != null){
				//if find a game event that can be produce by ruler,send it
				if(dispatcher!=null){
					dispatcher.addGameEvent(gameEvent);
				}
				
			}
		}
		
	}
	public void sendPhysicsEvent(PhysicsInAreaEvent physicsInAreaEvent){
		Body body = physicsInAreaEvent.getBody();
		Area area = physicsInAreaEvent.getArea();
		GameEvent gameEvent = null;
		for(GameRuler ruler:rulerList){
			//test every ruler
			gameEvent = ruler.produceGameEvent(body.getGameElement(),area.getGameElement(),physicsInAreaEvent);
			if(gameEvent != null){
				//if find a game event that can be produce by ruler,send it
				dispatcher.addGameEvent(gameEvent);
			}
		}
	}
	public void sendPhysicsEvent(PhysicsOutsideEvent physicsOutsideEvent){
		Body body = physicsOutsideEvent.getBody();
		GameEvent gameEvent = null;
		for(GameRuler ruler:rulerList){
			//test every ruler
			gameEvent = ruler.produceGameEvent(body.getGameElement(),physicsOutsideEvent);
			if(gameEvent != null){
				//if find a game event that can be produce by ruler,send it
				dispatcher.addGameEvent(gameEvent);
			}
		}
	}
	public void sendPhysicsEvent(PhysicsTimeoverEvent physicsTimeoverEvent){
		//just send time over event
		dispatcher.addPhysicsTimeoverEvent(physicsTimeoverEvent);
	}
	public void setEventDispatcher(EventDispatcher dispatcher){
		this.dispatcher = dispatcher;
	}
	
}
