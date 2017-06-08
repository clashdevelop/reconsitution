
package com.kbzgame.game.elements;

import com.kbzgame.game.event.RollerCrashRoller;
import com.kbzgame.game.event.RollerStepInTrap;
import com.kbzgame.game.skill.NonPersistentSkill;
import com.kbzgame.game.skill.PersistentSkill;
import com.kbzgame.game.skill.Skill;
import com.kbzgame.physics.elements.Body;
import com.kbzgame.physics.elements.PhysicsWorld;
import com.kbzgame.physics.event.EventListener;
import com.kbzgame.physics.event.PhysicsEvent;
import com.kbzgame.physics.shape.Shape;
import com.kbzgame.physics.shape.Vector;
import com.kbzgame.socket.GameSocket;

public class Roller extends Body {
	private String name; 
	private Vector mouseF = new Vector(0,0);
	private int score = 0;
	private Roller crasher;
	private int crashRound;
	public Skill skillOne = flashSkill();
	public Skill skillTwo = clashSkill();
	public Roller(Shape shape){
		super(shape);
		setMovable(true);
		setMaxVSize(0.6);
		setM(4);
		setf_factor(0.001);
		setk_factor(1);
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setScore(int score){
		synchronized (this) {
			this.score = score;
		}
	}
	public void addScore(int added){
		synchronized (this) {
			score += added;
		}
	}
	public int getScore(){
		synchronized (this) {
			return score;
		}
	}
	public void setCrasher(Roller crasher){
		synchronized (this) {
			this.crasher = crasher;
		}
	}
	public Roller getCrasher(){
		synchronized (this) {
			return crasher;
		}
	}
	public void setCrashRound(int crashRound){
		synchronized (this) {
			this.crashRound = crashRound;
		}
	}
	public int getCrashRound(){
		synchronized (this) {
			return crashRound;
		}
	}
	public void addMouseF(Vector mouseF){
		synchronized (this) {
			this.mouseF = mouseF;
		}
	}
	@Override public void update(){
		skillOne.update();
		skillTwo.update();
		//System.out.println("mouseF "+mouseF);
		synchronized (this) {
			crashRound++;
			if(crashRound>100){
				crashRound=0;
				crasher = null;
			}
			addF(mouseF);
		}
		
		super.update();
		//System.out.println("v= "+getV());
	} 
	@Override
	public void addToWorld(PhysicsWorld world){
		super.addToWorld(world);
		getWorld().registerEventListener(RollerStepInTrap.class,this,new StepInTrapListener());
		getWorld().registerEventListener(RollerCrashRoller.class,this,new RollerCrashRollerListener());
		GameSocket.addName(name);
	}
	@Override 
	public void quitWorld(){
		GameSocket.deleteName(name);//Thread safe
		getWorld().unregisterEventListener(RollerStepInTrap.class,this,new StepInTrapListener());
		getWorld().unregisterEventListener(RollerCrashRoller.class,this,new RollerCrashRollerListener());
		super.quitWorld();
	}
	public Skill flashSkill(){
		return new NonPersistentSkill(){

			@Override
			protected void useAction() {
				// TODO Auto-generated method stub
				Vector flashVector = new Vector(30,getV().getAngle());
				changePositionBy(flashVector.getComponentX(),flashVector.getComponentY());
			}

			@Override
			protected int useDelayRound() {
				// TODO Auto-generated method stub
				return (int)(5000/GameMap.roundDelayTime_MS);
			}
			
		};
	}
	public Skill clashSkill(){
		return new PersistentSkill(){
			private double maxV;
			private double preMaxV;
			@Override
			protected void useAction() {
				// TODO Auto-generated method stub
				System.out.println("clash used");
				preMaxV = getMaxVSize();
				maxV = 2;//world.maxVSize;
				setMaxVSize(maxV);
				Vector v = new Vector(maxV,getV().getAngle());
				setV(v);
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
				setMaxVSize(maxV);
			}

			@Override
			protected void endAction() {
				// TODO Auto-generated method stub
				setMaxVSize(preMaxV);
			}

			@Override
			protected int useDelayRound() {
				// TODO Auto-generated method stub
				return (int)(2000/GameMap.roundDelayTime_MS);
			}};
	}
	private class StepInTrapListener implements EventListener{

		@Override
		public void onEvent(PhysicsEvent event) {
			// TODO Auto-generated method stub
			//System.out.println("Listener use");
			//changePositionBy(60, 60);
			//quitWorld();
			Roller crasher = getCrasher();
			if(crasher!=null){
				crasher.addScore(10);
				System.out.println(crasher.getName()+":"+crasher.getScore());

			}
		}
		
	}
	private class RollerCrashRollerListener implements EventListener{

		@Override
		public void onEvent(PhysicsEvent event) {
			// TODO Auto-generated method stub
			//System.out.println("Listener use");
			//changePositionBy(60, 60);
			if(RollerCrashRoller.class.isInstance(event)){
				Roller rollerA =  (Roller)event.getElementA();
				Roller rollerB =  (Roller)event.getElementB();
				if(rollerA != Roller.this){
					setCrasher(rollerA);
					setCrashRound(0);
				}else{
					setCrasher(rollerB);
					setCrashRound(0);
				}
			}
		}
		
	}
}
