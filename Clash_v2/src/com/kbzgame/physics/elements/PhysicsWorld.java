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
import com.kbzgame.physics.shape.EdgeRect;
import com.kbzgame.physics.shape.Shape;
import com.kbzgame.physics.shape.Vector;
import com.kbzgame.quadtree.Quadtree;

public class PhysicsWorld {//默认为水平世界
	public final double maxVSize = 5;
	//保证数组的线程安全
	private List<Body> bodyList = Collections.synchronizedList(new ArrayList<Body>());
	private List<Area> areaList = Collections.synchronizedList(new ArrayList<Area>());
	private List<Timer> timerList = Collections.synchronizedList(new ArrayList<Timer>());
	
	private ExecutorService taskManager = Executors.newCachedThreadPool();
	static private Quadtree quad = new Quadtree(0, new EdgeRect(0,1200,1200,0));
	public static Quadtree getQuad() {
			return quad;
		}
	private double g = 1;
	private long roundDelayTime_MS = 1;
	public Contact contact;
	private Shape borderShape;
	private EventDispatcher dispatcher;
	private BodyCrashWithBodyDetection bodyCrashWithBodyDetection = new BodyCrashWithBodyDetection();
	private BodyStepInAreaDetection bodyStepInArea = new BodyStepInAreaDetection();
	private BodyOutsideDetection bodyOutsideDetetction = new BodyOutsideDetection();
	public PhysicsWorld(){
		contact = new Contact();
		dispatcher = new EventDispatcher();
		contact.setEventDispatcher(dispatcher);
	}
	public void setBorderShape(Shape borderShape){
		this.borderShape = borderShape;
	}
	public void open(){
		taskManager.execute(new UpdateTask());
	}
	public void shut(){
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
		contact.setEventDispatcher(dispatcher);
		this.contact = contact;
	}
	public void setDispatcher(EventDispatcher dispatcher){
		this.dispatcher = dispatcher;
		contact.setEventDispatcher(dispatcher);
	}
	public void setRoundDelayTime_MS(long delayTime){
		roundDelayTime_MS = delayTime;
	}
	public long getRoundDelayTime_MS(){
		return roundDelayTime_MS;
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
//					for(Body body:bodyList){
//						if(body.isMovable()){
//							addWorldFTo(body);
//							body.update();
//						}
//					}
					quad.clear();
					for (int i = 0; i < bodyList.size(); i++) {
						{
							quad.insert(bodyList.get(i));	
							if(bodyList.get(i).isMovable()){
								addWorldFTo(bodyList.get(i));
								bodyList.get(i).update();
							}
						}
					}
					
					for(Timer timer:timerList){
						if(timer.sendMessage()){
							//send time over event
						}
						timer.run(roundDelayTime_MS);
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
