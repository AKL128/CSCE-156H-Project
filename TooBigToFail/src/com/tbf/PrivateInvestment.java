package com.tbf;

public class PrivateInvestment extends Asset{
	private double quarterlyDividend;
	private double baseRateOfReturn;
	private double baseOmegaMeasure;
	private double totalValue;
	
	public PrivateInvestment(String code, String id, String label, double quarterlyDividend
							, double baseRateOfReturn, double baseOmegaMeasure, double totalValue) {
		super(code, id, label);
		this.quarterlyDividend = quarterlyDividend;
		this.baseRateOfReturn = baseRateOfReturn;
		this.baseOmegaMeasure = baseOmegaMeasure;
		this.totalValue = totalValue;
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
	
	public double getTotalValue() {
		return totalValue;
	}
	
//	public double getAnnualReturn() {
//		return (totalValue * baseRateOfReturn + (4 * quarterlyDividend));
//	}
}