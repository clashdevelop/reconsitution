package com.kbzgame.physics.shape;

public class CircleContainPolygonSATStrategy extends CircleContainPolygonStrategy {

	@Override
	public TestResult testWithBack(Circle borderCircle, Polygon polygon) {
		// TODO Auto-generated method stub
		Vector []edgeNormalVectors=polygon.getEdgeNormalVectors();
		for(int i=0;i<polygon.edgeNum;i++)
		{
			double[] shadow_polygon = polygon.shadowToVector(edgeNormalVectors[i]);
			double[] shadow_circle=borderCircle.shadowToVector(edgeNormalVectors[i]);
			double minShadow_A = shadow_polygon[0];
			double maxShadow_A = shadow_polygon[1];
			double minShadow_B = shadow_circle[0];
			double maxShadow_B = shadow_circle[1];
			if(!((minShadow_A<minShadow_B)&&(maxShadow_A>maxShadow_B))){
				double maxDis = 0;
				Point point = null;
				Point[] points = polygon.getPoints();
				for(int k=0;i<polygon.getEdgeNum();i++){
					double tx = points[k].getX()-borderCircle.getPosition().getX();
					double ty = points[k].getX()-borderCircle.getPosition().getX();
					double dis = Math.sqrt(tx*tx+ty*ty);
					if(dis>maxDis){
						maxDis = dis;
						point = points[k];
					}
				}
				Vector direVector = new Vector(point.getX(),point.getY(),borderCircle.getPosition().getX(),borderCircle.getPosition().getY());
				double size = maxDis-borderCircle.getR();
				Vector backVector = new Vector(size,direVector.getAngle());
				return new TestResult(false,backVector);
			}
			
		}
		
		return new TestResult(true,null);
	}

	@Override
	public boolean test(Circle borderCircle, Polygon polygon) {
		// TODO Auto-generated method stub
		Vector []edgeNormalVectors=polygon.getEdgeNormalVectors();
		for(int i=0;i<polygon.edgeNum;i++)
		{
			double[] shadow_polygon = polygon.shadowToVector(edgeNormalVectors[i]);
			double[] shadow_circle=borderCircle.shadowToVector(edgeNormalVectors[i]);
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
