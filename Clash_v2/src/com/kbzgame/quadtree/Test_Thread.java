package com.kbzgame.quadtree;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import com.kbzgame.physics.shape.Circle;
import com.kbzgame.physics.shape.EdgeRect;
import com.kbzgame.physics.shape.Point;
import com.kbzgame.physics.shape.Shape;
import com.kbzgame.physics.shape.ShapeCrashShapeDetection;

public class Test_Thread {
	private ExecutorService taskManager = Executors.newCachedThreadPool();
	Quadtree quad = new Quadtree(0, new EdgeRect(0,0,600,600));
	List<Shape> allObjects=new ArrayList<Shape>();
	ShapeCrashShapeDetection 	shapeCrashShapeDetection=new ShapeCrashShapeDetection();
	@Test
	public void test() {
		//fail("Not yet implemented");
		Circle []circle=new Circle[20];
		double x=560,y=560;
		double x1=0,y1=560;
		double x2=25,y2=25;
		allObjects.clear();
		for(int i=0;i<circle.length;i++)
		{
			circle[i]=new Circle(new Point(x2,y2),18);
			x2+=25;
			y2+=25;
			allObjects.add(circle[i]);
		}
		
		
		for(int i=0;i<circle.length;i++)
		{
			circle[i]=new Circle(new Point(x1,y1),18);
			x1+=25;
			y1-=25;
			allObjects.add(circle[i]);
		}
		taskManager.execute(new UpdateTask());
		while(true)
		{	
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private class UpdateTask implements Runnable{

//		@Override
		public void run() {
			while(true){
				quad.clear();
			for (int i = 0; i < allObjects.size(); i++) {
				  quad.insert(allObjects.get(i));
				  System.out.println("insert the quad !!");
				}
			quad.show();
		int count=0;
		int has=0;
		List<Shape> returnObjects = new ArrayList<Shape>();
		for (int i = 0; i < allObjects.size(); i++) {
		  returnObjects.clear();
		  quad.retrieve(returnObjects, allObjects.get(i));
		  returnObjects.remove(allObjects.get(i));
		  for (int c = 0; c < returnObjects.size(); c++) {
		    // Run collision detection algorithm between objects
			//  System.out.println("just for trying !!");
			  System.out.println(shapeCrashShapeDetection.shapeCrashWithShape(allObjects.get(i), returnObjects.get(c)));
			  System.out.println(shapeCrashShapeDetection.getBackVector(allObjects.get(i), returnObjects.get(c))); ;
			  if(shapeCrashShapeDetection.shapeCrashWithShape(allObjects.get(i), returnObjects.get(c)))has++;
			  count++;
		  }
		
		}System.out.println(count);
		System.out.println("valid"+has);
		//Thread.currentThread();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
			
			
			
			
		
	
	}
	}
	}


