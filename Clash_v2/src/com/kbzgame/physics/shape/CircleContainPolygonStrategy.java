package com.kbzgame.physics.shape;

public abstract class CircleContainPolygonStrategy {
	public abstract TestResult testWithBack(Circle borderCircle,Polygon polygon);
	public abstract boolean test(Circle borderCircle,Polygon polygon);
}
