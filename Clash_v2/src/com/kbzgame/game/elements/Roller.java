
package com.kbzgame.game.elements;

import com.kbzgame.game.skill.NonPersistentSkill;
import com.kbzgame.game.skill.PersistentSkill;
import com.kbzgame.game.skill.Skill;
import com.kbzgame.physics.elements.Body;
import com.kbzgame.physics.shape.Shape;
import com.kbzgame.physics.shape.Vector;

public class Roller extends Body implements JsonSender{
	private static int count = 0;
	public final int id = count++;
	private String name; 
	public Skill skillOne;
	public Skill skillTwo;
	public Roller(Shape shape){
		super(shape);
		setMovable(true);
		setMaxVSize(2);
		setM(1);
		setf_factor(0.2);
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void addMouseF(Vector mouseF){
		addF(mouseF);
	}
	@Override public String toJsonString() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override public void update(){
		skillOne.update();
		skillTwo.update();
		super.update();
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
				return (int)(5000/world.getRoundDelayTime_MS());
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
				preMaxV = getMaxVSize();
				maxV = world.maxVSize;
				setMaxVSize(maxV);
				Vector v = new Vector(maxV,getV().getAngle());
				setV(v);
			}

			@Override
			protected int keepActionRound() {
				// TODO Auto-generated method stub
				return (int)(1000/world.getRoundDelayTime_MS());
			}

			@Override
			protected void keepAction() {
				// TODO Auto-generated method stub
				if(maxV>preMaxV){
					maxV -= 5;
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
				return (int)(5000/world.getRoundDelayTime_MS());
			}};
	}
}
