//分发定时器事件，出界事件（物理事件，且目前只允许定时器事件）
//分发游戏事件
//
package com.kbzgame.physics.event;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.kbzgame.physics.elements.PhysicsElements;

public class EventDispatcher {
	//保证数组的线程安全(注册事件线程 事件传递线程竞争)
	Map<Class<? extends PhysicsEvent>,Map<PhysicsElements ,EventListener>> listenerMap = Collections.synchronizedMap(new HashMap<Class<? extends PhysicsEvent>,Map<PhysicsElements,EventListener>>());	
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
			listeners.remove(element);
		}
	}
	//现在为同步消息
	public void notify(PhysicsEvent gameEvent){
		dealWithEvent(gameEvent);
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
