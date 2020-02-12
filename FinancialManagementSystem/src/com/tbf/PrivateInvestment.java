package com.tbf;

public class PrivateInvestment extends Asset{
	private double quarterlyDividend;
	private double baseRateOfReturn;
	private double baseOmegaMeasure;
	private double value;
	
	public PrivateInvestment(String code, String id, String label, double quarterlyDividend
							, double baseRateOfReturn, double baseOmegaMeasure, double value) {
		super(code, id, label);
		this.quarterlyDividend = quarterlyDividend;
		this.baseRateOfReturn = baseRateOfReturn;
		this.baseOmegaMeasure = baseOmegaMeasure;
		this.value = value;
	}
	
	public double getQuarterlyDividend() {
		return quarterlyDividend;
	}
	
	public double getBaseRateOfReturn() {
		return baseRateOfReturn;
	}
	
	public double getBaseOmegaMeasure() {
		return baseOmegaMeasure;
	}
	
	public double getValue() {
		return value;
	}
}

