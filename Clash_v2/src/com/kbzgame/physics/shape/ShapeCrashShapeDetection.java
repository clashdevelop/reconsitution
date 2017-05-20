package com.kbzgame.physics.shape;

public class ShapeCrashShapeDetection {
	private PolygonCrashWithPolygonStrategy pwpStrategy;
	private PolygonCrashWithCircleStrategy pwcStrategy;
	private CircleCrashWithCircleStrategy cwcStrategy = new CircleCrashWithCircleStrategy();
	public boolean shapeCrashWithShape(Shape shapeA,Shape shapeB){
		if(isPolygon(shapeA) && isPolygon(shapeB)){
			return pwpStrategy.test((Polygon)shapeA,(Polygon)shapeB);
		}
		else if(isPolygon(shapeA) && isCircle(shapeB)){
			return pwcStrategy.test((Polygon)shapeA,(Circle)shapeB);
		}
		else if(isCircle(shapeA) && isPolygon(shapeB)){
			return pwcStrategy.test((Polygon)shapeB,(Circle)shapeA);
		}
		else if(isCircle(shapeA) && isCircle(shapeB)){
			return cwcStrategy.test((Circle)shapeA,(Circle)shapeB);
		}
		return false;
	}
	public void setPolygonCrashWithPolygonStrategy(PolygonCrashWithPolygonStrategy pwpStrategy){
		this.pwpStrategy = pwpStrategy;
	}
	public void setPolygonCrashWithCircleStrategy(PolygonCrashWithCircleStrategy pwcStrategy){
		this.pwcStrategy = pwcStrategy;
	}
	private boolean isPolygon(Shape shape){
		return Polygon.class.isInstance(shape);
	}
	private boolean isCircle(Shape shape){
		return Circle.class.isInstance(shape);
	}
}
