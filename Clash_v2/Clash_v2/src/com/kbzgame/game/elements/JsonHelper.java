package com.kbzgame.game.elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonHelper {
	public static JSONObject changeShapeToJson(String arg){
		Pattern circlePattern = Pattern.compile("^c.+c$");
		Matcher m1 = circlePattern.matcher(arg);
		if(m1.find()){
			//System.out.println("find circle:"+arg);
			Pattern doublePattern = Pattern.compile("\\d+\\.\\d+");
			Matcher rm = doublePattern.matcher(arg);
			double[] info = new double[3];
			int i =  0;
			while(rm.find()){
				if(i>2)break;
				//System.out.println(rm.group());
				info[i++] = Double.parseDouble(rm.group());
			}
			JSONObject centerJson = new JSONObject();
			centerJson.put("x",info[0]);
			centerJson.put("y",info[1]);
			JSONObject shapeJson = new JSONObject();
			shapeJson.put("type","circle");
			shapeJson.put("center",centerJson);
			shapeJson.put("radius",info[2]);
			//System.out.println("shapeJson"+shapeJson.toString());
			return shapeJson;
		}
		
		Pattern polyogonPattern = Pattern.compile("^p.+p$");
		Matcher m2 = polyogonPattern.matcher(arg);
		if(m2.find()){
			//System.out.println("find polygon:"+arg);
			JSONArray points = new JSONArray();
			
			Pattern doublePattern = Pattern.compile("\\d+\\.\\d+");
			Matcher rm = doublePattern.matcher(arg);
			while(rm.find()){
				//System.out.println(rm.group());
				JSONObject point = new JSONObject();
				point.put("x", rm.group());
				if(rm.find()){
					point.put("y", rm.group());
					points.put(point);
				}
			}
			JSONObject shapeJson = new JSONObject();
			shapeJson.put("type","polygon");
			shapeJson.put("points",points);
			//System.out.println("shapeJson"+shapeJson.toString());
			return shapeJson;
			
		}
		return null;
	}
	
	public static JSONObject getRollerId(int id){
		JSONObject sendJson = new JSONObject();
		
		sendJson.put("type", "localId");
		sendJson.put("content", id);
		
		return sendJson;
	}
	
}
