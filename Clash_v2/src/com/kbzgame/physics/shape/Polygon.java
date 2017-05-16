package com.kbzgame.physics.shape;

public class Polygon extends Shape{
	private Point[] points;
	public Polygon(){}
	public Polygon(Point[] points){
		this.points = points;
	}
	public Point[] getPoints(){
		return points;
	}
}
