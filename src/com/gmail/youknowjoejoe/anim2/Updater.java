package com.gmail.youknowjoejoe.anim2;

public interface Updater {
	public double getStart();
	public double getEnd();
	public void update(Node n, double d);
}
