package com.kbzgame.game.event;

import com.kbzgame.physics.event.EventDispatcher;

public class T_EventDispatcher extends EventDispatcher{
	public void addGameEvent(GameEvent gameEvent){
		System.out.println("Accept"+gameEvent.getClass().getSimpleName());
	}
}
