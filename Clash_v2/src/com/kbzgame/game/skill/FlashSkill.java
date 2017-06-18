package com.kbzgame.game.skill;

import com.kbzgame.game.elements.GameMap;
import com.kbzgame.game.elements.Roller;
import com.kbzgame.physics.shape.Vector;

public class FlashSkill extends Skill{
	public FlashSkill(Roller owner){
		super(owner);
	}
	@Override
	protected void useAction() {
		// TODO Auto-generated method stub
		owner.setCurrentSkill(this);
		Vector flashVector = new Vector(30,owner.getV().getAngle());
		owner.changePositionBy(flashVector.getComponentX(),flashVector.getComponentY());
	}
	@Override
	protected int keepActionRound() {
		// TODO Auto-generated method stub
		return (int)(300/GameMap.roundDelayTime_MS);
	}
	@Override
	protected void keepAction() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void endAction() {
		// TODO Auto-generated method stub
	}
	@Override
	protected int useDelayRound() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getSkillName() {
		// TODO Auto-generated method stub
		return "flash";
	}

}
