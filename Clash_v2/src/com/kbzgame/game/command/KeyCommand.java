package com.kbzgame.game.command;

import com.kbzgame.game.elements.Roller;

public class KeyCommand extends Command{
	private Key key;
	private Roller reciver;
	public KeyCommand(Key key,Roller reciver){
		this.key = key;
		this.reciver = reciver;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		switch(key){
		case SKILLONE:
			break;
		case SKILLTWO:
			break;
		case SKILLTHREE:
			break;
		case SKILLFOUR:
			break;
		default:;
			//think but do nothing
		}
	}
}
