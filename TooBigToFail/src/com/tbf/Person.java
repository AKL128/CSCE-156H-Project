package com.tbf;

import java.util.ArrayList;
import java.util.List;

public class Person {
	private String personCode;
	private String brokerData;
	private String firstName;
	private String lastName;
	private Address address;
	private ArrayList<String> email;

	public Person(String personCode, String brokerData, String firstName, String lastName, Address address, ArrayList<String> email) {
		super();
		this.personCode = personCode;
		this.brokerData = brokerData;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
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


}
