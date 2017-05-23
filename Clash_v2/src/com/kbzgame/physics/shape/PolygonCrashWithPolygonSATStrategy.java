package com.kbzgame.physics.shape;

public class PolygonCrashWithPolygonSATStrategy extends PolygonCrashWithPolygonStrategy{
	private double angle;
	private double minSize;
	private Vector backVector=new Vector(0,0);
	public Vector getBackVector() {
		return backVector;
	}
	@Override
	public boolean test(Polygon polygonA, Polygon polygonB) {
		// TODO Auto-generated method stub
		Vector[] edgeNormalVectors_A = polygonA.getEdgeNormalVectors();
		minSize=10000;
		backVector=new Vector(0,0);
		for(int i=0;i<polygonA.getEdgeNum();i++){
			double[] shadow_A = polygonA.shadowToVector(edgeNormalVectors_A[i]);
			double[] shadow_B = polygonA.shadowToVector(edgeNormalVectors_A[i]);
			double minShadow_A = shadow_A[0];
			double maxShadow_A = shadow_A[1];
			double minShadow_B = shadow_B[0];
			double maxShadow_B = shadow_B[1];
			if(maxShadow_A<minShadow_B || minShadow_A>maxShadow_B){
				return false;
			}
			
			 if(maxShadow_A>minShadow_B)
				if(maxShadow_A-minShadow_B<minSize){
				minSize=maxShadow_A-minShadow_B;
				angle=edgeNormalVectors_A[i].getAngle()+Math.PI;
				}
			 if(minShadow_A<maxShadow_B)
				 if(maxShadow_B-minShadow_A<minSize)
				 {
					 minSize=maxShadow_B-minShadow_A;
					 angle=edgeNormalVectors_A[i].getAngle();
				 }	
		}
		Vector[] edgeNormalVectors_B = polygonB.getEdgeNormalVectors();
		for(int i=0;i<polygonB.getEdgeNum();i++){
			double[] shadow_A = polygonA.shadowToVector(edgeNormalVectors_B[i]);
			double[] shadow_B = polygonA.shadowToVector(edgeNormalVectors_B[i]);
			double minShadow_A = shadow_A[0];
			double maxShadow_A = shadow_A[1];
			double minShadow_B = shadow_B[0];
			double maxShadow_B = shadow_B[1];
			if(maxShadow_A<minShadow_B || minShadow_A>maxShadow_B){
				return false;
			}
			if(maxShadow_A>minShadow_B)
				 if(maxShadow_A-minShadow_B<minSize){
				minSize=maxShadow_A-minShadow_B;
				angle=edgeNormalVectors_B[i].getAngle()+Math.PI;
				}
			 if(minShadow_A<maxShadow_B)
				//BackSize[i]=maxShadow_B-minShadow_A;
				 if(maxShadow_B-minShadow_A<minSize)
				 {
				minSize=maxShadow_B-minShadow_A;
				angle=edgeNormalVectors_B[i].getAngle();
				 }
		}
		Vector bac=new Vector(minSize,angle);
		backVector=bac;
		
		
		return true;
	}

}