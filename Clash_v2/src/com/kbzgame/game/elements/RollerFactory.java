package com.kbzgame.game.elements;

import com.kbzgame.physics.shape.ShapeFactory;

public class RollerFactory {
	public static Roller createRoller(String name){
		Roller roller = new Roller(ShapeFactory.createCircle(5,5,5));
		roller.setName(name);
		return roller;
	}
}
