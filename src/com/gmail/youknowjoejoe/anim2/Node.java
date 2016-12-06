package com.gmail.youknowjoejoe.anim2;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.LinkedList;

public class Node {
	private Node parent;
	private ArrayList<Node> children;
	private AffineTransform transform;
	private AffineTransform composition;
	
	public Node(){
		this.transform = new AffineTransform();
		this.parent = null;
		this.children = new ArrayList<Node>();
	}
	
	public Node(AffineTransform transform){
		this.transform = transform;
		this.parent = null;
		this.children = new ArrayList<Node>();
	}
	
	private LinkedList<AffineTransform> getTransforms(){
		LinkedList<AffineTransform> ts = new LinkedList<AffineTransform>();
		
		Node n = this;
		while(n != null){
			ts.addFirst(n.transform);
			n = n.parent;
		}
		
		return ts;
	}
	
	//tries to add Node to children
	//sets n's parent to "this"
	//returns true if successful
	public boolean adoptChild(Node n){
		if(n.isOrphan()){
			n.parent = this;
			children.add(n);
			return true;
		}
		return false;
	}
	
	//tries to remove Node n from children
	//sets parent to null;
	//returns true if successful
	public boolean removeChild(Node n){
		if(n.parent == this){
			n.parent = null;
			children.remove(n);
			return true;
		}
		return false;
	}
	
	private boolean isOrphan(){
		return parent==null;
	}
	
	public void composeTransforms(AffineTransform at){
		this.composition = new AffineTransform(at);
		this.composition.concatenate(transform);
		for(Node n: children){
			n.composeTransforms(this.composition);
		}
	}
	
	public void composeTransforms(){
		for(Node n: children){
			n.composeTransforms(this.transform);
		}
	}
}
