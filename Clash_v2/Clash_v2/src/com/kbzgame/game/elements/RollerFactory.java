package com.kbzgame.game.elements;

import com.kbzgame.physics.shape.Point;
import com.kbzgame.physics.shape.ShapeFactory;

public class RollerFactory {
	public static  Roller createRoller(String name){
		if(Math.random()>0.5){
			return  createCircleRoller(name);
		}
		else{
			return createPolygonRoller(name);
		}
	}
	public static Roller createCircleRoller(String name){
		Roller roller = new Roller(ShapeFactory.createCircle(5,5,5));
		roller.setName(name);
		return roller;
	}
	public static Roller createPolygonRoller(String name){
		Point[] points = new Point[]{new Point(0,0),new Point(5,8),new Point(8,5),new Point(5,0)};
		Roller roller = new Roller(ShapeFactory.createPolygon(points, 4));
		roller.setName(name);
		return roller;
	}
}
