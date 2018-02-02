package com.stochastique.projet.com.artifact.stochastique.projet;

public class LambdaResult {

	private String name;
	private double lambdaTrueResult;
	private double lambdaFalseResult;
	private double PitrueResult;
	private double PifalseResult;
	private double truePropagation;
	private double falsePropagation;
	
	public LambdaResult(String name,double lambdaTrueResult, double lambdaFalseResult,
			double pitrueResult, double pifalseResult,double truePropagation,double falsePropagation) {
		
		this.name = name;
		this.lambdaTrueResult = lambdaTrueResult;
		this.lambdaFalseResult = lambdaFalseResult;
		PitrueResult = pitrueResult;
		PifalseResult = pifalseResult;
		this.truePropagation = truePropagation;
		this.falsePropagation = falsePropagation;
	}

	@Override
	public String toString() {
		
		return "[Node Name = "+this.name+", lambda(v) = "+this.lambdaTrueResult+", P(v) = "+this.truePropagation+", PI(v) = "+this.PitrueResult+"] \n "
				+ "lambda(f) = "+this.lambdaFalseResult+", P(f) = "+this.falsePropagation+", PI(f) = "+this.PifalseResult;
		
	}

	
	
	
	
	
	
}
