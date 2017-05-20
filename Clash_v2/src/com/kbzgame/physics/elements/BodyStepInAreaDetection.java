package com.kbzgame.physics.elements;

import java.util.Iterator;
import java.util.List;

import com.kbzgame.physics.shape.ShapeContainShapeDetection;

public class BodyStepInAreaDetection {
	private ShapeContainShapeDetection shapeDetection= new ShapeContainShapeDetection();
	public void  test(List<Body> bodyList,List<Area> areaList){
		Iterator<Area> areaIterator= areaList.iterator();
		while(areaIterator.hasNext()){
			Area area = areaIterator.next();
			Iterator<Body> bodyIterator= bodyList.iterator();
			while(bodyIterator.hasNext()){
				Body body = bodyIterator.next();
				if(shapeDetection.shapeContainShape(area.getShape(),body.getShape())){
					//do some thing
				}
			}
		}
	}
	public void setShapeContainShapeDetection(ShapeContainShapeDetection shapeDetection){
		this.shapeDetection = shapeDetection;
	}
}
