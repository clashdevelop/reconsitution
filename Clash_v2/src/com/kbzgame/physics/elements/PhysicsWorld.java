package com.kbzgame.physics.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.kbzgame.physics.shape.EdgeRect;
import com.kbzgame.physics.shape.Shape;
import com.kbzgame.physics.shape.Vector;
import com.kbzgame.quadtree.Quadtree;

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
					
//					for (int i = 0; i < allObjects.size(); i++) {
//						  returnObjects.clear();
//						  quad.retrieve(returnObjects, allObjects.get(i));
//						  returnObjects.remove(allObjects.get(i));
//						  for (int c = 0; c < returnObjects.size(); c++) {
//						    // Run collision detection algorithm between objects
//							//  System.out.println("just for trying !!");
//							  System.out.println(shapeCrashShapeDetection.shapeCrashWithShape(allObjects.get(i), returnObjects.get(c)));
//							  System.out.println(shapeCrashShapeDetection.getBackVector(allObjects.get(i), returnObjects.get(c))); ;
//							  if(shapeCrashShapeDetection.shapeCrashWithShape(allObjects.get(i), returnObjects.get(c)))has++;
//							  count++;
//						  }
						
//						}
					
					
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
