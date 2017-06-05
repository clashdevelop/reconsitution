package com.kbzgame.quadtree;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.kbzgame.physics.elements.Body;
import com.kbzgame.physics.elements.BodyFactory;
import com.kbzgame.physics.shape.Circle;
import com.kbzgame.physics.shape.EdgeRect;
import com.kbzgame.physics.shape.Point;
import com.kbzgame.physics.shape.Shape;
import com.kbzgame.physics.shape.ShapeCrashShapeDetection;

public class Test_ShowQuadtree {
	Quadtree quad = new Quadtree(0, new EdgeRect(0,1200,1200,0));
	List<Body> allObjects=new ArrayList<Body>();
	ShapeCrashShapeDetection 	shapeCrashShapeDetection=ShapeCrashShapeDetection.create();
	@Test
	public void test() {
		double x=1160,y=1160;
		double x1=0,y1=1160;
		double x2=25,y2=25;
		for(int i=0;i<40;i++)
		{
			x2+=25;
			y2+=25;
			allObjects.add(BodyFactory.createBody(x2, y2, 18));
		}
		for(int i=0;i<40;i++)
		{
			x1+=25;
			y1-=25;
			allObjects.add(BodyFactory.createBody(x1, y1, 18));
		}
		for (int i = 0; i < allObjects.size(); i++) {
			  quad.insert(allObjects.get(i));
			}
		
		
		int count=0;
		int has=0;
		int once=0;
		List<Body> returnObjects = new ArrayList<Body>();
		for (int i = 0; i < allObjects.size(); i++) {
		  returnObjects.clear();
		  returnObjects=quad.retrieve(allObjects.get(i));
		  
		  returnObjects.remove(allObjects.get(i));
		  for (int c = 0; c < returnObjects.size(); c++) {
		    // Run collision detection algorithm between objects
			//  System.out.println("just for trying !!");
			  //System.out.println(shapeCrashShapeDetection.shapeCrashWithShape(allObjects.get(i), returnObjects.get(c)));
			  //System.out.println(shapeCrashShapeDetection.getBackVector(allObjects.get(i), returnObjects.get(c))); ;
			  if(shapeCrashShapeDetection.shapeCrashWithShape(allObjects.get(i).getShape(), returnObjects.get(c).getShape()).isHappened())
				  {has++;
				  once++;
				  }
			  
			  count++;
		  }
		  System.out.println(once);
		  once=0;
		
		}System.out.println(count);
		System.out.println("valid"+has);
		
	System.out.println(has);
		System.out.println(count);
	}
		

	}


