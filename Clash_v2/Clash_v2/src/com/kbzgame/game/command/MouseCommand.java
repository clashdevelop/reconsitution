package com.kbzgame.game.command;

import com.kbzgame.game.elements.Roller;
import com.kbzgame.physics.shape.Vector;

public class MouseCommand extends Command{
	private Roller reciver;
	private double mouseX;
	private double mouseY;
	public MouseCommand(Roller reciver,double mouseX,double mouseY){
		this.reciver = reciver;
		this.mouseX = mouseX;
		this.mouseY = mouseY;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		double times = 1000;
		Vector mouseF = new Vector(0,0);
		mouseF.resetComponent(mouseX,mouseY);
		if(mouseF.getSize()>600){
			reciver.addMouseF(new Vector(500/times,mouseF.getAngle()));
			return;
		}
		//System.out.println("size"+mouseF.getSize());
		reciver.addMouseF(mouseF.divByNum(times));
	}
	
}
