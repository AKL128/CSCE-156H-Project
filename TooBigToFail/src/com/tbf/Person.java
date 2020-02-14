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

	public void setPersonCode(String newPersonCode) {
		this.personCode = newPersonCode;
	}

	public String getBrokerData() {
		return brokerData;
	}

	public void setBrokerData(String newBrokerData) {
		this.brokerData = newBrokerData;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name newName) {
		this.name = newName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address newAddress) {
		this.address = newAddress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String newEmail) {
		this.email = newEmail;
	}
}
