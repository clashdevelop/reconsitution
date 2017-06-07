package tests;

import com.kbzgame.physics.elements.Body;
import com.kbzgame.physics.elements.BodyFactory;
import com.kbzgame.physics.elements.PhysicsWorld;
import com.kbzgame.physics.event.PhysicsBodyCrashEvent;
import com.kbzgame.physics.event.T_EventListener;

public class EventDispatcherTest {
	public static void main(String[] args){
		PhysicsWorld world = new PhysicsWorld();
		Body bodyA = BodyFactory.createBody(5, 5, 5);
		Body bodyB = BodyFactory.createBody(8, 5, 5);
		Body bodyC = BodyFactory.createBody(20, 5, 5);
		T_EventListener listener = new T_EventListener();
		world.registerEventListener(PhysicsBodyCrashEvent.class,bodyA, listener);
		
		PhysicsBodyCrashEvent event = new PhysicsBodyCrashEvent(bodyA,bodyB);
		//world.contact.sendPhysicsEvent(event);
	}
}
