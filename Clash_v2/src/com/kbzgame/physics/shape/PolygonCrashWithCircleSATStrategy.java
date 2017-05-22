package com.kbzgame.physics.shape;



public class PolygonCrashWithCircleSATStrategy extends PolygonCrashWithCircleStrategy{
	
	private Vector backVector=new Vector(0,0);
	public Vector getBackVector() {
		return backVector;
	}
	
	static class Back
	{
		double BackSize;
		Vector backVector;
		
	}
	Back []back=new Back[10] ;
	Back whatWeWantBack;
	@Override
	public boolean test(Polygon polygon, Circle circle) {
		// TODO Auto-generated method stub
		Vector[] edgeNormalVectors = polygon.getEdgeNormalVectors();
		for(int i=0;i<polygon.getEdgeNum();i++){
			double[] shadow_polygon = polygon.shadowToVector(edgeNormalVectors[i]);
			double[] shadow_circle=circle.shadowToVector(edgeNormalVectors[i]);
			double minShadow_A = shadow_polygon[0];
			double maxShadow_A = shadow_polygon[1];
			double minShadow_B = shadow_circle[0];
			double maxShadow_B = shadow_circle[1];
			if(maxShadow_A<minShadow_B || minShadow_A>maxShadow_B)return false;
			
			else if(maxShadow_A>minShadow_B)
				//BackSize[i]=maxShadow_A-minShadow_B;
				{back[i].BackSize=maxShadow_A-minShadow_B;
				back[i].backVector=edgeNormalVectors[i];}
			else if(minShadow_A<maxShadow_B)
				//BackSize[i]=maxShadow_B-minShadow_A;
				{back[i].BackSize=maxShadow_B-minShadow_A;
				back[i].backVector=edgeNormalVectors[i];}

			}
		for(int i=1;i<polygon.edgeNum;i++)
		{
			Back whatWeWantBack=back[0];
			if(back[i].BackSize<whatWeWantBack.BackSize)
			{
				whatWeWantBack=back[i];
			}
		}
		Vector bac=new Vector(whatWeWantBack.BackSize,whatWeWantBack.backVector.getAngle());
		backVector=bac;
		return true;
	}
}