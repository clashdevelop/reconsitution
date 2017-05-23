
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
		Vector[] edgeNormalVectors = polygon.getEdgeNormalVectors();
		minSize=10000;
		backVector=new Vector(0,0);
		for(int i=0;i<polygon.getEdgeNum();i++){
			double[] shadow_polygon = polygon.shadowToVector(edgeNormalVectors[i]);
			double[] shadow_circle=circle.shadowToVector(edgeNormalVectors[i]);
			double minShadow_A = shadow_polygon[0];
			double maxShadow_A = shadow_polygon[1];
			double minShadow_B = shadow_circle[0];
			double maxShadow_B = shadow_circle[1];
			if(maxShadow_A<minShadow_B || minShadow_A>maxShadow_B)return false;
			
			 if(maxShadow_A>minShadow_B)
				
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
					 minSize=minShadow_B-maxShadow_A;
					 angle=edgeNormalVectors[i].getAngle();
				 }
			
			}
		Vector bac=new Vector(minSize,angle);
		backVector=bac;
		return true;
	}
}