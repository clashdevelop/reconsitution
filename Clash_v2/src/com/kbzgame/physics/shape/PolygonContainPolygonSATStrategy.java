package com.kbzgame.physics.shape;

public class PolygonContainPolygonSATStrategy extends PolygonContainPolygonStrategy{

	@Override
	public boolean test(Polygon borderPolygon, Polygon polygon) {
		// TODO Auto-generated method stub
		Vector[] edgeNormalVectors_A = borderPolygon.getEdgeNormalVectors();
		for(int i=0;i<borderPolygon.getEdgeNum();i++){
			double[] shadow_A = borderPolygon.shadowToVector(edgeNormalVectors_A[i]);
			double[] shadow_B = polygon.shadowToVector(edgeNormalVectors_A[i]);
			double minShadow_A = shadow_A[0];
			double maxShadow_A = shadow_A[1];
			double minShadow_B = shadow_B[0];
			double maxShadow_B = shadow_B[1];
			if(!((minShadow_A<minShadow_B)&&(maxShadow_A>maxShadow_B))){
				return false;
			}
			
		}
		Vector[] edgeNormalVectors_B = polygon.getEdgeNormalVectors();
		for(int i=0;i<polygon.getEdgeNum();i++){
			double[] shadow_A = borderPolygon.shadowToVector(edgeNormalVectors_B[i]);
			double[] shadow_B = polygon.shadowToVector(edgeNormalVectors_B[i]);
			double minShadow_A = shadow_A[0];
			double maxShadow_A = shadow_A[1];
			double minShadow_B = shadow_B[0];
			double maxShadow_B = shadow_B[1];
			if(!((minShadow_A<minShadow_B)&&(maxShadow_A>maxShadow_B))){
				return false;
			}
		}
		//
		return true;
	}

}