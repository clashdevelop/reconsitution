package com.kbzgame.controller;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

import com.kbzgame.game.command.Command;
import com.kbzgame.game.command.CommandFactory;
import com.kbzgame.game.elements.GameMap;
import com.kbzgame.game.elements.JsonHelper;
import com.kbzgame.game.elements.Roller;
import com.kbzgame.game.elements.RollerFactory;

public class Gamepad {
	private GameMap map;
	private Roller roller;
	private BlockingQueue<Command> commands = new LinkedBlockingDeque<Command>();
	private ExecutorService taskManager = Executors.newCachedThreadPool();
	public Gamepad(GameMap map){
		this.map = map;
	}
	public void joinGame(String name){
		//roller = RollerFactory.createCircleRoller(name);
		//roller = RollerFactory.createPolygonRoller(name);
		roller = RollerFactory.createRoller(name);
		//some roller information set
		roller.addToWorld(map);
		taskManager.execute(new CommandTask());
	}
	public void quitGame(){
		taskManager.shutdownNow();
		roller.quitWorld(map);
	}
	public void acceptCommandMessage(String commandMessage){
		Command command = CommandFactory.creatCommand(commandMessage, roller);
		try {
			commands.put(command);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Intterupetd when store command!!");
			//e.printStackTrace();
		}
	}
	public String getGamePadId() {
		// TODO Auto-generated method stub
		return JsonHelper.getRollerId( roller.getID()).toString();
	}

	private class CommandTask implements Runnable{
		@Override
		public void run(){
			// TODO Auto-generated method stub
			try{
				//take command from command queue to execute 
				while(!Thread.interrupted()){
					Command exeCommand = commands.take();//thread safe
					exeCommand.execute();//执行命令，线程安全
				}
			}catch(InterruptedException e){
				System.out.println("Interrupted when wait for newCommand!");
				//e.printStackTrace();
			}
		}
	}
}
