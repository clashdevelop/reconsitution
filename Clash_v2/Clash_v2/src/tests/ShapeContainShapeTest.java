package tests;

import com.kbzgame.physics.shape.Point;
import com.kbzgame.physics.shape.Shape;
import com.kbzgame.physics.shape.ShapeContainShapeDetection;
import com.kbzgame.physics.shape.ShapeFactory;
import com.kbzgame.physics.shape.TestResult;

public class ShapeContainShapeTest {
	public static void main(String[] args){
		Shape shapeA = ShapeFactory.createCircle(4, 0, 1);
		Point[] points1 = new Point[]{
				new Point(0,0),
				new Point(0,4),
				new Point(4,4),
				new Point(4,0)
		};
		Shape shapeB = ShapeFactory.createPolygon(points1, 4);
		Point[] points2 = new Point[]{
				new Point(3,0),
				new Point(3,1),
				new Point(5,1),
				new Point(5,0)
		};
		Shape shapeC = ShapeFactory.createPolygon(points2, 4);
		
		ShapeContainShapeDetection detection = new ShapeContainShapeDetection();
		boolean bResult = detection.shapeContainShape(shapeB, shapeA);
		System.out.println(bResult);
		TestResult result1 = detection.shapeContainShapeWithBack(shapeB, shapeA);
		System.out.println(result1.isHappened());
		System.out.println(result1.getBackVector());
		TestResult result2 = detection.shapeContainShapeWithBack(shapeB, shapeC);
		System.out.println(result2.isHappened());
		System.out.println(result2.getBackVector());
	}
}
