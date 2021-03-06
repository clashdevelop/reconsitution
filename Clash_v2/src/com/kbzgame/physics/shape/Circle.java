package com.kbzgame.physics.shape;

public class Circle extends Shape{
	private Point position;
	private double r;
	public Circle(Point position,double r){
		this.position = position;
		this.r = r;
	}
	public Point getPosition(){
		return position;
	}
	public double getR(){
		return r;
	}
	@Override
	public void changePositionBy(double tx, double ty) {
		// TODO Auto-generated method stub
		position.changeBy(tx, ty);
	}
	public double[] shadowToVector(Vector vector){
		double[] shadows = new double[2];
		double minShadow = 0;
		double maxShadow = 0;
		Vector shadowVector=Vector.convertVectorToReferenceFrame(new Vector(0,0,position.getX(),position.getY()),vector);
		minShadow=shadowVector.getComponentX()-r;
		maxShadow=shadowVector.getComponentX()+r;
		shadows[0] = minShadow;
		shadows[1] = maxShadow;
		return shadows;
	}
	public String toString(){
		return "c"+position+","+r+"c";
	}
	@Override
	public EdgeRect getBorder() {
		// TODO Auto-generated method stub
		return new EdgeRect(getPosition().getX()-getR(),getPosition().getX()+getR(),
		getPosition().getY()+getR(),getPosition().getY()-getR());
	}
}
