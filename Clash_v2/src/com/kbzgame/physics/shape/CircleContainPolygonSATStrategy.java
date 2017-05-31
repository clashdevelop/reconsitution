package com.kbzgame.physics.shape;

public class CircleContainPolygonSATStrategy extends CircleContainPolygonStrategy {

	@Override
	public boolean test(Circle borderCircle, Polygon polygon) {
		// TODO Auto-generated method stub
		//int a[]=new int[polygon.edgeNum];
		
		//Compare []compare =new Compare[8];
		Vector []edgeNormalVectors=polygon.getEdgeNormalVectors();
		for(int i=0;i<polygon.edgeNum;i++)
		{
			double[] shadow_polygon = polygon.shadowToVector(edgeNormalVectors[i]);
			double[] shadow_circle=borderCircle.shadowToVector(edgeNormalVectors[i]);
			double minShadow_A = shadow_polygon[0];
			double maxShadow_A = shadow_polygon[1];
			double minShadow_B = shadow_circle[0];
			double maxShadow_B = shadow_circle[1];
			if(!((minShadow_A<minShadow_B)&&(maxShadow_A>maxShadow_B)))
				return false;
			
		}
		
		return true;
	}

}
