package com.kbzgame.physics.shape;

public class EdgeRect {
	private double leftX,rightX,topY,belowY;
	public EdgeRect()
	{
	}
	public EdgeRect(double leftX,double rightX,double topY,double belowY)
	{
		this.leftX = leftX;
		this.rightX = rightX ;
		this.topY = topY ;
		this.belowY = belowY ;
	}
	public double getLeftX() {
		return leftX;
	}
	public double getRightX() {
		return rightX;
	}
	public double getTopY() {
		return topY;
	}
	public double getBelowY() {
		return belowY;
	}

}
