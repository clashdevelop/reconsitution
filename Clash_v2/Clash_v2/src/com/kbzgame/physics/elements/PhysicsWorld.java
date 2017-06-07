package com.kbzgame.physics.elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.kbzgame.physics.event.EventDispatcher;
import com.kbzgame.physics.event.EventListener;
import com.kbzgame.physics.event.PhysicsEvent;
import com.kbzgame.physics.event.PhysicsTimeoverEvent;
import com.kbzgame.physics.shape.Point;
import com.kbzgame.physics.shape.Shape;
import com.kbzgame.physics.shape.ShapeFactory;
import com.kbzgame.physics.shape.Vector;

public class PhysicsWorld {//默认为水平世界
	public static final double maxVSize = 3;
	public static final long roundDelayTime_MS = 10;
	//保证数组的线程安全
	private List<Body> bodyList = Collections.synchronizedList(new ArrayList<Body>());
	private List<Area> areaList = Collections.synchronizedList(new ArrayList<Area>());
	private List<Timer> timerList = Collections.synchronizedList(new ArrayList<Timer>());
	
	private ExecutorService taskManager = Executors.newCachedThreadPool();
	private double g = 1;
	
	private Shape borderShape;
	private boolean running = false;
	
	private Contact contact;
	private EventDispatcher dispatcher;
	private BodyMoveDetection detection;
	public PhysicsWorld(){
		contact = new Contact();
		dispatcher = new EventDispatcher();
		contact.setEventDispatcher(dispatcher);
		detection = new BodyMoveDetection();
		Point[] points = new Point[]{new Point(0,0),new Point(0,800),new Point(800,800),new Point(800,0)};
		borderShape = ShapeFactory.createPolygon(points, 4);
	}
	public void setBorderShape(Shape borderShape){
		this.borderShape = borderShape;
	}
	public void open(){
		running = true;
		taskManager.execute(new UpdateTask());
	}
	public void shut(){
		running = false;
		taskManager.shutdownNow();
	}
	public double getG(){
		synchronized (this) {
			return g;
		}
		
	}
	public void setG(double g){
		synchronized (this) {
			this.g = g;
		}
	}
	public void setContact(Contact contact){
		if(running){
			System.out.println("You should set contact befor world open");
			return;
		}
		if(contact.getEventDispatcher()==null){
			contact.setEventDispatcher(dispatcher);
		}
		this.contact = contact;
	}
	
	public void registerEventListener(Class<? extends PhysicsEvent> event,PhysicsElements element,EventListener listener){
		dispatcher.registerEventListener(event, element, listener);
	}
	public void unregisterEventListener(Class<? extends PhysicsEvent> event,PhysicsElements element,EventListener listener){
		dispatcher.unregisterEventListener(event, element, listener);
	}
	public void addBody(Body body){
		bodyList.add(body);
	}
	public void removeBody(Body body){
		bodyList.remove(body);
	}
	public void addArea(Area area){
		areaList.add(area);
	}
	public void removeArea(Area area){
		areaList.remove(area);
	}
	public void addTimer(Timer timer){
		timerList.add(timer);
	}
	public void removeTimer(Timer timer){
		timerList.remove(timer);
	}
	public List<Body> getBodys(){
		return new ArrayList<Body>(bodyList);
	}
	public List<Area> getAreas(){
		return new ArrayList<Area>(areaList);
	}
	
	private class UpdateTask implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try{
				while(!Thread.interrupted()){
					//thread safe
					List<Body> bodys = new ArrayList<Body>(bodyList);
					for(Body body:bodys){
						if(body.isMovable()){
							addWorldFTo(body);
							body.update();
						}
					}
					detection.test(borderShape,bodys,new ArrayList<Area>(areaList),contact);
					//调用监视器 并用contact作为联系人
					List<Timer> timers = new ArrayList<Timer>(timerList);
					for(Timer timer:timers){
						if(timer.sendMessage()){
							//send time over event
							PhysicsTimeoverEvent event = new PhysicsTimeoverEvent(timer);
							contact.sendPhysicsEvent(event);
						}
						timer.run(roundDelayTime_MS);
					}
					//更新维护定时器
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
