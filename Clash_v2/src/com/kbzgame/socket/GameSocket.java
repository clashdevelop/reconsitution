package com.kbzgame.socket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
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

import net.sf.json.JSONObject;

@ServerEndpoint("/clash")
public class GameSocket {//connect web and server
	private static CopyOnWriteArraySet<GameSocket> playerSet= new CopyOnWriteArraySet<GameSocket>();
	private static Set<String> nameSet = Collections.synchronizedSet(new HashSet<String>());
	public static void deleteName(String name){
		nameSet.remove(name);
	}
	public static void addName(String name){
		nameSet.add(name);
	}
	private static GameMap map = new GameMap();
	
	private ExecutorService taskmanager = Executors.newCachedThreadPool();
	private Session session;
	private Gamepad gamepad;
	
	@OnOpen//use when open a socket open
	public void onOpen(Session session){
		this.session = session;
		playerSet.add(this);//thread safe
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
		
		JSONObject json = JSONObject.fromString(message);
		String type = json.getString("type");
		if("joinGame".equals(type)){
			String name = json.getString("name");
			if(nameSet.contains(name) || "Jack".equals(name)){
				JSONObject failMessage = new JSONObject();
				failMessage.put("type", "join");
				failMessage.put("ifsuccess",false);
				sendMessage(failMessage.toString());
			}
			else{
				//nameSet.add(name);//存储已存在的名字 移动至玩家加入map处
				gamepad = new Gamepad(map);
				System.out.println("player add 1");
				gamepad.joinGame(name);
				
				JSONObject successMessage = new JSONObject();
				successMessage.put("type", "join");
				successMessage.put("ifsuccess",true);
				successMessage.put("localId",name);
				sendMessage(successMessage.toString());//send player's id 
				
				sendMessage(map.getMapMessage());//send map message
				//System.out.println(map.getMapMessage());
				taskmanager.execute(new sendMessageTask());//run send message thread
			}
		}
		else{
			if(gamepad !=null){
				gamepad.acceptCommandMessage(message);
			}
		}
		
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

