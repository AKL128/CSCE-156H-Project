package com.tbf;

public class Stock extends Asset{
	private double quarterlyDividend;
	private double baseRateOfReturn;
	private double betaMeasure;
	private String stockSymbol;
	private double sharePrice;
	
	public Stock(String code, String id, String label, double quarterlyDividend
			, double baseRateOfReturn, double betaMeasure, String stockSymbol, double sharePrice) {
		super(code, id, label);
		this.quarterlyDividend = quarterlyDividend;
		this.baseRateOfReturn = baseRateOfReturn;
		this.betaMeasure = betaMeasure;
		this.stockSymbol = stockSymbol;
		this.sharePrice = sharePrice;
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
}