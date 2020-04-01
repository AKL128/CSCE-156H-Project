/**
 * Author: Anthony luu, and Brett Berg
 * Date: 2020/2/28
 *
 * This is a abstract super class for Person that is a parent class that holds all common data between subclasses of Person.
 * 
 */

package com.tbf;

import java.util.ArrayList;
import java.util.List;

public class Person {
	protected String personCode;
	protected String brokerData;
	protected String firstName;
	protected String lastName;
	protected Address address;
	protected ArrayList<String> email;
	protected List<Portfolio> portList;
	
//	protected double numberOfAsset;
//	protected double totalAnnualReturn;

	public Person(String personCode, String brokerData, String firstName, String lastName, Address address, ArrayList<String> email) {
		super();
		this.personCode = personCode;
		this.brokerData = brokerData;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		
		this.portList = new ArrayList<Portfolio>();
	}
	
	public void addPortfolio(Portfolio portfolio) {
		this.portList.add(portfolio);
	}

	public String getPersonCode() {
		return personCode;
	}

	public String getBrokerData() {
		return brokerData;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public Address getAddress() {
		return address;
	}


	public ArrayList<String> getEmail() {
		return email;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%s, %s\n%s\n%s\n", lastName, firstName, email, address.toString()));
		sb.append("---------------------------------------------\n");
		return sb.toString();
	}
	
	public String getFullName() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%s, %s", lastName, firstName));
		return sb.toString();
	}
	
//	public abstract double getFee();
//	
//	public abstract double getCommission();
//	
//	public abstract void setNumberOfAsset(double asset);
//	
//	public abstract void setTotalAnnualReturn(double totalAnnualReturn);

}
