/*
  *本类的对象被上层（游戏元素）所组合，上层可能会可能改变对象的某些信息
  *本类的对象将被加入PhysicsWorld类中，并被刷新线程使用
  *因此，本类对象的接口的使用涉及到临界资源的访问
*/
package com.kbzgame.physics.elements;

import com.kbzgame.physics.shape.Shape;
import com.kbzgame.physics.shape.Vector;

public class Body extends PhysicsElements{
	private Shape shape;
	private double m;
	private double f_factor = 1;
	private Vector F;
	private Vector v;
	private double maxVSize;
	private boolean movable;
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
			if(maxVSize >world.maxVSize){
				maxVSize = world.maxVSize;
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
			F.addVector(F);
		}
	}
	public void changePositionBy(double tx,double ty){
		synchronized (this) {
			shape.changePositionBy(tx, ty);
		}	
	}
	
	public void update(){
		synchronized (this) {
			Vector a = F.divByNum(m);
			F.resetComponent(0,0);//clear F when update
			changeV(a);
			changePositionBy(v.getComponentX(),v.getComponentY());	
		}	
	}
	private void changeV(Vector a){
		v.addVector(a);
		if(v.getSize()<f_factor*world.getG()){
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
		world.addBody(this);
	}
}
