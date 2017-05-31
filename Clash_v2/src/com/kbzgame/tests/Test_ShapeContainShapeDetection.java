package com.kbzgame.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.kbzgame.physics.shape.Circle;
import com.kbzgame.physics.shape.Point;
import com.kbzgame.physics.shape.Polygon;
import com.kbzgame.physics.shape.Shape;
import com.kbzgame.physics.shape.ShapeContainShapeDetection;

public class Test_ShapeContainShapeDetection {
	@Test
	public void testPolygonAndPolygon_1()
	{	
		
		Point []a=new Point[]{new Point(2,3),new Point(5,7),new Point(8,5),new Point(9,2),new Point(4,1)};
		Point []b=new Point[]{new Point(4,4),new Point(5,5),new Point(7,4),new Point(6,2)};
		Shape shapeA=new Polygon(a,5);
		Shape shapeB=new Polygon(b,4);
		ShapeContainShapeDetection 	shapeContainShapeDetection=new ShapeContainShapeDetection();
		System.out.println("testPolygonAndPolygon_1"+shapeContainShapeDetection.shapeContainShape(shapeA, shapeB));
		assertEquals(true,shapeContainShapeDetection.shapeContainShape(shapeA, shapeB));
	}
	@Test
	public void testPolygonAndPolygon_2()
	{	
		
		Point []a=new Point[]{new Point(2,3),new Point(5,7),new Point(8,5),new Point(9,2),new Point(4,1)};
		Point []b=new Point[]{new Point(2,4),new Point(2,6),new Point(4,5),new Point(4,3)};
		Shape shapeA=new Polygon(a,5);
		Shape shapeB=new Polygon(b,4);
		ShapeContainShapeDetection 	shapeContainShapeDetection=new ShapeContainShapeDetection();
		System.out.println("testPolygonAndPolygon_2"+shapeContainShapeDetection.shapeContainShape(shapeA, shapeB));
		assertEquals(false,shapeContainShapeDetection.shapeContainShape(shapeA, shapeB));
	}
	@Test
	public void testPolygonAndCircle_3()
	{	
		
		Point []a=new Point[]{new Point(2,3),new Point(5,7),new Point(8,5),new Point(9,2),new Point(4,1)};
		Shape shapeA=new Polygon(a,5);
		Shape shapeB=new Circle(new Point(5,4),1);
		ShapeContainShapeDetection 	shapeContainShapeDetection=new ShapeContainShapeDetection();
		System.out.println("testPolygonAndPolygon_3"+shapeContainShapeDetection.shapeContainShape(shapeA, shapeB));
		assertEquals(true,shapeContainShapeDetection.shapeContainShape(shapeA, shapeB));
	}
	@Test
	public void testPolygonAndCircle_4()
	{	
		
		Point []a=new Point[]{new Point(2,3),new Point(5,7),new Point(8,5),new Point(9,2),new Point(4,1)};
		Shape shapeA=new Polygon(a,5);
		Shape shapeB=new Circle(new Point(3,5),2);
		ShapeContainShapeDetection 	shapeContainShapeDetection=new ShapeContainShapeDetection();
		System.out.println("testPolygonAndCircle_4"+shapeContainShapeDetection.shapeContainShape(shapeA, shapeB));
		assertEquals(false,shapeContainShapeDetection.shapeContainShape(shapeA, shapeB));
	}

}
