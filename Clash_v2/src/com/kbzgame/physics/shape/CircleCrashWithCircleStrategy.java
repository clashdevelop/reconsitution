package com.kbzgame.physics.shape;

public  class CircleCrashWithCircleStrategy {
	public  boolean test(Circle circleA,Circle circleB){
		double dx = circleA.getPosition().getX()-circleB.getPosition().getX();
		double dy = circleA.getPosition().getY()-circleB.getPosition().getY();
		double dis = Math.sqrt(dx*dx+dy*dy);
		double crashDis = circleA.getR()+circleB.getR();
		if(dis<crashDis){return true;}
		return false;
	}
}
