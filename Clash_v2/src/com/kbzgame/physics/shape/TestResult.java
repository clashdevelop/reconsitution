package com.kbzgame.physics.shape;

public class TestResult {
	private boolean happened = false;
	private Vector backVector = null;
	public TestResult(boolean happend,Vector backVector){
		this.happened = happend;
		this.backVector = backVector;
	}
	public Vector getBackVector(){
		return backVector;
	}
	public boolean isHappened() {
		return happened;
	}
}
