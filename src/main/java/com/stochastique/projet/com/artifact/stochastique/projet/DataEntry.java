package com.stochastique.projet.com.artifact.stochastique.projet;

import java.util.ArrayList;
import java.util.List;

public class DataEntry {

	private Coordinate lastNodeFalse;
	private Coordinate lastNodeTrue;
	
	private Coordinate currentNodeFalse;
	private Coordinate currentNodeTrue;
	
	public DataEntry(double first,double second){
		lastNodeFalse = new Coordinate(first,second);
	}
	
	public DataEntry(double first, double second, double three, double fourth) {
		this(first,second);
		lastNodeTrue = new Coordinate(three,fourth);
		currentNodeFalse = new Coordinate(first,three);
		currentNodeTrue = new Coordinate(second,fourth);
	}

	public Coordinate getLastNodeFalse() {
		return lastNodeFalse;
	}

	public Coordinate getLastNodeTrue() {
		return lastNodeTrue;
	}

	public Coordinate getCurrentNodeFalse() {
		return currentNodeFalse;
	}

	public Coordinate getCurrentNodeTrue() {
		return currentNodeTrue;
	}
	
	

	

	

	

	
	
	
	
	

}
