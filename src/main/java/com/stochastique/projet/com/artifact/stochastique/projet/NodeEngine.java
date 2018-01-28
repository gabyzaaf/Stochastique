package com.stochastique.projet.com.artifact.stochastique.projet;

import java.util.ArrayList;



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
			double trueResult = MakeCalculForTheTrue(current.getDataEntry());
			double falseResult = MakeCalculForTheFalse(current.getDataEntry());
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



	public double MakeCalculForTheTrue(DataEntry data) {
		
		Probability proba = new Probability(data);
		double firstResult = proba.ObtainTheCurrentNodeValueIsTrue();
		double secondResult = proba.ObtainTheCurrentNodeValueIsFalse();
		double lastNodeFalseResult = Root.getDataEntry().getLastNodeFalse().getSecond();
		double lastNodeTrueResult = Root.getDataEntry().getLastNodeFalse().getFirst();
		
		
		return calculs(firstResult, lastNodeFalseResult, secondResult, lastNodeTrueResult);
	}
	
	public double MakeCalculForTheFalse(DataEntry data) {
		
		
		Probability proba = new Probability(data);
		
		double firstResult = proba.ObtainTheFalseCurrentNodeValue();
		double secondResult = proba.ObtainTheFalseCurrentValues();
		double lastNodeFalseResult = Root.getDataEntry().getLastNodeFalse().getSecond();
		double lastNodeTrueResult = Root.getDataEntry().getLastNodeFalse().getFirst();
		double res = calculs(firstResult, lastNodeFalseResult, secondResult, lastNodeTrueResult);
		return res;
		 
	}
	
	private double calculs(double firstResult, double lastNodeFalseResult,double secondResult, double lastNodeTrueResult){
		double res = firstResult * lastNodeFalseResult + secondResult * lastNodeTrueResult;
		res = (double)Math.round(res * 100000) / 100000;
		return res;
	}



	public Node ObtainTheLastRightNode() {
		Node tmp = null;
		while(Root.getRight() != null){
			tmp = Root.getRight();
		}
		return tmp;
	}



	
	

	
	
}