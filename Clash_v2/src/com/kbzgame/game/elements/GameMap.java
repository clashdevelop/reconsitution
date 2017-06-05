package com.kbzgame.game.elements;


import java.util.List;

import com.kbzgame.physics.elements.Area;
import com.kbzgame.physics.elements.Body;
import com.kbzgame.physics.elements.PhysicsWorld;

public class GameMap extends PhysicsWorld{
	private RulerContact contact;
	public GameMap(){
		contact = new RulerContact();
		//add some game rulers
		setContact(contact);
	}
	public String getMapMessage(){
		String message = "";
		List<Body> bodys = getBodys();
		List<Area> areas = getAreas();
		return  message;
	}
	
}
