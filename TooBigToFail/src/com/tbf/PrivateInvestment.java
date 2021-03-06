/**
 * Author: Anthony luu, and Brett Berg
 * Date: 2020/2/28
 *
 * This is a subclass of class Asset that holds appropriate methods and data that defines a Private Investment.
 *
 */

package com.tbf;

public class PrivateInvestment extends Asset{

	private double quarterlyDividend;
	private double baseRateOfReturn;
	private double baseOmegaMeasure;
	private double totalValue;
	private double stake;

	public PrivateInvestment(Integer assetId, String code, String id, String label, double quarterlyDividend
							, double baseRateOfReturn, double baseOmegaMeasure, double totalValue) {
		super(assetId, code, id, label);
		this.quarterlyDividend = quarterlyDividend;
		this.baseRateOfReturn = baseRateOfReturn;
		this.baseOmegaMeasure = baseOmegaMeasure;
		this.totalValue = totalValue;
	}

	PrivateInvestment(PrivateInvestment p) {
		super(p);
		this.assetId = p.assetId;
		this.code = p.code;
		this.id = p.id;
		this.label = p.label;
		this.quarterlyDividend = p.quarterlyDividend;
		this.baseRateOfReturn = p.baseRateOfReturn;
		this.baseOmegaMeasure = p.baseOmegaMeasure;
		this.totalValue = p.totalValue;
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

	public double getStake() {
		return stake;
	}

	public double getAnnualReturn() {
		return ((getValue() * (baseRateOfReturn)) + (4 * quarterlyDividend) * (stake));
	}

	public double getReturnRate() {
		return (getAnnualReturn() / getValue()) * 100;
	}

	public void setPortValue(double portValue) {
		this.stake = portValue;
	}

	public double getRisk() {
		return Math.exp((-125500 / totalValue)) + baseOmegaMeasure;
	}

	public double getValue() {
		return totalValue * (stake);
	}
}