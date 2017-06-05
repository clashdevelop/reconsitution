package com.kbzgame.game.elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonHelper {
	public static String changeShapeToJson(String arg){
		Pattern circlePattern = Pattern.compile("^c.+c$");
		Matcher m1 = circlePattern.matcher(arg);
		if(m1.find()){
			System.out.println("find circle:"+arg);
			Pattern doublePattern = Pattern.compile("\\d+\\.\\d+");
			Matcher rm = doublePattern.matcher(arg);
			while(rm.find()){
				System.out.println(rm.group());
			}
		}
		Pattern polyogonPattern = Pattern.compile("^p.+p$");
		Matcher m2 = polyogonPattern.matcher(arg);
		if(m2.find()){
			System.out.println("find polygon:"+arg);
			Pattern doublePattern = Pattern.compile("\\d+\\.\\d+");
			Matcher rm = doublePattern.matcher(arg);
			while(rm.find()){
				System.out.println(rm.group());
			}
		}
		return "";
	}
	
}
