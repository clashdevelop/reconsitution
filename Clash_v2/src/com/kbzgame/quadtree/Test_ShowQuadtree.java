package com.kbzgame.quadtree;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.kbzgame.physics.shape.Circle;
import com.kbzgame.physics.shape.EdgeRect;
import com.kbzgame.physics.shape.Point;
import com.kbzgame.physics.shape.Shape;

public class Test_ShowQuadtree {
	Quadtree quad = new Quadtree(0, new EdgeRect(0,0,600,600));
	List<Shape> allObjects=new ArrayList<Shape>();
	@Test
	public void test() {
		//fail("Not yet implemented");
//		Circle []circle=new Circle[]{new Circle(new Point(30,30),10),new Circle(new Point(70,30),10),
//			new Circle(new Point(500,30),10),new Circle(new Point(500,580),10),new Circle(new Point(30,70),10)};
		Circle []circle=new Circle[20];
		double x=560,y=560;
		double x1=0,y1=560;
		double x2=25,y2=25;
		for(int i=0;i<circle.length;i++)
		{
			circle[i]=new Circle(new Point(x2,y2),3);
			x2+=25;
			y2+=25;
			allObjects.add(circle[i]);
		}
		for(int i=0;i<circle.length;i++)
		{
			circle[i]=new Circle(new Point(x1,y1),Math.random()*3);
			x1+=25;
			y1-=25;
			allObjects.add(circle[i]);
		}
		//for(int i=0;i<circle.length;i++)
			//allObjects.add(circle[i]);		
		for (int i = 0; i < allObjects.size(); i++) {
			  quad.insert(allObjects.get(i));
			  //System.out.println("insert the quad !!");
			}
		quad.show();
		System.out.println("fuck!!!");
		
	}

}
