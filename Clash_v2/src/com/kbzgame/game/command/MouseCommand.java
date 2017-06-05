package com.kbzgame.game.command;

import com.kbzgame.game.elements.Roller;
import com.kbzgame.physics.shape.Vector;

public class MouseCommand extends Command{
	private Roller reciver;
	private double mouseX;
	private double mouseY;
	public MouseCommand(Roller reciver,double mouseX,double mouseY){
		this.reciver = reciver;
		if(mouseX>200){mouseX = 200;}
		if(mouseY>200){mouseY = 200;}
		this.mouseX = mouseX;
		this.mouseY = mouseY;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		Vector mouseF = new Vector(0,0);
		float times = 0.05f;
		double dx = mouseX * times;
		double dy = mouseY * times;
		mouseF.resetComponent(dx,dy);;
		reciver.addMouseF(mouseF);
	}
	
}
