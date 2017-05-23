/*
 * ����Ϸ�¼���Ƚϸ��ӣ����˻��෽���Ժ������
 * ��Ϸ�¼������֣�һ���������������ߣ���ײ�¼���
 * һ������һ�������ߣ����磩
 * �ڴˣ��������֣�û�д��������ࣩ������ͨ��һ�׽ӿ���ʵ��
 * ֻ��һ��Ԫ��ʱ(elementA)������һ��Ԫ��(elementB)����Ϊnull
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
