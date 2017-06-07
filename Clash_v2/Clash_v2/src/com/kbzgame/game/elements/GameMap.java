package com.kbzgame.game.elements;


import java.util.Iterator;
import java.util.List;

import com.kbzgame.physics.elements.Area;
import com.kbzgame.physics.elements.Body;
import com.kbzgame.physics.elements.PhysicsWorld;
import com.kbzgame.physics.shape.Point;
import com.kbzgame.physics.shape.Shape;
import com.kbzgame.physics.shape.ShapeFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GameMap extends PhysicsWorld{
	private RulerContact contact;
	public GameMap(){
		contact = new RulerContact();
		//add some game rulers
		//setContact(contact);
		Point[] points = new Point[]{new Point(0,0),new Point(0,200),new Point(200,200),new Point(200,0)};
		Shape shape = ShapeFactory.createPolygon(points, 4);
		setBorderShape(shape);
		open();
		//Point[] blockpoints = new Point[]{new Point(5,1),new Point(6,8),new Point(8,5),new Point(7,2)};
		//Shape blockshape = ShapeFactory.createPolygon(blockpoints, 4);
		//Block block = new Block(blockshape);
		//block.addToWorld(this);
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
				rollerJson.put("id",roller.getID());
				JSONObject shapeJson = JsonHelper.changeShapeToJson(roller.getShape()+"");
				rollerJson.put("shape",shapeJson);
				rollers.put(rollerJson);
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
		sendJson.put("blocks", blocks);
		sendJson.put("traps", traps);
		return sendJson.toString();

	}
	
}
