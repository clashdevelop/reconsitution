/*
 * 而游戏事件则比较复杂，建此基类方便以后的扩充
 * 游戏事件有两种：一种是有两个参与者（碰撞事件）
 * 一种是有一个参与者（出界）
 * 在此，不做区分（没有创建两个类），而是通过一套接口来实现
 * 只有一个元素时(elementA)，让另一个元素(elementB)保持为null
 */
package com.kbzgame.game.elements;


public class GameEvent {
	public GameEvent(){}//it is used to create a new instance by 'GmaeElements.class'
	//there are interface for event which have two elements
	private GameElements elementA;
	private GameElements elementB;
	public void setElementA(GameElements elementA){
		this.elementA = elementA;
	}
	public void setElementB(GameElements elementB){
		this.elementB = elementB;
	}
	public GameElements getElementA(){
		return elementA;
	}
	public GameElements getElementB(){
		return elementB;
	}
	public void setElement(GameElements element){
		elementA = element;
		elementB = null;
	}
}
