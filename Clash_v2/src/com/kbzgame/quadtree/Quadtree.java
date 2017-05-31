package com.kbzgame.quadtree;

import java.util.ArrayList;
import java.util.List;

import com.kbzgame.physics.shape.Circle;
import com.kbzgame.physics.shape.EdgeRect;
import com.kbzgame.physics.shape.Shape;

public class Quadtree{
	
	private int MAX_SIZE =10;
	  private int MAX_LEVEL = 5;

	  private int level;
	  private List<Shape> objects;
	  private EdgeRect bounds;
	  private Quadtree[] nodes;

	public Quadtree(int Level, EdgeRect Bounds) {
	   this.level = Level;
	   objects = new ArrayList<Shape>();
	   this.bounds = Bounds;
	   nodes = new Quadtree[4];
	  }
	  /**
	   * Show how the quadtree build
	   * 
	   * */
	 public void show()
	  {
		  System.out.println("hierarchy!!!");
		  for(Shape shape:objects){ 
			  Circle circle=(Circle)shape;
			  System.out.println("("+circle.getPosition().getX()+","+circle.getPosition().getY()+")"+","+circle.getR());
		  }
		  for (int i = 0; i < nodes.length; i++) {
			     if (nodes[i] != null) {
			       nodes[i].show();
			       //nodes[i] = null;
			      // System.out.println("#");
			       
			     }
			     else
			     System.out.println("#");
			    
			   }System.out.println("@");
	  }
	  
	  /**
	  * Clears the quadtree
	  */
	  public void clear() {
		   objects.clear();

		   for (int i = 0; i < nodes.length; i++) {
		     if (nodes[i] != null) {
		       nodes[i].clear();
		      nodes[i] = null;
		     }
		   }
		}
	 
	  /**
	   * Splits the node into 4 subnodes
	   */
	private void split() {
		   int subWidth = (int)(bounds.getWidth() / 2);
		   int subHeight = (int)(bounds.getHeight() / 2);
		   int x = (int)bounds.getX();
		   int y = (int)bounds.getY();

		   nodes[0] = new Quadtree(level+1, new EdgeRect(x + subWidth, y+subHeight, subWidth, subHeight));
		   nodes[1] = new Quadtree(level+1, new EdgeRect(x, y+ subHeight, subWidth, subHeight));
		   nodes[2] = new Quadtree(level+1, new EdgeRect(x, y , subWidth, subHeight));
		   nodes[3] = new Quadtree(level+1, new EdgeRect(x + subWidth, y , subWidth, subHeight));
		}
	
	  /**
	   * 
	  * Determine which node the object belongs to. -1 means
	  * object cannot completely fit within a child node and is part
	  * of the parent node
	  */
	  private int getIndex(Shape shape) {
		   int index = -1;
		   double verticalMidpoint = bounds.getY() + (bounds.getHeight() / 2);
		   double horizontalMidpoint = bounds.getX() + (bounds.getWidth() / 2);
		   
		   double []extreme= shape.getExtremePositons();
		   
		   
		   if(extreme[1]>verticalMidpoint)
		   {
			  if(extreme[3]<horizontalMidpoint)index=1;
			  else if(extreme[2]>horizontalMidpoint)index=0;
		   }
		   else if(extreme[0]<verticalMidpoint)
		   {
			   if(extreme[3]<horizontalMidpoint)index=2;
				  else if(extreme[2]>horizontalMidpoint)index=3;
		   }
		  // System.out.println(index);
		   return index;
		}
	  /**
	  * Insert the object into the quadtree. If the node
	  * exceeds the capacity, it will split and add all
	  * objects to their corresponding nodes.
	  */
	  public void insert(Shape shape) {
		   if (nodes[0] != null) {
		     int index = getIndex(shape);

		     if (index != -1) {
		       nodes[index].insert(shape);

		       return;
		     }
		   }

		   objects.add(shape);

		   if (objects.size() > MAX_SIZE && level < MAX_LEVEL) {
			   	if (nodes[0] == null) {
			         split(); 		        
		     }  
//			   split();
			   	int i = 0;
			     while (i < objects.size()) {
				       int index = getIndex(objects.get(i));
				       if (index != -1) {
				         nodes[index].insert(objects.remove(i));
				       }
				       else {
				         i++;
				       }
				     }

			   
		     
		   }
		}
	  /**
	  * Return all objects that could collide with the given object;if index=-1,should return all its child
	  * 
	  * 
	  */
	  public List<Shape> retrieve(List<Shape> returnObjects, Shape shape) {

		  int index = getIndex(shape);
		  if(nodes[0]!=null)
		  {
			  if(index!=-1)
			  {
				  //
				//nodes[index].retrieve(returnObjects, shape) ;
				nodes[index].retrieve(returnObjects, shape) ;
				
			  }
			  else{
				  for(int i=0;i<nodes.length;i++)
				  {
					
					nodes[i].retrieve(returnObjects, shape);
				  }
			  }
		  }
//		  if(  index ==-1||nodes[0] == null)		  
//		  returnObjects.addAll(objects);
		  
		  returnObjects.addAll(objects);
		  return returnObjects;
		}
	  
}
