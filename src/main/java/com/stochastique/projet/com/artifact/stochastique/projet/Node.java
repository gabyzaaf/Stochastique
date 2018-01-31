package com.stochastique.projet.com.artifact.stochastique.projet;

public class Node {
	
	private DataEntry data ;
	private Node previous,right,left;
	private String nodeName;
	private int lambdaTrue = 0;
	private int lambdaFalse = 1;
	
	private double trueResult,falseResult;
	
	public Node(String nodeName, DataEntry dataEntry) {
		this.nodeName = nodeName;
		this.data = dataEntry;
	}

	public void next(Node previousNode, Node nextNode) {
		nextNode.setPrevious(previousNode);
		addTheNextNode(nextNode);
	}
	
	private void setPrevious(Node previousNode) {
		this.previous = previousNode;	
	}

	private void addTheNextNode(Node nextNode){
		if(this.left == null){
			this.left = nextNode;
		}else{
			this.right = nextNode;
		}
	}

	public String getNames() {
		
		return this.nodeName;
	}

	public Node getRight() {
		// TODO Auto-generated method stub
		return this.right;
	}

	public Node getLeft() {
		// TODO Auto-generated method stub
		return this.left;
	}
	
	public DataEntry getDataEntry(){
		return this.data;
	}

	public int getLamdaTrue() {
		// TODO Auto-generated method stub
		return this.lambdaTrue;
	}

	public int getLamdaFalse() {
		// TODO Auto-generated method stub
		return this.lambdaFalse;
	}
	
	public void setTrueResult(double trueResult) {
		this.trueResult = trueResult;	
	}

	public void setFalseResult(double falseResult) {
		this.falseResult = falseResult;
		
	}

	public Node getPrevious() {
		
		return this.previous;
	}

	

	
	
	
	
	
	
}
