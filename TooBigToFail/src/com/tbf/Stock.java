/**
 * Author: Anthony luu, and Brett Berg
 * Date: 2020/2/28
 *
 * This is a subclass of class Asset that holds appropriate methods and data that defines a Stock.
 *
 */

package com.tbf;

public class Stock extends Asset{
	private double quarterlyDividend;
	private double baseRateOfReturn;
	private double betaMeasure;
	private String stockSymbol;
	private double sharePrice;
	private double shareNumber;

	public Stock(Integer assetId, String code, String id, String label, double quarterlyDividend
			, double baseRateOfReturn, double betaMeasure, String stockSymbol, double sharePrice) {
		super(assetId, code, id, label);
		this.quarterlyDividend = quarterlyDividend;
		this.baseRateOfReturn = baseRateOfReturn;
		this.betaMeasure = betaMeasure;
		this.stockSymbol = stockSymbol;
		this.sharePrice = sharePrice;
	}

	Stock(Stock s) {
		super(s);
		quarterlyDividend = s.quarterlyDividend;
		baseRateOfReturn = s.baseRateOfReturn;
		betaMeasure = s.betaMeasure;
		stockSymbol = s.stockSymbol;
		sharePrice = s.sharePrice;
	}

	public double getQuarterlyDividend() {
		return quarterlyDividend;
	}

	public double getBaseRateOfReturn() {
		return baseRateOfReturn;
	}

	public double getBetaMeasure() {
		return betaMeasure;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public double getSharePrice() {
		return sharePrice;
	}

	public double getShareNumber() {
		return shareNumber;
	}

	public double getAnnualReturn() {
		return ((getValue() * (baseRateOfReturn / 100)) + (4 * quarterlyDividend) * shareNumber);
	}

	public double getReturnRate() {
		return (getAnnualReturn() / getValue()) * 100;
	}

	public void setPortValue(double portValue) {
		this.shareNumber = portValue;
	}

	public double getRisk() {
		return betaMeasure;
	}

	public double getValue() {
		return sharePrice * shareNumber;
	}
}