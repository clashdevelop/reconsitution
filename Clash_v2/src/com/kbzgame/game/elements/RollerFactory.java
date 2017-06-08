package com.kbzgame.game.elements;

import java.util.ArrayList;
import java.util.List;

import com.kbzgame.physics.shape.Point;
import com.kbzgame.physics.shape.Shape;
import com.kbzgame.physics.shape.ShapeFactory;

public class RollerFactory {
	public static  Roller createRoller(String name,Shape borderShape){
		if(Math.random()>0.5){
			return  createCircleRoller(name,borderShape);
		}
		else{
			return createPolygonRoller(name,borderShape);
		}
	}
	public static Roller createCircleRoller(String name,Shape borderShape){
		Roller roller = new Roller(ShapeFactory.createCircle(5+(Math.random()*borderShape.getBorder().getRightX()-10),5+(Math.random()*borderShape.getBorder().getTopY()-10),5));
		roller.setName(name);
		return roller;
	}
	public static Roller createPolygonRoller(String name,Shape borderShape){
//		Point[] points = new Point[]{new Point(0,5),new Point(10,10),new Point(5,10),new Point(10,0),new Point(5,0)};
//		Roller roller = new Roller(ShapeFactory.createPolygon(points, 5));
		double x,y;
		x=Math.random()*borderShape.getBorder().getRightX();
		y=Math.random()*borderShape.getBorder().getTopY();
		double  init=Math.random();
		int edgeNum=(int) ((init<=0.3||init>0.6)?5:init*10);
		List<Point> points=new ArrayList<Point>();
		for(int i=0;i<edgeNum;i++)
		{
			points.add(new Point(x+Math.random()*((Math.random()>0.5)?1:-1)*10,y+Math.random()*((Math.random()>0.5)?1:-1)*10));
		}
		Point []pointArr=new Point[edgeNum];
		points.toArray(pointArr);
		
//		System.out.println(pointArr);
		Roller roller = new Roller(ShapeFactory.createPolygon(pointArr, edgeNum));
		roller.setName(name);
		return roller;
	}
}
