/**
 * Author: Anthony luu, and Brett Berg
 * Date: 2020/2/28
 *
 * This is a subclass of class Asset that holds appropriate methods and data that defines a Deposit Account.
 *
 */

package com.tbf;

public class DepositAccount extends Asset{

	private double apr;
	private double totalBalance;

	public DepositAccount(Integer assetId, String code, String id, String label, double apr) {
		super(assetId, code, id, label);
		this.apr = apr;
	}

	DepositAccount(DepositAccount d) {
		super(d);
		apr = d.apr;
	}

	public double getApr() {
		return apr;
	}

	public double getAnnualReturn() {
		return (getReturnRate()/100) * totalBalance;
	}

	public double getReturnRate() {
		return (Math.exp(apr) - 1) * 100;
	}

	public void setPortValue(double portValue) {
		this.totalBalance = portValue;
	}

	public double getValue() {
		return totalBalance;
	}
}