package com.kbzgame.game.skill;


public abstract class NonPersistentSkill extends Skill {
	private int delayround;
	private boolean ready;
	public NonPersistentSkill(){
		ready = true;
		delayround = 0;
	}
	@Override
	public void use() {
		// TODO Auto-generated method stub
		if(ready){
			useAction();
			ready = false;
			delayround = useDelayRound();
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		//update use delay time
		if(delayround == 0){
			ready = true;
		}else if(delayround >0){
			delayround--;
		}
		
	}
	protected abstract void useAction();
	protected abstract int useDelayRound();
}
