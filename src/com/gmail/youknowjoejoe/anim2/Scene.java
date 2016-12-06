package com.gmail.youknowjoejoe.anim2;

import java.util.ArrayList;

public class Scene {
	private ArrayList<Node> nodes;
	private ArrayList<Updater> updaters;
	
	public Scene(){
		nodes = new ArrayList<Node>();
		updaters = new ArrayList<Updater>();
		
	}
	
	private void update(double dt){
		updateNodes();
	}
	
	private void updateNodes(){
		
		applyUpdaters();
		
		composeTransforms();
	}
	
	private void applyUpdaters(){
		
	}
	
	private void composeTransforms(){
		for(Node n: nodes){
			if(n.isOrphan()){
				n.composeTransforms();
			}
		}
	}
}
