package com.kbzgame.physics.shape;

public class PolygonCrashWithPolygonSATStrategy extends PolygonCrashWithPolygonStrategy{
	
	private Vector backVector=new Vector(0,0);
	public Vector getBackVector() {
		return backVector;
	}
	//private double minBackSize=0;
	static class Back
	{
		double BackSize;
		Vector backVector;
		
	}
	Back []back=new Back[16] ;
	Back whatWeWantBack;
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
			else if(maxShadow_A>minShadow_B)
				//BackSize[i]=maxShadow_A-minShadow_B;
				{back[i].BackSize=maxShadow_A-minShadow_B;
				back[i].backVector=edgeNormalVectors_A[i];}
			else if(minShadow_A<maxShadow_B)
				//BackSize[i]=maxShadow_B-minShadow_A;
				{back[i].BackSize=maxShadow_B-minShadow_A;
				back[i].backVector=edgeNormalVectors_A[i];}
			
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
			else if(maxShadow_A>minShadow_B)
				//BackSize[i+polygonA.edgeNum]=maxShadow_A-minShadow_B;
			{
				back[i+polygonA.edgeNum].BackSize=maxShadow_A-minShadow_B;
				back[i+polygonA.edgeNum].backVector=edgeNormalVectors_B[i];
			}
			else if(minShadow_A<maxShadow_B)
				//BackSize[i+polygonA.edgeNum]=maxShadow_B-minShadow_A;
			{
				back[i+polygonA.edgeNum].BackSize=maxShadow_B-minShadow_A;
				back[i+polygonA.edgeNum].backVector=edgeNormalVectors_B[i];
			}
		}
		for(int i=1;i<polygonA.edgeNum+polygonB.edgeNum;i++)
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
