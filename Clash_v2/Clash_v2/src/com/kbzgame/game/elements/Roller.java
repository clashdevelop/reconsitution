
package com.kbzgame.game.elements;

import com.kbzgame.game.skill.NonPersistentSkill;
import com.kbzgame.game.skill.PersistentSkill;
import com.kbzgame.game.skill.Skill;
import com.kbzgame.physics.elements.Body;
import com.kbzgame.physics.shape.Shape;
import com.kbzgame.physics.shape.Vector;

public class Roller extends Body {
	private static int count = 0;
	private final int id = count++;
	private String name; 
	private Vector mouseF = new Vector(0,0);
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
	public int getID(){
		return id;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void addMouseF(Vector mouseF){
		synchronized (this) {
			this.mouseF = mouseF;
		}
	}
	@Override 
	public void update(){
		skillOne.update();
		skillTwo.update();
		//System.out.println("mouseF "+mouseF);
		synchronized (this) {
			addF(mouseF);
		}
		super.update();
		//System.out.println("v= "+getV());
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
}
