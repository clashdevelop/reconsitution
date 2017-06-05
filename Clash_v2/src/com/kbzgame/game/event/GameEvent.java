/*
 * 而游戏事件则比较复杂，建此基类方便以后的扩充
 * 游戏事件有两种：一种是有两个参与者（碰撞事件）
 * 一种是有一个参与者（出界）
 * 在此，不做区分（没有创建两个类），而是通过一套接口来实现
 * 只有一个元素时(elementA)，让另一个元素(elementB)保持为null
 */
package com.kbzgame.game.event;

import com.kbzgame.physics.event.PhysicsEvent;

public class GameEvent extends PhysicsEvent{//必须有默认构造函数
	public GameEvent(){}//it is used to create a new instance by 'GmaeElements.class'
	//there are interface for event which have two elements
}
