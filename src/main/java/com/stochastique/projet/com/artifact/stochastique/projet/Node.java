package com.stochastique.projet.com.artifact.stochastique.projet;

public class Node {
	
	private DataEntry data ;
	private Node previous,right,left;
	private String nodeName;
	private double lambdaTrue = 1;
	private double lambdaFalse = 1;
	private boolean state;
	
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

	public double getLamdaTrue() {
		// TODO Auto-generated method stub
		return this.lambdaTrue;
	}

	public double getLamdaFalse() {
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

	public boolean previousNodeAlreadyChecked() {
		if(this.previous == null){
			return false;
		}
		return this.previous.getState();
	}

	public boolean getState() {
		// TODO Auto-generated method stub
		return this.state;
	}

	public void checkThePreviousNodeToTrue() {
		if(this.previous != null){
			this.previous.setState(true);
		}
		
	}

	public void setState(boolean stateValue) {
		this.state = stateValue;
		
	}

	public void setTrueLambdaToThePreviousNode(double truePropagation) {
		this.lambdaTrue = truePropagation;
	}

	public void setFalseLambdaToThePreviousNode(double falsePropagation) {
		this.lambdaFalse = falsePropagation;
	}

	public double getTrueResult() {
		// TODO Auto-generated method stub
		return this.trueResult;
	}

	public double getFalseResult() {
		// TODO Auto-generated method stub
		return this.falseResult;
	}

	

	public boolean rightIsChecked() {
		return this.right.getState();
	}

	public boolean leftIsChecked() {
		return this.left.getState();
	}

	public void changeLambdaValues() {
		this.lambdaFalse = 1;
		this.lambdaTrue = 0;
		
	}

	

	

	
	

	
	
	
	
	
	
}
