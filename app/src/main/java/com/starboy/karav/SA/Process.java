package com.starboy.karav.SA;

public class Process {
	private float xbase;
	private float ybase;
	private int level;

	public void setlevel(int l) {
		level = l;
	}

	public void setbase(float x, float y) {
		xbase = x;
		ybase = y;
	}

	public boolean process(float x, float y) {
		return true;
	}

}
