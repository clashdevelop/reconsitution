package tests;

import com.kbzgame.game.elements.JsonHelper;
import com.kbzgame.physics.shape.Point;
import com.kbzgame.physics.shape.Shape;
import com.kbzgame.physics.shape.ShapeFactory;

public class JsonHelperTest {
	public static void main(String[] args){ 
		Shape circle = ShapeFactory.createCircle(5, 5, 5);
		Point points[] = new Point[]{
				new Point(1,1),
				new Point(1,3),
				new Point(3,3),
				new Point(3,1)
		};
		Shape polygon = ShapeFactory.createPolygon(points, 4);
		System.out.println("circle:"+circle);
		System.out.println("polygon:"+polygon);
		
		JsonHelper.changeShapeToJson(""+circle);
		JsonHelper.changeShapeToJson(""+polygon);
	}
}
