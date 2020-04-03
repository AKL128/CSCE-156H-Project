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

	public Person(Integer personId, String personCode, String brokerData, String firstName, String lastName, Address address) {
		super();
		this.personId = personId;
		this.personCode = personCode;
		this.brokerData = brokerData;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;

		this.portList = new ArrayList<Portfolio>();
	}
	
	public Person(String firstName, String lastName) {
		this(null, null, null, firstName, lastName, null);
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
	
	public static List<String> loadAllEmails() {
		Integer emailPersonId = null;
		String emailName = null;
		List<String> emails = new ArrayList<>();

		Connection conn = null;

		conn = DatabaseInfo.getConnection();
		String query = "select e.emailName, e.personId from Email e";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				
				emailPersonId = rs.getInt("personId");
				String emailPersonIdString = emailPersonId.toString();
				emailName = rs.getString("emailName");
				
				
				emails.add(emailPersonIdString + ", " + emailName);
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
		return emails;
	}
	
	public static List<Person> loadAllPersons() {
		Person p = null;

		List<Person> persons = new ArrayList<>();
		
		List<Address> addressList = Address.loadAllAddresses();
		List<String> emailList = loadAllEmails();

		Connection conn = null;

		conn = DatabaseInfo.getConnection();

		String query = "select p.personId, p.personCode, p.brokerData, p.firstName, p.lastName, p.addressId from Person p "
				+ "join Address a on a.addressId = p.addressId "
				+ "join Email e on e.personId = p.personId";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				
				int personId = rs.getInt("p.personId");
				String personCode = rs.getString("p.personCode");
				String brokerData = rs.getString("p.brokerData");
				String firstName = rs.getString("p.firstName");
				String lastName = rs.getString("p.lastName");
				Address address = null;
				ArrayList<String> emails = new ArrayList<String>();
				for(Address a : addressList) {
					if(a.getAddressId() == rs.getInt("p.addressId")) {
						address = a;
					}
				}
				
				
				String tokens[] = null;
				if(brokerData != null) {
					tokens = brokerData.split(",");
					if(tokens[0].contains("E")) {
						p = new ExpertBroker(personId, personCode, brokerData, firstName, lastName, address);
					} else if(tokens[0].contains("J")) {
						p = new JuniorBroker(personId, personCode, brokerData, firstName, lastName, address);
					}
				} else {
					p = new Person(personId, personCode, brokerData, firstName, lastName, address);
				}
				String emailTokens[] = null;
				for(String em : emailList) {
					emailTokens = em.split(",");
					int emailPersonId = Integer.parseInt(emailTokens[0]);
					if(emailPersonId == rs.getInt("p.personId")) {
						p.addEmail(emailTokens[1]);
					}
				}
				persons.add(p);
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
		return persons;
	}



}