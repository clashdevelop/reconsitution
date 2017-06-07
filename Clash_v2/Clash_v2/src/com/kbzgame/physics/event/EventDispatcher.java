//�ַ���ʱ���¼��������¼��������¼�����Ŀǰֻ����ʱ���¼���
//�ַ���Ϸ�¼�
//
package com.kbzgame.physics.event;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.kbzgame.physics.elements.PhysicsElements;

public class EventDispatcher {
	//��֤������̰߳�ȫ(ע���¼��߳� �¼������߳̾���)
	Map<Class<? extends PhysicsEvent>,Map<PhysicsElements ,EventListener>> listenerMap = Collections.synchronizedMap(new HashMap<Class<? extends PhysicsEvent>,Map<PhysicsElements,EventListener>>());	
	public void registerEventListener(Class<? extends PhysicsEvent> eventClass,PhysicsElements element,EventListener listener){
		Map<PhysicsElements ,EventListener> listeners = listenerMap.get(eventClass);
		if(listeners==null){
			//���¼�����
			listeners = Collections.synchronizedMap(new HashMap<PhysicsElements ,EventListener>());
			listenerMap.put(eventClass,listeners);
		}
		listeners.put(element,listener);
		//System.out.println("add a new  register");
	}
	public void unregisterEventListener(Class<? extends PhysicsEvent> eventClass,PhysicsElements element,EventListener listener){
		Map<PhysicsElements ,EventListener> listeners = listenerMap.get(eventClass);
		if(listeners!=null){
			//ɾ��ָ����ע����Ϣ
			listeners.remove(element);
		}
	}
	//����Ϊͬ����Ϣ
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
