package com.kbzgame.physics.shape;

public class EdgeRect {
	private int x,y,width,height;
	public EdgeRect()
	{
		this.x=0;
		this.y=0;
		this.width=0;
		this.height=0;
	}
	
	public EdgeRect(int x,int y,int width,int height)
	{
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
