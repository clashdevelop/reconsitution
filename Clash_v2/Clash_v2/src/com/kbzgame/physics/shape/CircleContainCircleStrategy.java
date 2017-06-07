package com.kbzgame.physics.shape;

public class CircleContainCircleStrategy {
	public TestResult testWithBack(Circle borderCircle,Circle circle){
		double dx = borderCircle.getPosition().getX()-circle.getPosition().getX();
		double dy = borderCircle.getPosition().getY()-circle.getPosition().getY();
		double dis = Math.sqrt(dx*dx+dy*dy);
		if(dis+circle.getR()<=borderCircle.getR()){return new TestResult(true,null);}
		Vector direVetor = new Vector(circle.getPosition().getX(),circle.getPosition().getY(),borderCircle.getPosition().getX(),borderCircle.getPosition().getY()); 
		double size = dis+circle.getR()-borderCircle.getR();
		Vector backVector = new Vector(size,direVetor.getAngle());
		return new TestResult(false,backVector);
	}
	public boolean test(Circle borderCircle,Circle circle){
		double dx = borderCircle.getPosition().getX()-circle.getPosition().getX();
		double dy = borderCircle.getPosition().getY()-circle.getPosition().getY();
		double dis = Math.sqrt(dx*dx+dy*dy);
		if(dis+circle.getR()<=borderCircle.getR()){return true;}
		return false;
	}
}
