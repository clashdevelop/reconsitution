package com.kbzgame.physics.shape;

public class Circle extends Shape{
	private Point position;
	private double r;
	public Circle(Point position,double r){
		this.position = position;
		this.r = r;
	}
	public Point getPosition(){
		return position;
	}
	public double getR(){
		return r;
	}
	@Override
	public void changePositionBy(double tx, double ty) {
		// TODO Auto-generated method stub
		position.changeBy(tx, ty);
	}
}
