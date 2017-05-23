package com.kbzgame.physics.shape;

public abstract class PolygonCrashWithCircleStrategy {
	public abstract Vector getBackVector();
	public abstract boolean test(Polygon polygon,Circle circle);
}
