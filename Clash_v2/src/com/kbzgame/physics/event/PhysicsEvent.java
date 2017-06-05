package com.kbzgame.physics.event;

import com.kbzgame.physics.elements.PhysicsElements;

public class PhysicsEvent {
	private PhysicsElements elementA;
	private PhysicsElements elementB;
	public void setElementA(PhysicsElements elementA){
		this.elementA = elementA;
	}
	public void setElementB(PhysicsElements elementB){
		this.elementB = elementB;
	}
	public PhysicsElements getElementA(){
		return elementA;
	}
	public PhysicsElements getElementB(){
		return elementB;
	}
	public void setElement(PhysicsElements element){
		elementA = element;
		elementB = null;
	}
	public PhysicsElements getElement() {
		return elementA;
	}
}
