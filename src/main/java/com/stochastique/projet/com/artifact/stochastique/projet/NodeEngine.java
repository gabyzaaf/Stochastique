package com.stochastique.projet.com.artifact.stochastique.projet;

import java.util.ArrayList;

import junit.framework.Assert;



public class NodeEngine {

	public static Node Root;
	private ArrayList<Node> nodes = new ArrayList<Node>();
	private ArrayList<ResultDatas> nodesResults = new ArrayList<ResultDatas>();
	
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



	
	

	
	
}
