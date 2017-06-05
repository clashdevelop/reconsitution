package com.kbzgame.physics.elements;

import java.util.Iterator;
import java.util.List;

import com.kbzgame.physics.event.PhysicsOutsideEvent;
import com.kbzgame.physics.shape.Shape;
import com.kbzgame.physics.shape.ShapeContainShapeDetection;
import com.kbzgame.physics.shape.TestResult;
import com.kbzgame.physics.shape.Vector;

public class BodyOutsideDetection {
	
	private ShapeContainShapeDetection shapeDetection = new ShapeContainShapeDetection();
	public void testPolygonBoder(Shape borderShape,List<Body> bodys,Contact contact){
		Iterator<Body> bodyIter = bodys.iterator();
		
		while(bodyIter.hasNext()){
			Body body = bodyIter.next();
			TestResult result = shapeDetection.shapeContainShapeWithBack(borderShape,body.getShape());
			if(!result.isHappened()){
				Vector backVector = result.getBackVector();
				if(body.isMovable()){
					body.changePositionBy(backVector.getComponentX(),backVector.getComponentY());
				}
				PhysicsOutsideEvent event = new PhysicsOutsideEvent(body);
				contact.sendPhysicsEvent(event);
				}
			
		}
	}
}
