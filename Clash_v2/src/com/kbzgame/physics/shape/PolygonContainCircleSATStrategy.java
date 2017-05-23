package com.kbzgame.physics.shape;

public class PolygonContainCircleSATStrategy extends PolygonContainCircleStrategy{
	
	@Override
	public boolean test(Polygon broderPolygon, Circle circle) {
		// TODO Auto-generated method stub
		Vector []edgeNormalVectors=broderPolygon.getEdgeNormalVectors();
		for(int i=0;i<broderPolygon.edgeNum;i++)
		{
			double[] shadow_polygon = broderPolygon.shadowToVector(edgeNormalVectors[i]);
			double[] shadow_circle=circle.shadowToVector(edgeNormalVectors[i]);
			double minShadow_A = shadow_polygon[0];
			double maxShadow_A = shadow_polygon[1];
			double minShadow_B = shadow_circle[0];
			double maxShadow_B = shadow_circle[1];
			if(!(minShadow_A<minShadow_B)&&(maxShadow_A>maxShadow_B))
				return false;
			
		}
		
		return true;
	
	}
}
