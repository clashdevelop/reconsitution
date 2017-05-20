package com.kbzgame.physics.shape;

public class CircleContainCircleStrategy {
	public boolean test(Circle borderCircle,Circle circle){
		double dx = borderCircle.getPosition().getX()-circle.getPosition().getX();
		double dy = borderCircle.getPosition().getY()-circle.getPosition().getY();
		double dis = Math.sqrt(dx*dx+dy*dy);
		if(dis+circle.getR()<=borderCircle.getR()){return true;}
		return false;
	}
}
