package com.stochastique.projet.com.artifact.stochastique.projet;

public class Probability {
	
	
	private DataEntry grid;
	
	public Probability(DataEntry valueFromGrid){
		this.grid = valueFromGrid;
	}
	
	public double ObtainTheCurrentNodeValueIsTrue() {
		Coordinate currentNodeTrue = grid.getCurrentNodeTrue();
		Coordinate lastNodeTrue = grid.getLastNodeTrue();
		return calculTheProbability(currentNodeTrue,lastNodeTrue);
	}

	public double ObtainTheCurrentNodeValueIsFalse() {
		Coordinate currentNodeTrue = grid.getCurrentNodeTrue();
		Coordinate lastNodeFalse = grid.getLastNodeFalse();
		return calculTheProbability(currentNodeTrue,lastNodeFalse);
	}
	
	public double ObtainTheFalseCurrentNodeValue() {
		Coordinate currentNodeFalse = grid.getCurrentNodeFalse();
		Coordinate currentNodeTrue = grid.getLastNodeTrue(); 
		return calculTheProbability(currentNodeFalse,currentNodeTrue);
	}
	
	public double ObtainTheFalseCurrentValues() {
		Coordinate currentNodeFalse = grid.getCurrentNodeFalse();
		Coordinate lastNodeFalse = grid.getLastNodeFalse();
		return calculTheProbability(currentNodeFalse,lastNodeFalse);
	}
	
	private double calculTheProbability(Coordinate currentNodeTrue,Coordinate lastNodeTrue){
		if(currentNodeTrue.getFirst() == lastNodeTrue.getFirst() || currentNodeTrue.getFirst() == lastNodeTrue.getSecond() ){
			return currentNodeTrue.getFirst();
		}else if(currentNodeTrue.getSecond() == lastNodeTrue.getFirst() || currentNodeTrue.getSecond() == lastNodeTrue.getSecond()){
			return currentNodeTrue.getSecond();
		}
		return 0;
	}

	

	

	
	
	


	
}
