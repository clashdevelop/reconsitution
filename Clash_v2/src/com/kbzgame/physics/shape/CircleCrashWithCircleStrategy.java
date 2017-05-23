package com.kbzgame.physics.shape;

public  class CircleCrashWithCircleStrategy {
	private Vector backVector;
	public  boolean test(Circle circleA,Circle circleB){
		backVector = new Vector(0,0);
		
		double dx = circleA.getPosition().getX()-circleB.getPosition().getX();
		double dy = circleA.getPosition().getY()-circleB.getPosition().getY();
		double dis = Math.sqrt(dx*dx+dy*dy);
		double crashDis = circleA.getR()+circleB.getR();
		if(dis>crashDis){return false;}
		double size = crashDis-dis;
		Vector vector = new Vector(circleB.getPosition().getX(),circleB.getPosition().getY(),circleA.getPosition().getX(),circleA.getPosition().getY());
		backVector = new Vector(size,vector.getAngle());
		return true;
	}
	public Vector getBackVector(){
		return backVector;
	}
}
