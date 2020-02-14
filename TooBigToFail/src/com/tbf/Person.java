package com.tbf;

public class Person {
	private String personCode;
	private String brokerData;
	private Name name;
	private Address address;
	private String email;
	
	public Person(String personCode, String brokerData, Name name, Address address, String email) {
		super();
		this.personCode = personCode;
		this.brokerData = brokerData;
		this.name = name;
		this.address = address;
		this.email = email;
	}
	
	public String getPersonCode() {
		return personCode;
	}
	
	public String getBrokerData() {
		return brokerData;
	}
	
	public Name getName() {
		return name;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public String getEmail() {
		return email;
	}
}