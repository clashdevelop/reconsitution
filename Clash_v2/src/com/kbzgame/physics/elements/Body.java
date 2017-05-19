package com.kbzgame.physics.elements;

import com.kbzgame.physics.shape.Shape;
import com.kbzgame.physics.shape.Vector;

public class Body {
	private Shape shape;
	private double m;
	private double f_factor;
	private double e_factor;
	private Vector F;
	private Vector v;
	private double minVSize;
	private double maxVSize;
	private boolean movable;
	private PhysicsWorld world;
	public Body(Shape shape){
		this.shape = shape;
		F = new Vector(0,0);
		v = new Vector(0,0);
	}
	//information function
	public Shape getShape(){return shape;}
	public void setM(double m){this.m = m;}
	public double getM(){return m;}
	public void setK(double k){this.f_factor = k;}
	public double getf_factor(){return f_factor;}
	public Vector getV(){return v;}
	public void setMinVSize(double minVSize){this.minVSize = minVSize;}
	public void setMaxVSzie(double maxVSize){this.maxVSize = maxVSize;}
	public void setMovable(boolean movable){this.movable = movable;}
	public boolean isMovable(){return movable;}
	//
	public void addTo(PhysicsWorld world){
		this.world = world;
		world.addBody(this);
	}
	public void addF(Vector F){
		F.addVector(F);
	}
	public void changePositionBy(double tx,double ty){
		shape.changePositionBy(tx, ty);
	}
	//move()��������ˢ���߳�,��Ϣ�ı亯�������ϲ���ã������ٽ���Դ,��Ҫ������
	//��ȡRoller���������Ϣ��������ÿ��ˢ�½���ȡ�ͷŸ���������ˢ���ٶȣ����Ǽ�ʱ�ͷ��ٽ���Դ
	//��addF��world��ˢ���߳̾���PhysicsWorld������������ˢ���ٶȣ����Ǽ�ʱ�Խϲ�
	public void move(){
		Vector a = F.divByNum(m);
		changeV(a);
		changePositionBy(v.getComponentX(),v.getComponentY());		
	}
	private void changeV(Vector a){
		v.addVector(a);
		if(v.getSize()<minVSize){
			v = new Vector(0,0);
		}
		else if(v.getSize()>maxVSize){
			v = new Vector(maxVSize,v.getAngle());
		}
	}
}
