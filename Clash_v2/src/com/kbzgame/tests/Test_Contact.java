package com.kbzgame.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.kbzgame.physics.elements.Body;
import com.kbzgame.physics.elements.BodyCrashWithBodyDetection;
import com.kbzgame.physics.elements.Contact;
import com.kbzgame.physics.elements.T_Contact;
import com.kbzgame.physics.shape.Circle;
import com.kbzgame.physics.shape.Point;
import com.kbzgame.physics.shape.ShapeCrashShapeDetection;
import com.kbzgame.physics.shape.T_ShapeCrashShapeDetection;



public class Test_Contact {

	@Test
	public void T_test() {
		
		List<Body> bodyList=new ArrayList<Body>();
		bodyList.add(new Body(new Circle(new Point(4,4),5)));
		bodyList.add(new Body(new Circle(new Point(5,4),5)));
		//fail("Not yet implemented");
		Contact A=new T_Contact();
		BodyCrashWithBodyDetection bodyCrashWithBodyDetection=new BodyCrashWithBodyDetection(A);
		bodyCrashWithBodyDetection.setShapeCrashShapeDetection(new T_ShapeCrashShapeDetection());
		bodyCrashWithBodyDetection.test(bodyList);
	}
	@Test
	public void test() {
		
		List<Body> bodyList=new ArrayList<Body>();
		bodyList.add(new Body(new Circle(new Point(4,4),5)));
		bodyList.add(new Body(new Circle(new Point(5,4),5)));
		//fail("Not yet implemented");
		Contact A=new T_Contact();
		BodyCrashWithBodyDetection bodyCrashWithBodyDetection=new BodyCrashWithBodyDetection(A);
		bodyCrashWithBodyDetection.setShapeCrashShapeDetection(new ShapeCrashShapeDetection());
		bodyCrashWithBodyDetection.test(bodyList);
	}

}
