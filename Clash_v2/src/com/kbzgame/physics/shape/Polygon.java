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
	public int getEdgeNum(){
		return edgeNum;
	}
	public Vector[] getEdgeVectors(){
		Vector[] edgeVectors = new Vector[edgeNum];
		for(int i=0;i<edgeNum-1;i++){
			edgeVectors[i] = new Vector(points[i].getX(),points[i].getY(),points[i+1].getX(),points[i+1].getY());
		}
		edgeVectors[edgeNum-1] = new Vector(points[edgeNum-1].getX(),points[edgeNum-1].getY(),points[0].getX(),points[0].getY());
		return edgeVectors;
	}
	public Vector[] getEdgeNormalVectors(){
		Vector[] edgeVectors = getEdgeVectors();
		Vector[] edgeNormalVectors = new Vector[edgeNum];
		for(int i=0;i<edgeNum-1;i++){
			edgeNormalVectors[i] = new Vector(1,edgeVectors[i].getAngle()+Math.PI/2);
		}
		return edgeNormalVectors;
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
	@Override
	public void changePositionBy(double tx, double ty) {
		// TODO Auto-generated method stub
		for(int i=0;i<edgeNum;i++){
			points[i].changeBy(tx, ty);
		}
	}
}
