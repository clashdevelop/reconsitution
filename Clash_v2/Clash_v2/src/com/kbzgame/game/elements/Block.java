package com.kbzgame.game.elements;

import com.kbzgame.physics.elements.Body;
import com.kbzgame.physics.shape.Shape;

public class Block extends Body{

	public Block(Shape shape) {
		super(shape);
		// TODO Auto-generated constructor stub
		setMovable(false);
	}

}
