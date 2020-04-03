package com.tbf;

import java.util.ArrayList;

public abstract class Broker extends Person{
	
	protected double numberOfAsset;
	protected double totalAnnualReturn;

	public Broker(Integer brokerId, String personCode, String brokerData, String firstName, String lastName, Address address) {
		super(brokerId, personCode, brokerData, firstName, lastName, address);
		this.numberOfAsset = numberOfAsset;
	}
	
	public Broker(String firstName, String lastName) {
		this( null, null, null, firstName, lastName, null);
	}
	
	public abstract double getFee();
	
	public abstract double getCommission();
	
	public abstract void setNumberOfAsset(double asset);
	
	public abstract void setTotalAnnualReturn(double totalAnnualReturn);

}