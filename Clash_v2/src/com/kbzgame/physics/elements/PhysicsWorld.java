package com.kbzgame.physics.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.kbzgame.physics.shape.Vector;

public class PhysicsWorld {//默认为水平世界
	private List<Body> bodyList = new ArrayList<Body>();
	private List<Area> areaList = new ArrayList<Area>();
	private List<Timer> timerList = new ArrayList<Timer>();
	private ExecutorService taskManager = Executors.newCachedThreadPool();
	private double g = 1;
	private long roundDelayTime_MS = 200;
	public PhysicsWorld(){
		taskManager.execute(new UpdateTask());
	}
	
	public double getG(){return g;}
	public void setG(double g){this.g = g;}
	public void setroundDelayTime_MS(long delayTime){
		roundDelayTime_MS = delayTime;
	}
	public void addBody(Body body){
		bodyList.add(body);
	}
	
	private class UpdateTask implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try{
				while(!Thread.interrupted()){
					for(Body body:bodyList){
						if(body.isMovable()){
							addWorldFTo(body);
							body.move();
						}
					}
					
					TimeUnit.MILLISECONDS.sleep(roundDelayTime_MS);
				}
			}catch(InterruptedException e){
				//e.printStackTrace();
				//it's not a exception that exit when thread sleep
			}
		}
		
	}
	private void addWorldFTo(Body body){
		if(body.getV().getSize()!=0){
			body.addF(new Vector(body.getf_factor()*body.getM()*g,body.getV().getAngle()+Math.PI));
		}
	}
}
