package com.kbzgame.physics.shape;

public class Rect extends Polygon{
	private Point position;
	private double width = 10;
	private double height = 10;
	private double angle = 0;
	private Vector wVector = new Vector(10,0);
	private Vector hVector = new Vector(10,Math.PI/2);
	public Rect(Point position,double width,double height,double angle){
		super();
		//extend from Shape
		this.position = position;
		this.width = width;
		this.height = height;
		this.angle = angle;
		wVector = new Vector(width,angle);
		hVector = new Vector(height,angle+Math.PI/2);
	}
	public Point[] getPoints(){
		Point[] points = new Point[4];
		//基准点
		points[0] = position;
		points[1] = new Point(position.getX()+hVector.getComponentX(),position.getY()+hVector.getComponentY());
		points[2] = new Point(position.getX()+wVector.getComponentX(),position.getY()+wVector.getComponentY());
		//对角点
		points[3] = new Point(position.getX()+hVector.getComponentX()+wVector.getComponentX(),position.getY()+hVector.getComponentY()+wVector.getComponentY());
		return points;
	}
	//不可能为空，有初始值
	public Vector getWVector(){
		return wVector;
	}
	public Vector getHVector(){
		return hVector;
	}
	
	public double getWidth(){
		return width;
		
	}
	public double getHeight(){
		return height;
	}
	public double getAngle(){
		return angle;
	}
}
