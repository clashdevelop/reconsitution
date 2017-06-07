package com.kbzgame.quadtree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.kbzgame.physics.elements.Body;
import com.kbzgame.physics.shape.EdgeRect;
import com.kbzgame.physics.shape.Shape;

public class Quadtree{
	
	private int MAX_SIZE =10;//非临界形状的最大值
	  private int MAX_LEVEL = 5;//最大的层数

	  private int level;
	  private List<Body> objects;
	  private EdgeRect bounds;
	  private Quadtree[] nodes;

	public Quadtree(int Level, EdgeRect Bounds) {
	   this.level = Level;
	   objects = new ArrayList<Body>();
	   this.bounds = Bounds;
	   nodes = new Quadtree[4];
	  }
	  /**
	   * Show how the quadtree build
	   * 
	   * */
	@SuppressWarnings(value = {})
	 public  void show()
	  {
//		  System.out.println("hierarchy!!!");
//		  for(Shape shape:objects){ 
//			  Circle circle=(Circle)shape;
//			  System.out.println("("+circle.getPosition().getX()+","+circle.getPosition().getY()+")"+","+circle.getR());
//		  }
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
	  public  void clear() {
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
	private  void split() {
		
		   double subWidth = (bounds.getRightX()-bounds.getLeftX()) / 2;
		   double subHeight = (bounds.getTopY()-bounds.getBelowY()) / 2;

		   nodes[0] = new Quadtree(level+1, new EdgeRect(bounds.getLeftX() + subWidth, bounds.getRightX(), bounds.getTopY(), bounds.getBelowY()+subHeight));
		   nodes[1] = new Quadtree(level+1, new EdgeRect(bounds.getLeftX(), bounds.getRightX()+ subWidth, bounds.getTopY(), bounds.getBelowY()+subHeight));
		   nodes[2] = new Quadtree(level+1, new EdgeRect(bounds.getLeftX(), bounds.getLeftX()+subWidth,bounds.getBelowY()+subHeight, bounds.getBelowY()));
		   nodes[3] = new Quadtree(level+1, new EdgeRect(bounds.getLeftX() + subWidth, bounds.getRightX(), bounds.getBelowY()+subHeight, bounds.getBelowY()));
		}
	
	  /**
	   * 
	  * Determine which node the object belongs to. -1 means
	  * object cannot completely fit within a child node and is part
	  * of the parent node
	  */
	  private  PositionType getIndex(Shape shape) {
		   double verticalMidpoint = (bounds.getTopY()+bounds.getBelowY())/2;
		   double horizontalMidpoint =(bounds.getLeftX()+bounds.getRightX())/2;
		   
		   EdgeRect rectEdge=shape.getBorder();
		   
		   if(rectEdge.getBelowY()>verticalMidpoint)
		   {
			  if(rectEdge.getRightX()<horizontalMidpoint){
				  return PositionType.SecondQ;
			  }
			  else if(rectEdge.getLeftX()>horizontalMidpoint){
				  return PositionType.FirstQ;
			  }
			  else{
				return PositionType.First_SecondQ;
			  }
			  
		   }
		   
		   else if(rectEdge.getTopY()<verticalMidpoint)
		   {
			   if(rectEdge.getRightX()<horizontalMidpoint){
				   return PositionType.ThirdQ;
			   }
			   else if(rectEdge.getLeftX()>horizontalMidpoint){
				   return PositionType.FourthQ;
			   }
			   else{
				   return PositionType.Third_FourthQ;
			   }
		   }
		   else{
			   if(rectEdge.getRightX()<horizontalMidpoint){
				   return PositionType.Second_ThirdQ;
			   }
			   else if(rectEdge.getLeftX()>horizontalMidpoint){
				   return PositionType.Fourth_FirstQ;
			   }
			   else{
				   return PositionType.AllQ;
			   }
		   }
		}
	  private  void addToNode(Body body){
		  PositionType index = getIndex(body.getShape());
		  switch(index){
		  case FirstQ:
			  nodes[0].insert(body);
			  break;
		  case SecondQ:
			  nodes[1].insert(body);
			  break;
		  case ThirdQ:
			  nodes[2].insert(body);
			  break;
		  case FourthQ:
			  nodes[3].insert(body);
			  break;
		  case First_SecondQ:
			  nodes[0].insert(body);
			  nodes[1].insert(body);
			  break;
		  case Second_ThirdQ:
			  nodes[1].insert(body);
			  nodes[2].insert(body);
			  break;
		  case Third_FourthQ:
			  nodes[2].insert(body);
			  nodes[3].insert(body);
		   	  break;
		  case Fourth_FirstQ:
			  nodes[3].insert(body);
			  nodes[0].insert(body);
			  break;
		  case AllQ:
			  nodes[0].insert(body);
			  nodes[1].insert(body);
			  nodes[2].insert(body);
			  nodes[3].insert(body);
			  break;
		  default://do nothing  
		  }
	  }
	  /**
	  * Insert the object into the quadtree. If the node
	  * exceeds the capacity, it will split and add all
	  * objects to their corresponding nodes.
	  */
	  public void insert(List<Body> bodyList){
		  Iterator<Body> bodyIter = bodyList.iterator();
		  while(bodyIter.hasNext()){
			  insert(bodyIter.next());
		  }
	  }
	  public  void insert(Body body) {
		   if (nodes[0] != null) {
			   addToNode(body);
		       return;
		     }
		   //最大值未满 或者 临界情况
		   objects.add(body);

		   if (objects.size() > MAX_SIZE && level < MAX_LEVEL) {
			   	if (nodes[0] == null) {
			         split(); 		        
		     }  
//			   split();
			List<Body> copy = new ArrayList<Body>(objects);
			Iterator<Body> copyIter = copy.iterator();
		    objects.clear();
			while (copyIter.hasNext()) {
				insert(copyIter.next());
				}
		   }
	  }
	  /**
	  * Return all objects that could collide with the given object;if index=-1,should return all its child
	  * 
	  * 
	  */
	  public  List<Body> retrieve(Shape shape) {
		  if(nodes[0]==null){
			  return new ArrayList<Body>(objects);
		  }
		  PositionType index = getIndex(shape);
		  Set<Body> bodys = new HashSet<Body>();
		  switch(index){
		  case FirstQ:
			  return nodes[0].retrieve(shape);
		  case SecondQ:
			  return nodes[1].retrieve(shape);
		  case ThirdQ:
			  return nodes[2].retrieve(shape);
		  case FourthQ:
			  return nodes[3].retrieve(shape);
		  case First_SecondQ:
			  bodys.clear();
			  bodys.addAll(nodes[0].retrieve(shape));
			  bodys.addAll(nodes[1].retrieve(shape));
			  return new ArrayList<Body>(bodys);
		  case Second_ThirdQ:
			  bodys.clear();
			  bodys.addAll(nodes[1].retrieve(shape));
			  bodys.addAll(nodes[2].retrieve(shape));
			  return new ArrayList<Body>(bodys);
		  case Third_FourthQ:
			  bodys.clear();
			  bodys.addAll(nodes[2].retrieve(shape));
			  bodys.addAll(nodes[3].retrieve(shape));
			  return new ArrayList<Body>(bodys);
		  case Fourth_FirstQ:
			  bodys.clear();
			  bodys.addAll(nodes[3].retrieve(shape));
			  bodys.addAll(nodes[0].retrieve(shape));
			  return new ArrayList<Body>(bodys);
		  case AllQ:
			  bodys.clear();
			  bodys.addAll(nodes[0].retrieve(shape));
			  bodys.addAll(nodes[1].retrieve(shape));
			  bodys.addAll(nodes[2].retrieve(shape));
			  bodys.addAll(nodes[3].retrieve(shape));
			  return new ArrayList<Body>(bodys);
		  default:
			  return null;
			  //do nothing  
		  }
	  }
	  
}
