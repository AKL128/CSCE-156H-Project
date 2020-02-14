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
	
	public String getCity() {
		return city;
	}
	
	public String getState() {
		return state;
	}
	
	public int getZip() {
		return zip;
	}
	
	public String getCountry() {
		return country;
	}
}