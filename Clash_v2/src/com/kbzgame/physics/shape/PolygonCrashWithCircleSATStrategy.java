

package com.kbzgame.physics.shape;



public class PolygonCrashWithCircleSATStrategy extends PolygonCrashWithCircleStrategy{
	private double minSize=0;
	private double angle=0;
	private Vector backVector=new Vector(0,0);
	public Vector getBackVector() {
		return backVector;
	}
	

	@Override
	public boolean test(Polygon polygon, Circle circle) {
		// TODO Auto-generated method stub
		
		minSize=10000;
		backVector=new Vector(0,0);
		Vector[] edgeNormalVectors = polygon.getEdgeNormalVectors();
		for(int i=0;i<polygon.getEdgeNum();i++){
			double[] shadow_polygon = polygon.shadowToVector(edgeNormalVectors[i]);
			double[] shadow_circle=circle.shadowToVector(edgeNormalVectors[i]);
			double minShadow_A = shadow_polygon[0];
			double maxShadow_A = shadow_polygon[1];
			double minShadow_B = shadow_circle[0];
			double maxShadow_B = shadow_circle[1];
			if(minShadow_B-maxShadow_A>0.00001 || minShadow_A-maxShadow_B>0.00001)return false;			
			 if(maxShadow_A>=minShadow_B)
				
				//BackSize[i]=maxShadow_A-minShadow_B;
				 if(maxShadow_A-minShadow_B<minSize)
				 {
					 minSize=maxShadow_A-minShadow_B;
					 angle=edgeNormalVectors[i].getAngle()+Math.PI;
							 
				 }
			 if(minShadow_A<maxShadow_B)
				//BackSize[i]=maxShadow_B-minShadow_A;
				 if(maxShadow_B-minShadow_A<minSize)
				 {
					 minSize=maxShadow_B-minShadow_A;
					 angle=edgeNormalVectors[i].getAngle();
				 }
			
			}
		Vector[] pointToCenter = new Vector[polygon.getEdgeNum()];
		for(int i=0;i<polygon.edgeNum;i++)
		{
			Point []points=polygon.getPoints();
			pointToCenter[i]=new Vector(points[i].getX(),points[i].getY(),circle.getPosition().getX(),circle.getPosition().getY());
		}
		for(int i=0;i<polygon.getEdgeNum();i++){
			double[] shadow_polygon = polygon.shadowToVector(pointToCenter[i]);
			double[] shadow_circle=circle.shadowToVector(pointToCenter[i]);
			double minShadow_A = shadow_polygon[0];
			double maxShadow_A = shadow_polygon[1];
			double minShadow_B = shadow_circle[0];
			double maxShadow_B = shadow_circle[1];
			if(minShadow_B-maxShadow_A>0.00001 || minShadow_A-maxShadow_B>0.00001)return false;
			
			 if(maxShadow_A-minShadow_B>0)
				
				//BackSize[i]=maxShadow_A-minShadow_B;
				 if(maxShadow_A-minShadow_B<minSize)
				 {
					 minSize=maxShadow_A-minShadow_B;
					 angle=pointToCenter[i].getAngle()+Math.PI;
							 
				 }
			 if(minShadow_A<maxShadow_B)
				//BackSize[i]=maxShadow_B-minShadow_A;
				 if(maxShadow_B-minShadow_A<minSize)
				 {
					 minSize=maxShadow_B-minShadow_A;
					 angle=pointToCenter[i].getAngle();
				 }
			
			}
		Vector bac=new Vector(minSize,angle);
		backVector=bac;
		return true;
	}
}