//分发定时器事件，出界事件（物理事件，且目前只允许定时器事件）
//分发游戏事件
//
package com.kbzgame.physics.event;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

import com.kbzgame.physics.elements.PhysicsElements;

public class EventDispatcher {
	//保证数组的线程安全(注册事件线程 事件传递线程竞争)
	private Map<Class<? extends PhysicsEvent>,Map<PhysicsElements ,EventListener>> listenerMap = Collections.synchronizedMap(new HashMap<Class<? extends PhysicsEvent>,Map<PhysicsElements,EventListener>>());	
	private BlockingQueue<PhysicsEvent> eventQueue = new LinkedBlockingDeque<PhysicsEvent>();
	private ExecutorService taskManager = Executors.newCachedThreadPool();
	public EventDispatcher(){
		taskManager.execute(new EventDealTask());
	}
	public void registerEventListener(Class<? extends PhysicsEvent> eventClass,PhysicsElements element,EventListener listener){
		Map<PhysicsElements ,EventListener> listeners = listenerMap.get(eventClass);
		if(listeners==null){
			//新事件类型
			listeners = Collections.synchronizedMap(new HashMap<PhysicsElements ,EventListener>());
			listenerMap.put(eventClass,listeners);
		}
		listeners.put(element,listener);
		//System.out.println("add a new  register");
	}
	public void unregisterEventListener(Class<? extends PhysicsEvent> eventClass,PhysicsElements element,EventListener listener){
		Map<PhysicsElements ,EventListener> listeners = listenerMap.get(eventClass);
		if(listeners!=null){
			//删除指定的注册信息
			if(listeners.get(element)==listener)
				listeners.remove(element);
		}
	}
	
	//异步消息
	public void notify(PhysicsEvent event){
		//dealWithEvent(gameEvent);
		try {
			eventQueue.put(event);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("This event intterupted is ok");
		}
	}
	
	
	private class EventDealTask implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				while(!Thread.interrupted()){
					PhysicsEvent event = eventQueue.take();
					dealWithEvent(event);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("This event intterupted is ok");
			}
		}
	}
	private void dealWithEvent(PhysicsEvent event){
		Class<? extends PhysicsEvent> eventClass = event.getClass();
		Map<PhysicsElements ,EventListener> listeners = listenerMap.get(eventClass);
		if(listeners != null){
			//System.out.println("messageList isn't null"+messageList.size());
			PhysicsElements elementA = event.getElementA();
			
			if(elementA!=null){
				EventListener listener = listeners.get(elementA);
				if(listener != null){
					listener.onEvent(event);
				}
			}
			PhysicsElements elementB = event.getElementB();
			if(elementB!=null){
				EventListener listener = listeners.get(elementB);
				if(listener!=null){
					listener.onEvent(event);
				}
			}
		}
	}
}
