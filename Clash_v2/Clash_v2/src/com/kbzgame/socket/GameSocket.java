package com.kbzgame.socket;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.kbzgame.controller.Gamepad;
import com.kbzgame.game.elements.GameMap;

@ServerEndpoint("/clash")
public class GameSocket {//connect web and server
	private static CopyOnWriteArraySet<GameSocket> playerSet= new CopyOnWriteArraySet<GameSocket>();
	private static GameMap map = new GameMap();
	
	private ExecutorService taskmanager = Executors.newCachedThreadPool();
	private Session session;
	private Gamepad gamepad;
	
	
	@OnOpen//use when open a socket open
	public void onOpen(Session session){
		this.session = session;
		playerSet.add(this);//thread safe
		
		gamepad = new Gamepad(map);
		System.out.println("player add 1");
		gamepad.joinGame("Bob");
		sendMessage(gamepad.getGamePadId());//send player's id 
		sendMessage(map.getMapMessage());//send player's id 
		taskmanager.execute(new sendMessageTask());//run send message thread
	}
	@OnClose//use when a socket close
	public void onClose(){
		playerSet.remove(this);
		
		System.out.println("player reduce 1");
		gamepad.quitGame();
		taskmanager.shutdownNow();
	}
	@OnMessage//use when accept message
	public void onMassage(String message,Session session){
		gamepad.acceptCommandMessage(message);
	}
	@OnError//use when there is a error
	public void onError(Session session,Throwable error){
		System.out.println("Error");
		
		System.out.println("Quit game!");
		gamepad.quitGame();
		taskmanager.shutdownNow();
	}
	
	private void sendMessage(String message) {
		try {
			session.getBasicRemote().sendText(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("****Error when send message****");
			e.printStackTrace();
		}
	}
	
	private void notifyAllPlayer(String message){
		for(GameSocket player:playerSet){
			player.sendMessage(message);
		}
	
	}
	private class sendMessageTask implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try{
				while(!Thread.interrupted()){
					String message = map.getRollerMessage();
					sendMessage(message);
					//System.out.println("message= "+message);
					TimeUnit.MILLISECONDS.sleep(200);//send message every 200ms 
				}
			}catch(InterruptedException e){
				//System.out.println("exit from interruptedException");
				//the exception is ok
			}
		}
	}
}

