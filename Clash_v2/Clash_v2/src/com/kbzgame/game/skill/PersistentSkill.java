package com.kbzgame.game.skill;


public abstract class PersistentSkill extends Skill {
	private int keepround;
	private int delayround;
	private boolean active;
	private boolean ready;
	public PersistentSkill(){
		ready = true;
		active = false;
		keepround = 0;
		delayround = 0;
	}
	public void use(){
		if(ready){
			active = true;
			keepround = keepActionRound();
			delayround = useDelayRound();
			useAction();
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
}
