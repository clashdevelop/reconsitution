package com.kbzgame.game.elements;

import static org.junit.Assert.*;

import org.junit.Test;

import com.kbzgame.physics.shape.Point;
import com.kbzgame.physics.shape.Shape;
import com.kbzgame.physics.shape.ShapeFactory;

public class Test_rollerFactory {

	@Test
	public void test() {
//		fail("Not yet implemented");
		Point[] points = new Point[]{new Point(0,0),new Point(0,800),new Point(800,800),new Point(800,0)};
		Shape borderShape = ShapeFactory.createPolygon(points, 4);
		RollerFactory.createPolygonRoller("zhangsan",borderShape);
		System.out.println();
	}

}
