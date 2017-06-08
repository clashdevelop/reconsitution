package com.kbzgame.physics.shape;

public class ShapeCrashShapeDetection {
	private PolygonCrashWithPolygonStrategy pwpStrategy=new PolygonCrashWithPolygonSATStrategy();
	private PolygonCrashWithCircleStrategy pwcStrategy=new PolygonCrashWithCircleSATStrategy();
	private CircleCrashWithCircleStrategy cwcStrategy = new CircleCrashWithCircleStrategy();
	private static ShapeCrashShapeDetection shapeDetection;
	private ShapeCrashShapeDetection(){}
	public static ShapeCrashShapeDetection create(){
		if(shapeDetection==null){
			shapeDetection = new ShapeCrashShapeDetection();
		}
		return shapeDetection;
	}
	public TestResult shapeCrashWithShape(Shape shapeA,Shape shapeB){
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
		return null;
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
