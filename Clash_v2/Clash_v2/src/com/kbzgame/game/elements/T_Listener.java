package com.kbzgame.game.elements;

import com.kbzgame.physics.event.EventListener;
import com.kbzgame.physics.event.PhysicsEvent;

public class T_Listener implements EventListener{
	private String message;
	public T_Listener(String message){
		this.message = message;
	}
	@Override
	public void onEvent(PhysicsEvent event) {
		// TODO Auto-generated method stub
		System.out.println("send "+message);
	}

}
