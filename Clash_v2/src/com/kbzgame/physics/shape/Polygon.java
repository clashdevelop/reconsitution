package com.kbzgame.physics.shape;

public class Polygon extends Shape{
	protected  Point[] points;
	protected int edgeNum;
	public Polygon(){}
	public Polygon(Point[] points,int edgeNum){
		this.points = points;
		this.edgeNum = edgeNum;
	}
	public Point[] getPoints(){
		return points;
	}
	public double[] shadowToVector(Vector vector){
		double[] shadows = new double[2];
		double minShadow = 0;
		double maxShadow = 0;
		for(int i=0;i<edgeNum;i++){
			Vector pointVector = new Vector(0,0,points[i].getX(),points[i].getY());
			Vector shadowVector = Vector.convertVectorToReferenceFrame(pointVector, vector);
			double pointShadow = Math.abs(shadowVector.getComponentX());
			if(pointShadow<minShadow){
				minShadow = pointShadow;
			}
			if(pointShadow>maxShadow){
				maxShadow = pointShadow;
			}
		}
		shadows[0] = minShadow;
		shadows[1] = maxShadow;
		return shadows;
	}
}
