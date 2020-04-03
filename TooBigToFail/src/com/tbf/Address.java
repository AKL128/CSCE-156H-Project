/**
 * Author: Anthony luu, and Brett Berg
 * Date: 2020/2/28
 *
 * This is a class for Address that holds all data for a full address.
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

public class Address {
	
	private static final Logger log = LogManager.getLogger(Address.class);
	
	private Integer addressId;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String country;

	public Address(Integer addressId, String street, String city, String state, String zip, String country) {
		super();
		this.addressId = addressId;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
	}

	public Integer getAddressId() {
		return addressId;
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

	public String getZip() {
		return zip;
	}

	public String getCountry() {
		return country;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%s, %s, %s, %s, %s", street, city, state, zip, country));
		return sb.toString();
	}
	
	

}