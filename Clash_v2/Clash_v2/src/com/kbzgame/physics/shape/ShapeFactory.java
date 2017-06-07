package com.kbzgame.physics.shape;

public class ShapeFactory {
	public static Shape createCircle(double x,double y,double r){
		return new Circle(new Point(x,y),r);
	}
	public static Shape createPolygon(Point[] points,int edgeNum){
		return new Polygon(points,edgeNum);
	}
}
