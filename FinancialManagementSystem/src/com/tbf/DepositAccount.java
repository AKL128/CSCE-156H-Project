package com.tbf;

public class DepositAccount extends Asset{

	private double apr;
	
	public DepositAccount(String code, String id, String label, double apr) {
		super(code, id, label);
		this.apr = apr;
	}
	
	public double getApr() {
		return apr;
	}
}
