package com.kbzgame.physics.shape;

public class PolygonCrashPolygonSATStrategy extends PolygonCrashWithPolygonStrategy{

	@Override
	public boolean test(Polygon polygonA, Polygon polygonB) {
		// TODO Auto-generated method stub
		Vector[] edgeNormalVectors_A = polygonA.getEdgeNormalVectors();
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
		}
		return true;
	}

}
