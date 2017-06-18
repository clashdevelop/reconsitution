package com.kbzgame.game.skill;

import com.kbzgame.game.elements.Roller;

public abstract class Skill {
	private int keepround;//确保前端能够接受，保持的回合最好大于20
	private int delayround;
	private boolean active;
	private boolean ready;
	protected Roller owner;
	public Skill(Roller owner){
		this.owner = owner;
		ready = true;
		active = false;
		keepround = 0;
		delayround = 0;
	}
	public void use(){
		if(ready && owner.getCurrentSkill() == null){
			owner.setCurrentSkill(this);
			useAction();
			active = true;
			keepround = keepActionRound();
			delayround = useDelayRound();
			ready = false;
			
		}
	}
	public void update(){
		//skill delay use time
 		if(delayround==0){
			//if delay round='0',it's ready to use
			ready = true;
		}else if(delayround > 0){
			//else reduce delay round
			delayround--;
		}
		//skill keep time
		if(active){
			//if keep time over,change active to 'false',and use 'endAction()'
			if(keepround==0){
				active = false;
				endAction();
				owner.setCurrentSkill(null);
			}
			//else reduce keep time 
			else if(keepround>0){
				keepAction();
				keepround--;
			}
		}
	}
	
	protected abstract void useAction();
	protected abstract int keepActionRound();
	protected abstract void keepAction();
	protected abstract void endAction();
	protected abstract int useDelayRound(); 
	public abstract String getSkillName();
}
