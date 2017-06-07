package com.kbzgame.physics.shape;

public class PolygonContainPolygonSATStrategy extends PolygonContainPolygonStrategy{
	private PolygonCrashWithPolygonStrategy pwp  = new PolygonCrashWithPolygonSATStrategy();
	@Override
	public TestResult testWithBack(Polygon borderPolygon, Polygon polygon) {
		// TODO Auto-generated method stub
		boolean contain = true;
		Vector[] edges = borderPolygon.getEdgeVectors();
		Point[] points = borderPolygon.getPoints();
		Vector backVector = new Vector(0,0);
		Vector[] edgeNormalVectors_A = borderPolygon.getEdgeNormalVectors();
		for(int i=0;i<borderPolygon.getEdgeNum();i++){
			double[] shadow_A = borderPolygon.shadowToVector(edgeNormalVectors_A[i]);
			double[] shadow_B = polygon.shadowToVector(edgeNormalVectors_A[i]);
			double minShadow_A = shadow_A[0];
			double maxShadow_A = shadow_A[1];
			double minShadow_B = shadow_B[0];
			double maxShadow_B = shadow_B[1];
			if(!((minShadow_A<minShadow_B)&&(maxShadow_A>maxShadow_B))){
				contain = false;
				Vector edge = edges[i];
				Point p = new Point(points[i].getX()-edge.getComponentX(),points[i].getY()-edge.getComponentY());
				Rect rect = new Rect(p,edge.getSize()*3,edge.getSize(),edge.getAngle());
				TestResult result= pwp.test(polygon,rect);
				if(result.isHappened()){
					backVector.addVector(result.getBackVector());
				}
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
				contain = false;
			}
		}
		//
		if(contain){
			return new TestResult(true,null);
		}else{
			return new TestResult(false,backVector);
		}
	}
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