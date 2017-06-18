package com.kbzgame.game.elements;


import java.util.Iterator;
import java.util.List;

import com.kbzgame.game.event.RollerCrashRoller;
import com.kbzgame.game.event.RollerStepInTrap;
import com.kbzgame.physics.elements.Area;
import com.kbzgame.physics.elements.Body;
import com.kbzgame.physics.elements.PhysicsWorld;
import com.kbzgame.physics.event.PhysicsBodyCrashEvent;
import com.kbzgame.physics.event.PhysicsInAreaEvent;
import com.kbzgame.physics.shape.Point;
import com.kbzgame.physics.shape.Shape;
import com.kbzgame.physics.shape.ShapeFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GameMap extends PhysicsWorld{
	private RuleContact contact;
	public GameMap(){
		contact = new RuleContact();
		//add some game rulers
		contact.addGameRuler(new Rule(Roller.class,Trap.class,PhysicsInAreaEvent.class,RollerStepInTrap.class));
		contact.addGameRuler(new Rule(Roller.class,Roller.class,PhysicsBodyCrashEvent.class,RollerCrashRoller.class));

		setContact(contact);
		Point[] points = new Point[]{new Point(0,150),new Point(0,300),new Point(225,450),new Point(450,300),new Point(450,150),new Point(150,0)};
		Shape shape = ShapeFactory.createPolygon(points, 6);
		setBorderShape(shape);

		open();
		Point[] blockpoints = new Point[]{new Point(150,150),new Point(150,160),new Point(160,160),new Point(160,150)};
		Shape blockshape = ShapeFactory.createPolygon(blockpoints, 4);

		Block block = new Block(blockshape);
		block.addToWorld(this);

		Point[] blockpoints1 = new Point[]{new Point(100,350),new Point(100,370),new Point(120,360),new Point(120,355)};
		Shape blockshape1 = ShapeFactory.createPolygon(blockpoints1, 4);

		Block block1 = new Block(blockshape1);
		block1.addToWorld(this);

//		Point[] blockpoints2 = new Point[]{new Point(400,350),new Point(400,360),new Point(430,370),new Point(430,350),new Point(415,340)};
//		Shape blockshape2 = ShapeFactory.createPolygon(blockpoints2, 5);
//
//		Block block2 = new Block(blockshape2);
//		block2.addToWorld(this);
		//this.registerEventListener(PhysicsBodyCrashEvent.class,block, new T_Listener("Block"));

//		Point[] trappoints = new Point[]{new Point(10,10),new Point(10,50),new Point(50,60),new Point(60,10)};
//		Shape trapshape = ShapeFactory.createPolygon(trappoints, 4);
//		Trap trap = new Trap(trapshape);
//		trap.addToWorld(this);

		Point[] trappoints1 = new Point[]{new Point(175,175),new Point(175,275),new Point(275,275),new Point(275,175)};
		Shape trapshape1 = ShapeFactory.createPolygon(trappoints1, 4);
		Trap trap1 = new Trap(trapshape1);
		trap1.addToWorld(this);
//		Point[] trappoints2 = new Point[]{new Point(100,250),new Point(100,300),new Point(150,300),new Point(150,250)};
//		Shape trapshape2 = ShapeFactory.createPolygon(trappoints2, 4);
//		Trap trap2 = new Trap(trapshape2);
//		trap2.addToWorld(this);
//		Point[] trappoints3 = new Point[]{new Point(300,400),new Point(250,450),new Point(300,500),new Point(350,450)};
//		Shape trapshape3 = ShapeFactory.createPolygon(trappoints3, 4);
//		Trap trap3 = new Trap(trapshape3);
//		trap3.addToWorld(this);
//		Point[] trappoints4 = new Point[]{new Point(400,250),new Point(400,300),new Point(450,300),new Point(450,250)};
//		Shape trapshape4 = ShapeFactory.createPolygon(trappoints4, 4);
//		Trap trap4 = new Trap(trapshape4);
//		trap4.addToWorld(this);
//		Point[] trappoints5 = new Point[]{new Point(300,200),new Point(250,150),new Point(300,100),new Point(350,150)};
//		Shape trapshape5 = ShapeFactory.createPolygon(trappoints5, 4);
//		Trap trap5 = new Trap(trapshape5);
//		trap5.addToWorld(this);

		//this.registerEventListener(PhysicsInAreaEvent.class,trap, new T_Listener("Trap"));
	}
	public String getRollerMessage(){
		List<Body> bodys = getBodys();
		Iterator<Body> bodyIter = bodys.iterator();
		
		JSONArray rollers = new JSONArray();
		while(bodyIter.hasNext()){
			Body body = bodyIter.next();
			if(Roller.class.isInstance(body)){
				Roller roller = (Roller)body;
				JSONObject rollerJson = new JSONObject();
				rollerJson.put("type", "player");
				rollerJson.put("id",roller.getName());
				rollerJson.put("score",roller.getScore());
				rollerJson.put("skill",roller.getCurrentSkill()==null?"noskill":roller.getCurrentSkill().getSkillName());
				JSONObject shapeJson = JsonHelper.changeShapeToJson(roller.getShape()+"");
				rollerJson.put("shape",shapeJson);
				rollers.put(rollerJson);
				if(roller.getCurrentSkill()!=null){
					System.out.println("skillname ="+roller.getCurrentSkill().getSkillName());
				}
			}
		}
		JSONObject sendJson = new JSONObject();
		sendJson.put("type", "movableJson");
		sendJson.put("content", rollers);
		return sendJson.toString();
	}
	public String getMapMessage(){
		List<Body> bodys = getBodys();
		Iterator<Body> bodyIter = bodys.iterator();	
		JSONArray blocks = new JSONArray();
		while(bodyIter.hasNext()){
			Body body = bodyIter.next();
			if(Block.class.isInstance(body)){
				Block block = (Block)body;
				JSONObject blockJson = new JSONObject();
				JSONObject shapeJson = JsonHelper.changeShapeToJson(block.getShape()+"");
				blockJson.put("shape",shapeJson);
				blocks.put(blockJson);
			}
		}

		List<Area> areas = getAreas();
		Iterator<Area> areaIter = areas.iterator();	
		JSONArray traps = new JSONArray();
		while(areaIter.hasNext()){
			Area area = areaIter.next();
			if(Trap.class.isInstance(area)){
				Trap trap = (Trap)area;
				JSONObject trapJson = new JSONObject();
				JSONObject shapeJson = JsonHelper.changeShapeToJson(trap.getShape()+"");
				trapJson.put("shape",shapeJson);
				traps.put(trapJson);
			}
		}
		
		
		JSONObject sendJson = new JSONObject();
		sendJson.put("type", "staticItems");
		sendJson.put("borderShape", JsonHelper.changeShapeToJson(getBorderShapeStr()));
		//System.out.println(JsonHelper.changeShapeToJson(getBorderShapeStr()));
		sendJson.put("blocks", blocks);
		sendJson.put("traps", traps);
		return sendJson.toString();

	}
	
}
