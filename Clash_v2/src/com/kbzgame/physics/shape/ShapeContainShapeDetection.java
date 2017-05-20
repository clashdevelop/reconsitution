package com.kbzgame.physics.shape;

public class ShapeContainShapeDetection {
	private PolygonContainPolygonStrategy pcpStrategy;
	private PolygonContainCircleStrategy pccStrategy;
	private CircleContainPolygonStrategy ccpStrategy;
	private CircleContainCircleStrategy cccStrategy = new CircleContainCircleStrategy();
	public boolean shapeContainShape(Shape borderShape,Shape shape){
		if(isPolygon(borderShape) && isPolygon(shape)){
			return pcpStrategy.test((Polygon)borderShape,(Polygon)shape);
		}
		else if(isPolygon(borderShape) && isCircle(shape)){
			return pccStrategy.test((Polygon)borderShape,(Circle)shape);
		}
		else if(isCircle(borderShape) && isPolygon(shape)){
			return ccpStrategy.test((Circle)borderShape,(Polygon)shape);
		}
		else if(isCircle(borderShape) && isCircle(shape)){
			return cccStrategy.test((Circle)borderShape,(Circle)shape);
		}
		return false;
	}
	public void setPolygonContainPolygonStrategy(PolygonContainPolygonStrategy pcpStrategy){
		this.pcpStrategy = pcpStrategy;
	}
	public void setPolygonContainCircleStrategy(PolygonContainCircleStrategy pccStrategy){
		this.pccStrategy = pccStrategy;
	}
	public void setCircleContainPolygonStrategy(CircleContainPolygonStrategy ccpStrategy){
		this.ccpStrategy = ccpStrategy;
	}
	private boolean isPolygon(Shape shape){
		return Polygon.class.isInstance(shape);
	}
	private boolean isCircle(Shape shape){
		return Circle.class.isInstance(shape);
	}
}
