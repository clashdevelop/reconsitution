package com.kbzgame.physics.elements;

public class Timer extends PhysicsElements{
	private long delayTime = 0;
	private boolean timeover = true;
	private boolean notify = false;
	public Timer(){}
	public void setDelayTime(long delayTime){
		synchronized (this) {
			notify = false;
			timeover = false;
			this.delayTime = delayTime;
		}
	}
	public void run(long time){
		synchronized (this) {
			if(timeover){return;}
			delayTime -= time;
			if(delayTime <=0){
				timeover = true;
			}
		}
	}
	public boolean sendMessage(){
		boolean send = false;
		synchronized (this) {
			if(timeover&&!notify){
				send = true;
				notify = true;
			}
		}
		return send;
	}
	@Override
	public void addToWorld(PhysicsWorld world){
		world.addTimer(this);
	}
	@Override
	public void quitWorld(PhysicsWorld world){
		world.removeTimer(this);
	}
}
