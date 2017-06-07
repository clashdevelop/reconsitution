/*
  *����Ķ����ϲ㣨��ϷԪ�أ�����ϣ��ϲ���ܻ���ܸı�����ĳЩ��Ϣ
  *����Ķ��󽫱�����PhysicsWorld���У�����ˢ���߳�ʹ��
  *��ˣ��������Ľӿڵ�ʹ���漰���ٽ���Դ�ķ���
*/
package com.kbzgame.physics.elements;

import com.kbzgame.physics.shape.Shape;
import com.kbzgame.physics.shape.Vector;

public class Body extends PhysicsElements{
	private Shape shape;
	private double m;
	private double f_factor = 1;
	private double k_factor = 0;
	private Vector F;
	private Vector v;
	private double maxVSize = 0;
	private double minVSize = 0;
	private boolean movable = false;
	protected PhysicsWorld world;
	public Body(Shape shape){
		this.shape = shape;
		F = new Vector(0,0);
		v = new Vector(0,0);
	}
	//information function
	public Shape getShape(){
		synchronized (this) {
			return shape;
		}
	}
	public void setM(double m){
		synchronized (this) {
			this.m = m;
		}
	}
	public double getM(){
		synchronized (this) {
			return m;
		}
	}
	public void setf_factor(double k){
		synchronized (this) {
			if(k>1){
				k = 1;
			}
			this.f_factor = k;
		}
	}
	public double getf_factor(){
		synchronized (this) {
			return f_factor;
		}	
	}
	public void setk_factor(double k){
		synchronized (this) {
			if(k>1){
				k = 1;
			}
			this.k_factor = k;
		}
	}
	public double getk_factor(){
		synchronized (this) {
			return k_factor;
		}	
	}
	public Vector getV(){
		synchronized (this) {
			return v;
		}
	}
	public void setV(Vector v){
		synchronized (this) {
			this.v = v;
		}
	}
	public void setMaxVSize(double maxVSize){
		synchronized (this) {
			if(maxVSize >PhysicsWorld.maxVSize){
				maxVSize = PhysicsWorld.maxVSize;
			}
			this.maxVSize = maxVSize;
		}
	}
	public double getMaxVSize(){
		synchronized (this) {
			return maxVSize;
		}
	}
	public void setMovable(boolean movable){
		synchronized (this) {
			this.movable = movable;
		}	
	}
	public boolean isMovable(){
		synchronized (this) {
			return movable;
		}
	}
	//this function needn't synchronized
	public void addF(Vector F){
		synchronized (this) {
			this.F.addVector(F);
		}
	}
	public void changePositionBy(double tx,double ty){
		synchronized (this) {
			shape.changePositionBy(tx, ty);
		}	
	}
	
	public void update(){
		synchronized (this) {
			if(isMovable()){
				Vector a = F.divByNum(m);
				F.resetComponent(0,0);//clear F when update
				changeV(a);
				changePositionBy(v.getComponentX(),v.getComponentY());
			}
		}	
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
	@Override
	public void quitWorld(PhysicsWorld world) {
		// TODO Auto-generated method stub
		world.removeBody(this);
		world = null;
	}
	@Override
	public void addToWorld(PhysicsWorld world){
		this.world = world;
		minVSize =f_factor*world.getG();
		world.addBody(this);
	}
}
