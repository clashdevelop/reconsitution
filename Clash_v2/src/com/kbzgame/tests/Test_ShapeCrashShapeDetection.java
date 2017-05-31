package com.kbzgame.tests;

import static org.junit.Assert.*;

import javax.swing.text.Position;

import org.junit.Test;

import com.kbzgame.physics.shape.Circle;
import com.kbzgame.physics.shape.Point;
import com.kbzgame.physics.shape.Polygon;
import com.kbzgame.physics.shape.Shape;
import com.kbzgame.physics.shape.ShapeCrashShapeDetection;

public class Test_ShapeCrashShapeDetection {

	@Test
	public void testCircle_1() {
		
		Shape shapeA=new Circle(new Point(0,0),5);
		Shape shapeB=new Circle(new Point(9,0),5);
		ShapeCrashShapeDetection 	shapeCrashShapeDetection=new ShapeCrashShapeDetection();
		System.out.println(shapeCrashShapeDetection.shapeCrashWithShape(shapeA, shapeB));
		assertEquals(true,shapeCrashShapeDetection.shapeCrashWithShape(shapeA, shapeB));
		System.out.println("testCircle_1"+shapeCrashShapeDetection.getBackVector(shapeA, shapeB));
		
		//fail("Not yet implemented");
	}
	@Test
	public void testCircle_2() {
		
		Shape shapeA=new Circle(new Point(3,4),3);
		Shape shapeB=new Circle(new Point(0,0),2.5);
		ShapeCrashShapeDetection 	shapeCrashShapeDetection=new ShapeCrashShapeDetection();
		System.out.println(shapeCrashShapeDetection.shapeCrashWithShape(shapeA, shapeB));
		assertEquals(true,shapeCrashShapeDetection.shapeCrashWithShape(shapeA, shapeB));
		System.out.println("testCircle_2"+shapeCrashShapeDetection.getBackVector(shapeA, shapeB));
		System.out.println(Math.tan(shapeCrashShapeDetection.getBackVector(shapeA, shapeB).getAngle()));
		
		//fail("Not yet implemented");
	}
	@Test
	public void testPolygon_1() {
		Point []a=new Point[]{new Point(2,1),new Point(2,4),new Point(4,4),new Point(4,1)};
		Point []b=new Point[]{new Point(1,4),new Point(2,6),new Point(3,5),new Point(3,2)};
		Shape shapeA=new Polygon(a,4);
		
		Shape shapeB=new Polygon(b,4);
		ShapeCrashShapeDetection 	shapeCrashShapeDetection=new ShapeCrashShapeDetection();
		System.out.println(shapeCrashShapeDetection.shapeCrashWithShape(shapeA, shapeB));
		assertEquals(true,shapeCrashShapeDetection.shapeCrashWithShape(shapeA, shapeB));
		System.out.println("testPolygon_1"+shapeCrashShapeDetection.getBackVector(shapeA, shapeB));
		System.out.println(Math.tan(shapeCrashShapeDetection.getBackVector(shapeA, shapeB).getAngle()));
		
		//fail("Not yet implemented");
	}
	@Test
	public void Polygon_2() {
		Point []a=new Point[]{new Point(0,0),new Point(0,2),new Point(2,2),new Point(2,0)};
		Point []b=new Point[]{new Point(1,1),new Point(1,3),new Point(3,3),new Point(4,2),new Point(3,1)};
		Shape shapeA=new Polygon(a,4);
		
		Shape shapeB=new Polygon(b,5);
		ShapeCrashShapeDetection 	shapeCrashShapeDetection=new ShapeCrashShapeDetection();
		System.out.println(shapeCrashShapeDetection.shapeCrashWithShape(shapeA, shapeB));
		assertEquals(true,shapeCrashShapeDetection.shapeCrashWithShape(shapeA, shapeB));
		System.out.println("Polygon_2"+shapeCrashShapeDetection.getBackVector(shapeA, shapeB));
		System.out.println(Math.tan(shapeCrashShapeDetection.getBackVector(shapeA, shapeB).getAngle()));
		
		//fail("Not yet implemented");
	}
	@Test
	public void testPolygon_3() {
		Point []a=new Point[]{new Point(4,1),new Point(4,4),new Point(6,4),new Point(6,1)};
		Point []b=new Point[]{new Point(1,4),new Point(2,6),new Point(3,5),new Point(3,2)};
		Shape shapeA=new Polygon(a,4);
		
		Shape shapeB=new Polygon(b,4);
		ShapeCrashShapeDetection 	shapeCrashShapeDetection=new ShapeCrashShapeDetection();
		System.out.println(shapeCrashShapeDetection.shapeCrashWithShape(shapeA, shapeB));
		assertEquals(false,shapeCrashShapeDetection.shapeCrashWithShape(shapeA, shapeB));
		System.out.println("testPolygon_3"+shapeCrashShapeDetection.getBackVector(shapeA, shapeB));
		System.out.println(Math.tan(shapeCrashShapeDetection.getBackVector(shapeA, shapeB).getAngle()));
		
		//fail("Not yet implemented");
	}
	@Test
	public void testPolygon_4() {
		Point []a=new Point[]{new Point(3,1),new Point(3,4),new Point(5,4),new Point(5,1)};
		Point []b=new Point[]{new Point(1,4),new Point(2,6),new Point(3,5),new Point(3,2)};
		Shape shapeA=new Polygon(a,4);
		
		Shape shapeB=new Polygon(b,4);
		ShapeCrashShapeDetection 	shapeCrashShapeDetection=new ShapeCrashShapeDetection();
		System.out.println(shapeCrashShapeDetection.shapeCrashWithShape(shapeA, shapeB));
		assertEquals(true,shapeCrashShapeDetection.shapeCrashWithShape(shapeA, shapeB));
		System.out.println("testPolygon_4"+shapeCrashShapeDetection.getBackVector(shapeA, shapeB));
		System.out.println(Math.tan(shapeCrashShapeDetection.getBackVector(shapeA, shapeB).getAngle()));
		
		//fail("Not yet implemented");
	}
	@Test
	public void testPolygon_5() {
		Point []a=new Point[]{new Point(2,0),new Point(1,5),new Point(3,5),new Point(5,3)};
		Point []b=new Point[]{new Point(5,3),new Point(7,4),new Point(9,1),new Point(7,0)};
		Shape shapeA=new Polygon(a,4);
		
		Shape shapeB=new Polygon(b,4);
		ShapeCrashShapeDetection 	shapeCrashShapeDetection=new ShapeCrashShapeDetection();
		System.out.println(shapeCrashShapeDetection.shapeCrashWithShape(shapeA, shapeB));
		assertEquals(true,shapeCrashShapeDetection.shapeCrashWithShape(shapeA, shapeB));
		System.out.println("testPolygon_5"+shapeCrashShapeDetection.getBackVector(shapeA, shapeB));
		System.out.println(Math.tan(shapeCrashShapeDetection.getBackVector(shapeA, shapeB).getAngle()));
		//fail("Not yet implemented");
	}
	//检测出了精度
	@Test 
	public void testPolygonAndCircle_1()
	{	
		
		Point []a=new Point[]{new Point(2,2),new Point(1,5),new Point(3,5),new Point(5,3),new Point(4,1),};
		Shape shapeA=new Polygon(a,5);
		Shape shapeB=new Circle(new Point(7,3),2);
		ShapeCrashShapeDetection 	shapeCrashShapeDetection=new ShapeCrashShapeDetection();
		System.out.println(shapeCrashShapeDetection.shapeCrashWithShape(shapeA, shapeB));
		assertEquals(true,shapeCrashShapeDetection.shapeCrashWithShape(shapeA, shapeB));
		System.out.println("testPolygonAndCircle_1"+shapeCrashShapeDetection.getBackVector(shapeA, shapeB));
	}
	@Test
	public void testPolygonAndCircle_2()
	{	
		
		Point []a=new Point[]{new Point(2,5),new Point(4,6),new Point(5,4),new Point(3,3)};
		Shape shapeA=new Polygon(a,4);
		Shape shapeB=new Circle(new Point(6,4),1);
		ShapeCrashShapeDetection 	shapeCrashShapeDetection=new ShapeCrashShapeDetection();
		System.out.println(shapeCrashShapeDetection.shapeCrashWithShape(shapeA, shapeB));
		assertEquals(true,shapeCrashShapeDetection.shapeCrashWithShape(shapeA, shapeB));
		System.out.println("testPolygonAndCircle_2"+shapeCrashShapeDetection.getBackVector(shapeA, shapeB));
	}
	@Test
	public void testPolygonAndCircle_3()
	{	
		
		Point []a=new Point[]{new Point(3,4),new Point(3,6),new Point(5,6),new Point(5,4)};
		Shape shapeA=new Polygon(a,4);
		Shape shapeB=new Circle(new Point(7,1),5);
		ShapeCrashShapeDetection 	shapeCrashShapeDetection=new ShapeCrashShapeDetection();
		System.out.println(shapeCrashShapeDetection.shapeCrashWithShape(shapeA, shapeB));
		assertEquals(true,shapeCrashShapeDetection.shapeCrashWithShape(shapeA, shapeB));
		System.out.println("testPolygonAndCircle_3"+shapeCrashShapeDetection.getBackVector(shapeA, shapeB));
		System.out.println(Math.tan(shapeCrashShapeDetection.getBackVector(shapeA, shapeB).getAngle()));
	}

	
}
