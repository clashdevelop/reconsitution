package com.kbzgame.physics.shape;

public class Rect extends Polygon{
	public Rect(Point position,double width,double height,double angle){
		super();
		edgeNum = 4;
		points = caculatePoints(position,width,height,angle);
	}
	private Point[] caculatePoints(Point position,double width,double height,double angle){
		Vector wVector = new Vector(width,angle);
		Vector hVector = new Vector(height,angle+Math.PI/2);
		Point[] points = new Point[4];
		//基准点
		points[0] = position;
		points[1] = new Point(position.getX()+hVector.getComponentX(),position.getY()+hVector.getComponentY());
		points[2] = new Point(position.getX()+hVector.getComponentX()+wVector.getComponentX(),position.getY()+hVector.getComponentY()+wVector.getComponentY());
		points[3] = new Point(position.getX()+wVector.getComponentX(),position.getY()+wVector.getComponentY());
		//对角点
		return points;
	}
}
