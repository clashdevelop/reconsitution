package com.kbzgame.physics.shape;

public class PolygonContainCircleSATStrategy extends PolygonContainCircleStrategy{
	private PolygonCrashWithCircleStrategy pcw = new PolygonCrashWithCircleSATStrategy(); 
	@Override
	public TestResult testWithBack(Polygon borderPolygon, Circle circle) {
		// TODO Auto-generated method stub
		boolean contain = true;
		Vector[] edges = borderPolygon.getEdgeVectors();
		Vector []edgeNormalVectors=borderPolygon.getEdgeNormalVectors();
		Point[] points = borderPolygon.getPoints();
		Vector backVector = new Vector(0,0);
		for(int i=0;i<borderPolygon.edgeNum;i++)
		{
			double[] shadow_polygon = borderPolygon.shadowToVector(edgeNormalVectors[i]);
			double[] shadow_circle=circle.shadowToVector(edgeNormalVectors[i]);
			double minShadow_A = shadow_polygon[0];
			double maxShadow_A = shadow_polygon[1];
			double minShadow_B = shadow_circle[0];
			double maxShadow_B = shadow_circle[1];
			if(!((minShadow_A<minShadow_B)&&(maxShadow_A>maxShadow_B))){
				contain = false;
				Vector edge = edges[i];
				Point p = new Point(points[i].getX()-edge.getComponentX(),points[i].getY()-edge.getComponentY());
				Rect rect = new Rect(p,edge.getSize()*3,edge.getSize(),edge.getAngle());
				//System.out.println(rect);
				TestResult result= pcw.test(rect, circle);
				if(result.isHappened()){
					backVector.addVector(new Vector(result.getBackVector().getSize(),result.getBackVector().getAngle()+Math.PI));
				}
			}
		}
		if(contain){
			return new TestResult(true,null);
		}else{
			return new TestResult(false,backVector);
		}
	
	}
	@Override
	public boolean test(Polygon borderPolygon, Circle circle) {
		// TODO Auto-generated method stub
		
		Vector []edgeNormalVectors=borderPolygon.getEdgeNormalVectors();
		
		for(int i=0;i<borderPolygon.edgeNum;i++)
		{
			double[] shadow_polygon = borderPolygon.shadowToVector(edgeNormalVectors[i]);
			double[] shadow_circle=circle.shadowToVector(edgeNormalVectors[i]);
			double minShadow_A = shadow_polygon[0];
			double maxShadow_A = shadow_polygon[1];
			double minShadow_B = shadow_circle[0];
			double maxShadow_B = shadow_circle[1];
			if(!((minShadow_A<minShadow_B)&&(maxShadow_A>maxShadow_B))){
				return false;
			}
		}
		return true;
	
	}
}
