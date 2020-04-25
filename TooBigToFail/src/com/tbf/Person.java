/**
 * Author: Anthony luu, and Brett Berg
 * Date: 2020/2/28
 *
 * This is a abstract super class for Person that is a parent class that holds all common data between subclasses of Person.
 *
 */

package com.tbf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Person {
	
	private static final Logger log = LogManager.getLogger(Person.class);
	
	protected Integer personId;
	protected String personCode;
	protected String brokerData;
	protected String firstName;
	protected String lastName;
	protected Address address;
	protected List<String> email =  new ArrayList<String>();
	protected List<Portfolio> portList;

//	protected double numberOfAsset;
//	protected double totalAnnualReturn;

	public Person(Integer personId, String personCode, String brokerData, String firstName, String lastName, Address address, List<String> email) {
		super();
		this.personId = personId;
		this.personCode = personCode;
		this.brokerData = brokerData;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;

		this.portList = new ArrayList<Portfolio>();
	}
	
	public Person(Integer personId, String personCode, String firstName, String lastName, Address address, List<String> email) {
		super();
		this.personId = personId;
		this.personCode = personCode;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		
		this.portList = new ArrayList<Portfolio>();
	}



	public Person(Integer personId, String personCode, String firstName, String lastName, Address address) {
		super();
		this.personId = personId;
		this.personCode = personCode;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		
		this.portList = new ArrayList<Portfolio>();
	}

	public void addPortfolio(Portfolio portfolio) {
		this.portList.add(portfolio);
	}

	public Integer getPersonId() {
		return personId;
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


	public List<String> getEmail() {
		return email;
	}
	
	public void setEmailList(List<String> emails) {
		this.email = emails;
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
	
	public void addEmail(String emailName) {
		this.email.add(emailName);
	}
	
	



}