/**
 * Author: Anthony luu, and Brett Berg
 * Date: 2020/3/29
 *
 * This is a abstract super class for Broker that is a parent class that holds all common data between subclasses of Person.
 *
 */

package com.tbf;

import java.util.ArrayList;
import java.util.List;

public abstract class Broker extends Person{
	
	protected double numberOfAsset;
	protected double totalAnnualReturn;

	public Broker(Integer personId, String personCode, String brokerData, String firstName, String lastName, Address address, List<String> email) {
		super(personId, personCode, brokerData, firstName, lastName, address, email);
		this.numberOfAsset = numberOfAsset;
	}
	

	
	public Broker(Integer personId, String personCode, String brokerData, String firstName, String lastName,
			Address address) {
		super(personId, personCode, brokerData, firstName, lastName, address, null);
		this.numberOfAsset = numberOfAsset;
	}



	public abstract double getFee();
	
	public abstract double getCommission();
	
	public abstract void setNumberOfAsset(double asset);
	
	public abstract void setTotalAnnualReturn(double totalAnnualReturn);

}