package com.kbzgame.quadtree;

import static org.junit.Assert.*;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;


import com.kbzgame.physics.shape.Circle;
import com.kbzgame.physics.shape.EdgeRect;
import com.kbzgame.physics.shape.Point;
import com.kbzgame.physics.shape.Shape;
import com.kbzgame.physics.shape.ShapeCrashShapeDetection;
public class Test_Quadtree {
	private ExecutorService taskManager = Executors.newCachedThreadPool();
	Quadtree quad = new Quadtree(0, new EdgeRect(0,0,1200,1200));
	List<Shape> allObjects=new ArrayList<Shape>();
	
	ShapeCrashShapeDetection 	shapeCrashShapeDetection=new ShapeCrashShapeDetection();
	
	@Test
	public void test() throws InterruptedException {
		//fail("Not yet implemented");
		
		
		Circle []circle=new Circle[200];
		double x=1160,y=1160;
		double x1=0,y1=1160;
		double x2=25,y2=25;
//		for(int i=0;i<circle.length;i++)
//		{
//			circle[i]=new Circle(new Point(x,y),Math.random()*50);
//			x-=25;
//			y-=25;
//			allObjects.add(circle[i]);
//		}
		for(int i=0;i<circle.length;i++)
		{
			circle[i]=new Circle(new Point(x2,y2),4);
			x2+=5;
			y2+=5;
			allObjects.add(circle[i]);
		}
		
		
		for(int i=0;i<circle.length;i++)
		{
			//Math.random()*30
			circle[i]=new Circle(new Point(x1,y1),4);
			x1+=5;
			y1-=5;
			allObjects.add(circle[i]);
		}
//		System.out.println("zzzzzzzzzzzzzzz");
//		Circle []circle=new Circle[]{new Circle(new Point(30,30),10),new Circle(new Point(70,30),10),
//				new Circle(new Point(500,30),10),new Circle(new Point(500,580),10),new Circle(new Point(30,70),10)};
//		for(int i=0;i<circle.length;i++)
//			allObjects.add(circle[i]);		
		
		
		quad.clear();
//
		for (int i = 0; i < allObjects.size(); i++) {
		  quad.insert(allObjects.get(i));
		  //System.out.println("insert the quad !!");
		}
		quad.show();
		
		
//		taskManager.execute(new UpdateTask());
//		while(true){
//			//Thread.currentThread();
//			Thread.sleep(200);;
//		}
		
		
		//System.out.println("wtf!!");
//		while(true)
//		{	
//			allObjects.clear();
//			for(int i=0;i<circle.length;i++)
//			{
//				circle[i]=new Circle(new Point(x2,y2),Math.random()*36);
//				x2+=25;
//				y2+=25;
//				allObjects.add(circle[i]);
//			}
		int count=0;
		int has=0;
		int once=0;
		List<Shape> returnObjects = new ArrayList<Shape>();
		for (int i = 0; i < allObjects.size(); i++) {
		  returnObjects.clear();
		  if(i==5){}
		  quad.retrieve(returnObjects, allObjects.get(i));
		  
		  returnObjects.remove(allObjects.get(i));
		  for (int c = 0; c < returnObjects.size(); c++) {
		    // Run collision detection algorithm between objects
			//  System.out.println("just for trying !!");
			  //System.out.println(shapeCrashShapeDetection.shapeCrashWithShape(allObjects.get(i), returnObjects.get(c)));
			  //System.out.println(shapeCrashShapeDetection.getBackVector(allObjects.get(i), returnObjects.get(c))); ;
			  if(shapeCrashShapeDetection.shapeCrashWithShape(allObjects.get(i), returnObjects.get(c)))
				  {has++;
				  once++;
				  }
			  
			  count++;
		  }
		  System.out.println(once);
		  once=0;
		
		}System.out.println(count);
		System.out.println("valid"+has);
		Thread.currentThread();
		Thread.sleep(5000);
	}


	private class UpdateTask implements Runnable{

//		@Override
		public void run() {
//			// TODO Auto-generated method stub
//			while(true){
//			quad.clear();
//			
//			Circle []circle=new Circle[20];
//			double x=560,y=560;
//			double x1=0,y1=560;
//			double x2=25,y2=25;
//			allObjects.clear();
//			for(int i=0;i<circle.length;i++)
//			{
//				//circle[i]=new Circle(new Point(x,y),Math.random()*50);
//				circle[i]=new Circle(new Point(x2,y2),10);
//				x-=25;
//				y-=25;
//				allObjects.add(circle[i]);
//			}
//			for(int i=0;i<circle.length;i++)
//			{
//				//circle[i]=new Circle(new Point(x2,y2),Math.random()*36);
//				circle[i]=new Circle(new Point(x2,y2),18);
//				x2+=25;
//				y2+=25;
//				allObjects.add(circle[i]);
//			}
//			
//			
//			for (int i = 0; i < allObjects.size(); i++) {
//			  quad.insert(allObjects.get(i)); 
//			}
//			
//
//			
//			
//		int count=0;
//		int has=0;
//		List<Shape> returnObjects = new ArrayList<Shape>();
//		for (int i = 0; i < allObjects.size(); i++) {
//		  returnObjects.clear();
//		  quad.retrieve(returnObjects, allObjects.get(i));
//		  
//		  returnObjects.remove(allObjects.get(i));
//		  for (int c = 0; c < returnObjects.size(); c++) {
//		    // Run collision detection algorithm between objects
//			//  System.out.println("just for trying !!");
//			  System.out.println(shapeCrashShapeDetection.shapeCrashWithShape(allObjects.get(i), returnObjects.get(c)));
//			  System.out.println(shapeCrashShapeDetection.getBackVector(allObjects.get(i), returnObjects.get(c))); ;
//			  if(shapeCrashShapeDetection.shapeCrashWithShape(allObjects.get(i), returnObjects.get(c)))has++;
//			  count++;
//		  }
//		
//		}System.out.println(count);
//		System.out.println("valid"+has);
//		Thread.currentThread();
//		try {
//			Thread.sleep(20000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//			}
			
			
			
			
//			int count=0;
//			while(true){
//			System.out.println("wtf!!");
//			System.out.println(count++);
//			try {
//				Thread.currentThread();
//				Thread.sleep(200);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			}
		
	
	//}
		
	}
	
	
}
}
