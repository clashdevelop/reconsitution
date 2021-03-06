/*
 * 游戏规则类考虑了物理事件和游戏事件的不统一
 * 如：物理世界的两个物体碰撞可能意味着游戏世界玩家和玩家，玩家和障碍物等的碰撞
 * 游戏规则是一类游戏元素和一类游戏元素的物理事件到游戏事件的转换
 * 在此，游戏元素类型，物理事件确定一种游戏事件
 * 
 * 同时，GameRuler提供了两套接口，一个元素（出界），两个元素（碰撞）
 */
package com.kbzgame.game.elements;


import com.kbzgame.game.event.GameEvent;
import com.kbzgame.physics.elements.PhysicsElements;
import com.kbzgame.physics.event.PhysicsEvent;

public  class Rule {
	private Class<? extends PhysicsElements> elementAClass;
	private Class<? extends PhysicsElements> elementBClass;
	private Class<? extends PhysicsEvent> physicsEventClass;
	private Class<? extends GameEvent> gameEventClass;
	public Rule(Class<? extends PhysicsElements> elementAClass,Class<? extends PhysicsElements> elementBClass,Class<? extends PhysicsEvent> physicsEventClass,Class<? extends GameEvent> gameEventClass){
		this.elementAClass = elementAClass;
		this.elementBClass = elementBClass;
		this.physicsEventClass = physicsEventClass;
		this.gameEventClass = gameEventClass;
	}
	public GameEvent produceGameEvent(PhysicsElements elementA,PhysicsElements elementB,PhysicsEvent physicsEvent){
		if(elementAClass == null || elementBClass == null || physicsEventClass==null || gameEventClass== null){
			return null;
		}
		boolean compareSuccess = elementAClass.isInstance(elementA) && elementBClass.isInstance(elementB) && physicsEventClass.isInstance(physicsEvent)
								||elementBClass.isInstance(elementA) && elementAClass.isInstance(elementB) && physicsEventClass.isInstance(physicsEvent);
		if(compareSuccess){
			try {
				GameEvent gameEvent = gameEventClass.newInstance();
				gameEvent.setElementA(elementA);
				gameEvent.setElementB(elementB);
				return gameEvent;
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				System.out.println("Error when create a 'GameEvetn' newInstance");
				e.printStackTrace();
			}
		}
		return null;
	}
	public Rule(Class<? extends PhysicsElements> elementClass,Class<? extends PhysicsEvent> physicsEventClass,Class<? extends GameEvent> gameEventClass){
		this.elementAClass = elementClass;
		this.physicsEventClass = physicsEventClass;
		this.gameEventClass = gameEventClass;
	}
	public GameEvent produceGameEvent(PhysicsElements element,PhysicsEvent physicsEvent){
		if(elementAClass == null || physicsEventClass==null || gameEventClass== null){
			return null;
		}
		if(elementAClass.isInstance(element) && physicsEventClass.isInstance(physicsEvent)){
			try {
				GameEvent gameEvent = gameEventClass.newInstance();
				gameEvent.setElement(element);
				return gameEvent;
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				System.out.println("Error when create a 'GameEvetn' newInstance");
				e.printStackTrace();
			}
		}
		return null;
	}
}
