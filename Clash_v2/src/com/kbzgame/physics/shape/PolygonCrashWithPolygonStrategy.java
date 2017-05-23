package com.kbzgame.physics.shape;

public abstract class PolygonCrashWithPolygonStrategy {
	public abstract Vector getBackVector();
	public abstract boolean test(Polygon polygonA,Polygon polygonB);
}
