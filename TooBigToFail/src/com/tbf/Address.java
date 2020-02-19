package com.tbf;

public class Address {
	private String street;
	private String city;
	private String state;
	private int zip;
	private String country;

	public Address(String steet, String city, String state, int zip, String coutnry) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String newStreet) {
		this.street = newStreet;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String newCity) {
		this.city = newCity;
	}

	public String getState() {
		return state;
	}

	public void setState(String newState) {
		this.state = newState;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int newZip) {
		this.zip = newZip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String newCountry) {
		this.country = newCountry;
	}
}
