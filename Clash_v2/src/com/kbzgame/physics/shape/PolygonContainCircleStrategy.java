package com.kbzgame.physics.shape;

public abstract class PolygonContainCircleStrategy {
	public abstract TestResult testWithBack(Polygon borderPolygon,Circle circle);
	public abstract boolean test(Polygon borderPolygon,Circle circle);
}
