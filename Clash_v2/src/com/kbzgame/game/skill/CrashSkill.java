package com.kbzgame.game.skill;

import com.kbzgame.game.elements.GameMap;
import com.kbzgame.game.elements.Roller;
import com.kbzgame.physics.shape.Vector;

public class CrashSkill extends Skill{
	
	private double maxV;
	private double preMaxV;
	public CrashSkill(Roller owner){
		super(owner);
	}
	@Override
	protected void useAction() {
		// TODO Auto-generated method stub
		System.out.println("clash used");
		preMaxV = owner.getMaxVSize();
		maxV = 2;//world.maxVSize;
		 owner.setMaxVSize(maxV);
		Vector v = new Vector(maxV, owner.getV().getAngle());
		 owner.setV(v);
	}

	@Override
	protected int keepActionRound() {
		// TODO Auto-generated method stub
		return (int)(1000/GameMap.roundDelayTime_MS);
	}

	@Override
	protected void keepAction() {
		// TODO Auto-generated method stub
		if(maxV>preMaxV){
			maxV -= 0.05;
		}
		else{
			maxV = preMaxV;
		}
		owner.setMaxVSize(maxV);
	}

	@Override
	protected void endAction() {
		// TODO Auto-generated method stub
		owner.setMaxVSize(preMaxV);
	}

	@Override
	protected int useDelayRound() {
		// TODO Auto-generated method stub
		return (int)(2000/GameMap.roundDelayTime_MS);
	}

	@Override
	public String getSkillName() {
		// TODO Auto-generated method stub
		return "crash";
	}
}

