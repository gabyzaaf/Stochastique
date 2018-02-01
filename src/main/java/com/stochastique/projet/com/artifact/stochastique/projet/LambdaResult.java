package com.stochastique.projet.com.artifact.stochastique.projet;

public class LambdaResult {

	private String name;
	private double lambdaTrueResult;
	private double lambdaFalseResult;
	private double PitrueResult;
	private double PifalseResult;
	
	public LambdaResult(String name,double lambdaTrueResult, double lambdaFalseResult,
			double pitrueResult, double pifalseResult) {
		
		this.name = name;
		this.lambdaTrueResult = lambdaTrueResult;
		this.lambdaFalseResult = lambdaFalseResult;
		PitrueResult = pitrueResult;
		PifalseResult = pifalseResult;
	}

	@Override
	public String toString() {
		return "LambdaResult [name=" + name + ", lambdaTrueResult="
				+ lambdaTrueResult + ", lambdaFalseResult=" + lambdaFalseResult
				+ ", PitrueResult=" + PitrueResult + ", PifalseResult="
				+ PifalseResult + "]";
	}

	
	
	
	
	
	
}
