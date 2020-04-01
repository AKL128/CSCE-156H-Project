package com.tbf;

import java.util.ArrayList;

public abstract class Broker extends Person{

	protected int brokerId;
	protected double numberOfAsset;
	protected double totalAnnualReturn;

	public Broker(int brokerId, String personCode, String brokerData, String firstName, String lastName, Address address,
			ArrayList<String> email) {
		super(personId, personCode, brokerData, firstName, lastName, address, email);

	}

	public abstract double getFee();

	public abstract double getCommission();

	public abstract void setNumberOfAsset(double asset);

	public abstract void setTotalAnnualReturn(double totalAnnualReturn);

}
