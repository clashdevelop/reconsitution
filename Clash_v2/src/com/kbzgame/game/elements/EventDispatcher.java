//分发定时器事件，出界事件（物理事件，且目前只允许定时器事件）
//分发游戏事件
//
package com.kbzgame.game.elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.kbzgame.physics.elements.Body;
import com.kbzgame.physics.elements.Timer;
import com.kbzgame.physics.event.PhysicsTimeoverEvent;

public class EventDispatcher {
	Map<Class<? extends GameEvent>,List<RegisterMessage>> listenerMap = new HashMap<Class<? extends GameEvent>,List<RegisterMessage>>();
	Map<Timer,EventListener> timerListenerMap = new HashMap<Timer,EventListener>();
	
	public void registerGameEventListener(Class<? extends GameEvent> eventClass,GameElements element,EventListener listener){
		List<RegisterMessage> messageList = listenerMap.get(eventClass);
		if(messageList==null){
			//新事件类型
			messageList = new ArrayList<RegisterMessage>();
		}
		messageList.add(new RegisterMessage(element,listener));
	}
	public void registerTimeoverEventListener(Timer timer,EventListener listener){
		timerListenerMap.put(timer,listener);
	}
	//现在为同步消息
	public void addGameEvent(GameEvent gameEvent){
		dealWithEvent(gameEvent);
	}
	public void addPhysicsTimeoverEvent(PhysicsTimeoverEvent timeOverEvent){
		dealWithEvent(timeOverEvent);
	}
	
	
	private void dealWithEvent(GameEvent event){
		Class<? extends GameEvent> eventClass = event.getClass();
		List<RegisterMessage> messageList = listenerMap.get(eventClass);
		if(messageList != null){
			GameElements elementA = event.getElementA();
			GameElements elementB = event.getElementB();
			for(RegisterMessage message:messageList){
				if(elementA!=null){
					if(message.getElement()==elementA){
						EventListener listener = message.getEventListener();
						listener.onEvent();
					}
				}
				if(elementB!=null){
					if(message.getElement()==elementB){
						EventListener listener = message.getEventListener();
						listener.onEvent();
					}
				}
			}
		}
	}
	private void dealWithEvent(PhysicsTimeoverEvent event){
		EventListener listener = timerListenerMap.get(event.getTimer());
		if(listener!=null){
			listener.onEvent();
		}
	}
	private static class RegisterMessage{
		private GameElements element;
		private EventListener listener;
		public RegisterMessage(GameElements element,EventListener listener){
			this.element = element;
			this.listener = listener;
		}
		public GameElements getElement(){
			return element;
		}
		public  EventListener getEventListener(){
			return listener;
		}
	}
}
