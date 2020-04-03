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

public class Address {
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
	
	public static List<Address> loadAllAddresses() {
		Address a = null;

		List<Address> addresses = new ArrayList<>();

		String DRIVER_CLASS = "com.mysql.jdbc.Driver";
		try {
			Class.forName(DRIVER_CLASS).getDeclaredConstructor().newInstance();
		} catch(Exception e) {
			throw new RuntimeException(e);
		}

		Connection conn = null;
		String url = DatabaseInfo.url;
		String username = DatabaseInfo.username;
		String password = DatabaseInfo.password;

		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}

			String query = "select * from Address a "
					+ "join State s on s.stateId = a.stateId "
					+ "join Country c on c.countryId = a.countryId";
			PreparedStatement ps = null;
			ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				
				int addressId = rs.getInt("addressId");
				String street = rs.getString("street");
				String city = rs.getString("city");
				String state = rs.getString("stateName");
				String zip = rs.getString("zipCode");
				String country = rs.getString("countryName");
				
				a = new Address(addressId, street, city, state, zip, country);
				
				addresses.add(a);
			}
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		try{
			if(rs != null && !rs.isClosed()) {
				rs.close();
			}
			if(ps != null && !ps.isClosed()) {
				ps.close();
			}
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return addresses;
	}

}