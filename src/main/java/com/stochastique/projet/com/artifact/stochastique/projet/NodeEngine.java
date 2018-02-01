package com.stochastique.projet.com.artifact.stochastique.projet;

import java.util.ArrayList;





public class NodeEngine {

	public static Node Root;
	private ArrayList<Node> nodes = new ArrayList<Node>();
	private ArrayList<ResultDatas> nodesResults = new ArrayList<ResultDatas>();
	private ArrayList<LambdaResult> lambdaResult = new ArrayList<LambdaResult>();
	public NodeEngine(Node root) {
		Root = root;
	}
	
	
	
	public void run(){
		if(Root.getLeft() != null){
			nodes.add(Root.getLeft());
		}
		if(Root.getRight() != null){
			nodes.add(Root.getRight());
		}
	
		while(nodes.size() != 0){
			Node current = nodes.get(0);
			
			if(current.getLeft() != null){
				nodes.add(current.getLeft());
			}
			if(current.getRight() != null){
				nodes.add(current.getRight());
			}
			double trueResult = MakeCalculForTheTrue(current,current.getDataEntry());
			current.setTrueResult(trueResult);
			double falseResult = MakeCalculForTheFalse(current,current.getDataEntry());
			current.setFalseResult(falseResult);
			nodesResults.add(new ResultDatas(current.getNames(),trueResult,falseResult));
			nodes.remove(0);
		}
		
	}
	
	public ResultDatas ObtainResult(int indice){
		return nodesResults.get(indice);
	}
	
	public void Display(){
		for(ResultDatas nodeResult : nodesResults){
			System.out.println(nodeResult);
		}
	}



	public double MakeCalculForTheTrue(Node current, DataEntry data) {
		
		Probability proba = new Probability(data);
		double firstResult = proba.ObtainTheCurrentNodeValueIsTrue();
		double secondResult = proba.ObtainTheCurrentNodeValueIsFalse();
		double lastNodeFalseResult = current.getPrevious().getDataEntry().getLastNodeFalse().getSecond();
		double lastNodeTrueResult = current.getPrevious().getDataEntry().getLastNodeFalse().getFirst();
		
		
		return calculs(firstResult, lastNodeFalseResult, secondResult, lastNodeTrueResult);
	}
	
	public double MakeCalculForTheFalse(Node current, DataEntry data) {
		
		
		Probability proba = new Probability(data);
		
		double firstResult = proba.ObtainTheFalseCurrentNodeValue();
		double secondResult = proba.ObtainTheFalseCurrentValues();
		double lastNodeFalseResult = current.getPrevious().getDataEntry().getLastNodeFalse().getSecond();
		double lastNodeTrueResult = current.getPrevious().getDataEntry().getLastNodeFalse().getFirst();
		double res = calculs(firstResult, lastNodeFalseResult, secondResult, lastNodeTrueResult);
		return res;
		 
	}
	
	private double calculs(double firstResult, double lastNodeFalseResult,double secondResult, double lastNodeTrueResult){
		double res = firstResult * lastNodeFalseResult + secondResult * lastNodeTrueResult;
		res = (double)Math.round(res * 100000) / 100000;
		return res;
	}



	public Node ObtainTheLastRightNode() {
		Node tmp = Root.getRight();
		while( tmp.getRight() != null){
			tmp = tmp.getRight();
		}
		return tmp;
	}



	public double MakeCalculForTruePropagation(Node node, DataEntry dataNext) {
		Probability proba = new Probability(dataNext);
		double firstResult = proba.ObtainTheCurrentNodeValueIsTrue();
		double currentLambdaTrue = node.getLamdaTrue();
		double thirdresult = proba.ObtainTheCurrentNodeValueIsFalse();
		double currentLambdaFalse = node.getLamdaFalse();
		return calculs(firstResult,currentLambdaTrue,thirdresult,currentLambdaFalse);
	}



	public double MakeCalculForFalsePropagation(Node node, DataEntry dataNext) {
		Probability proba = new Probability(dataNext);
		double firstValue = proba.ObtainTheFalseCurrentNodeValue();
		double lambdaTrueValues = node.getLamdaTrue();
		double thirdValue = proba.ObtainTheFalseCurrentValues();
		double currentLambdaFalse = node.getLamdaFalse();
		return calculs(firstValue,lambdaTrueValues,thirdValue,currentLambdaFalse);
	}



	public Node ObtainSpecificNode(String nodeName) {
		nodes.clear();
		if(Root.getRight() != null){
			nodes.add(Root.getRight());
		}
		if(Root.getLeft() != null){
			nodes.add(Root.getLeft());
		}
		
		while(nodes.size() != 0){
			Node tmp = nodes.get(0);
			if(tmp.getNames().equals(nodeName)){
				nodes.clear();
				return tmp;
			}
			if(tmp.getRight() != null){
				nodes.add(tmp.getRight());
			}
			if(tmp.getLeft() != null){
				nodes.add(tmp.getLeft());
			}
			nodes.remove(0);
		}
		return null;
	}



	public void runPropagation() {
		nodes.clear();
		Node nodeForBeginPropagation = ObtainSpecificNode("D");
		
		nodes.add(nodeForBeginPropagation);
		
		while(!nodes.isEmpty()){
			Node tmp = nodes.get(0);
			if(!tmp.previousNodeAlreadyChecked()){
				if(!tmp.getNames().equalsIgnoreCase("A")){
					double truePropagation = MakeCalculForTruePropagation(tmp, tmp.getDataEntry());
					double falsePropagation = MakeCalculForFalsePropagation(tmp, tmp.getDataEntry());
					tmp.checkThePreviousNodeToTrue();
					// set The Value To the Previous Node.
					tmp.setTrueLambdaToThePreviousNode(truePropagation);
					tmp.setFalseLambdaToThePreviousNode(falsePropagation);
					// Calcule Pi * Lambda 
					double lambdaTrueResult = tmp.getLamdaTrue();
					double lambdaFalseResult = tmp.getLamdaFalse();
					double pitrueResult = tmp.getTrueResult();
					double pifalseResult = tmp.getFalseResult();
					
					double piLambdaResultTrue = lambdaTrueResult *pitrueResult;
					double piLambdaResultFalse = lambdaFalseResult * pifalseResult;
					
					this.lambdaResult.add(new LambdaResult(tmp.getPrevious().getNames(),lambdaTrueResult, lambdaFalseResult, pitrueResult, pifalseResult));
					this.nodes.add(tmp.getPrevious());
				}
				tmp.setState(true);
				if(tmp.getRight() != null && !tmp.rightIsChecked()){
					this.nodes.add(tmp.getRight());
				}
				
				if(tmp.getLeft() != null && !tmp.leftIsChecked()){
					this.nodes.add(tmp.getLeft());
				}
				this.nodes.remove(0);
			}else{
				if(this.nodes.size() != 0){
					this.nodes.remove(0);
				}
			}
		}
		
		
	}



	public void DisplayPropagation() {
		this.lambdaResult.forEach( (k) -> {System.out.println(k);});
		
	}



	
	

	
	
}
