/*
 * 第一次优化：2017-6-5
 * 新增private  Vector[] edgeVectors;
 *		private	Vector[] edgeNormalVectors;
 *	来存储边和边的法向量，省去每次计算的时间
 * 但是投影因为投影矢量的不确定，暂不优化
 */

package com.kbzgame.physics.shape;

public class Polygon extends Shape{
	protected  Point[] points;
	protected int edgeNum;
	private  Vector[] edgeVectors;
	private	Vector[] edgeNormalVectors;
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
		if(edgeVectors==null){
			edgeVectors = new Vector[edgeNum];
			for(int i=0;i<edgeNum-1;i++){
				edgeVectors[i] = new Vector(points[i].getX(),points[i].getY(),points[i+1].getX(),points[i+1].getY());
			}
			edgeVectors[edgeNum-1] = new Vector(points[edgeNum-1].getX(),points[edgeNum-1].getY(),points[0].getX(),points[0].getY());
		}
		return edgeVectors;
	}
	public Vector[] getEdgeNormalVectors(){
		if(edgeNormalVectors == null){
			Vector[] edgeVectors =  getEdgeVectors();
			edgeNormalVectors = new Vector[edgeNum];
			for(int i=0;i<edgeNum;i++){
				edgeNormalVectors[i] = new Vector(1,edgeVectors[i].getAngle()+Math.PI/2);
			}
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
			if(i==0)
			{
				minShadow = shadowVector.getComponentX();
				maxShadow = shadowVector.getComponentX();
			}
			double pointShadow = shadowVector.getComponentX();
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
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("p");
		for(int i=0;i<edgeNum;i++){
			builder.append(i>0?","+points[i]:""+points[i]);
		}
		builder.append("p");
		return builder.toString();
	}
	@Override
	public EdgeRect getBorder() {
		// TODO Auto-generated method stub
		double left=points[0].getX();
		double right=points[0].getX();
		double top=points[0].getY();
		double bottom=points[0].getY();
		for(int i=1;i<points.length;i++)
		{
			if(points[i].getX()<left){left=points[i].getX();}
			if(points[i].getX()>right){right=points[i].getX();}
			if(points[i].getY()<bottom){bottom=points[i].getY();}
			if(points[i].getY()>top){top=points[i].getY();}
		}
		EdgeRect border = new EdgeRect(left,right,top,bottom);
		return border;
	}
}
