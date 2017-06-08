package com.kbzgame.game.command;


import com.kbzgame.game.elements.Roller;

import net.sf.json.JSONObject;

public class CommandFactory {
	public static Command creatCommand(String commandMessage,Roller reciver){
		JSONObject json = JSONObject.fromString(commandMessage);
		String type = json.getString("type");
		if("mouse".equals(type)){
			JSONObject message = JSONObject.fromString(json.getString("content"));
			double x = message.getDouble("x");
			double y = message.getDouble("y");
			MouseCommand command = new MouseCommand(reciver,x,y);
			//System.out.println("mouseCommand"+command);
			return command;
		}
		if("keyboard".equals(type)){
			JSONObject message = JSONObject.fromString(json.getString("content"));
			int keyValue = message.getInt("key");
			//通过values()得到数组
			Key pressKey = Key.values()[keyValue];
			//System.out.println("mouseCommand"+command);
			KeyCommand command = new KeyCommand(pressKey,reciver);
			return command;
		}
		return null;
	}
}
