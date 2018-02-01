package com.stochastique.projet.com.artifact.stochastique.projet;

public class ResultDatas  {

	private String name;
	private double trueResult;
	private double falseResult;
	
	public ResultDatas(String name, double trueResult, double falseResult) {
		this.name = name;
		this.trueResult = trueResult;
		this.falseResult = falseResult;
	}

	public String getName() {
		return name;
	}

	public double getTrueResult() {
		return trueResult;
	}

	public double getFalseResult() {
		return falseResult;
	}

	@Override
	public String toString() {
		return "ResultDatas [NodeName=" + name + ", P(v)=" + trueResult
				+ ", P(f)=" + falseResult + "]";
	}
	
	
	
	

}
