package com.kbzgame.physics.shape;

public abstract class PolygonContainPolygonStrategy {
	public abstract TestResult testWithBack(Polygon borderPolygon,Polygon polygon);
	public abstract boolean test(Polygon borderPolygon,Polygon polygon);
}
