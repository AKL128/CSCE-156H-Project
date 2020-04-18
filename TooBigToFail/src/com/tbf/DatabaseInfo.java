/**
 * Author: Anthony luu, and Brett Berg
 * Date: 2020/3/30
 *
 * This is a class that holds methods for database sql retrieval and connection
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

public class DatabaseInfo {
	
	private static final Logger log = LogManager.getLogger(Address.class);
	
	public static final String url = "jdbc:mysql://cse.unl.edu/aluu";
	public static final String username = "aluu";
	public static final String password = "UFNG1XNWj";
	
	public static Connection getConnection() {
		String DRIVER_CLASS = "com.mysql.jdbc.Driver";
		try {
			Class.forName(DRIVER_CLASS).getDeclaredConstructor().newInstance();
			Connection conn = null;
			conn = DriverManager.getConnection(url, username, password);
			return conn;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	
	}
	
	public static List<Address> loadAllAddresses() {
		Address a = null;

		List<Address> addresses = new ArrayList<>();

		Connection conn = null;

		conn = DatabaseInfo.getConnection();

		String query = "select a.addressId, a.street, a.city, s.stateName, a.zipCode, c.countryName from Address a "
					+ "join State s on s.stateId = a.stateId "
					+ "join Country c on c.countryId = a.countryId";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				
				int addressId = rs.getInt("a.addressId");
				String street = rs.getString("a.street");
				String city = rs.getString("a.city");
				String state = rs.getString("s.stateName");
				String zip = rs.getString("a.zipCode");
				String country = rs.getString("c.countryName");
				
				a = new Address(addressId, street, city, state, zip, country);
				
				addresses.add(a);
			}
		} catch(SQLException e) {
			log.error("Address has something bad in it!", e);
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
	
	public static List<Asset> loadAllAssets() {
		Asset a = null;

		List<Asset> assets = new ArrayList<>();
		
		Connection conn = null;

		conn = DatabaseInfo.getConnection();

		String query = "select assetId, assetCode, assetLabel, assetType,"
				+ " apr, quarterlyDividend, baseRateOfReturn, baseOmegaMeasure, totalValue"
				+ ", betaMeasure, stockSymbol, sharePrice from Asset a ";
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				int assetId = rs.getInt("assetId");
				
				String assetCode = rs.getString("assetCode");
				String assetLabel = rs.getString("assetLabel");
				
				String assetType = rs.getString("assetType");
				
				if(assetType.contains("D")) {
					double apr = rs.getDouble("apr");
					
					a = new DepositAccount(assetId, assetCode, assetType, assetLabel, apr);
				} else if(assetType.contains("P")) {
					double quarterlyDividend = rs.getDouble("quarterlyDividend");
					double baseRateOfReturn = rs.getDouble("baseRateOfReturn");
					double baseOmegaMeasure =  rs.getDouble("baseOmegaMeasure");
					double totalValue = rs.getDouble("totalValue");
					
					a = new PrivateInvestment(assetId, assetCode, assetType, assetLabel, quarterlyDividend, baseRateOfReturn, baseOmegaMeasure, totalValue);
				} else if(assetType.contains("S")) {
					double quarterlyDividend = rs.getDouble("quarterlyDividend");
					double baseRateOfReturn = rs.getDouble("baseRateOfReturn");
					double betaMeasure = rs.getDouble("betaMeasure");
					String stockSymbol = rs.getString("stockSymbol");
					double sharePrice = rs.getDouble("sharePrice");
					
					a = new Stock(assetId, assetCode, assetType, assetLabel, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice);
				}
				assets.add(a);
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
		return assets;
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
		
		List<Address> addressList = loadAllAddresses();
		List<String> emailList = loadAllEmails();

		Connection conn = null;

		conn = DatabaseInfo.getConnection();

		String query = "select p.personId, p.personCode, p.brokerData, p.firstName, p.lastName, p.addressId from Person p ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query2 = "select emailName from Email where personId = (?)";
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
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
					} else {
						p = new Person(personId, personCode, brokerData, firstName, lastName, address);
					}
				} 
				String emailTokens[] = null;
				ps2 = conn.prepareStatement(query2);
				ps2.setInt(1, personId);
				rs2 = ps2.executeQuery();
				while(rs2.next()) {
					String email = rs2.getString("emailName");
					p.addEmail(email);
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
			if(rs2 != null && !rs2.isClosed()) {
				rs2.close();
			}
			if(ps2 != null && !ps2.isClosed()) {
				ps2.close();
			}
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return persons;
	}
	
	public static List<Portfolio> loadAllPortfolios() {
		log.info("Loading All Portfolios. . .");
		Portfolio p = null;

		List<Portfolio> portfolios = new ArrayList<>();
		List<Person> persons = loadAllPersons();
		List<Asset> assets = loadAllAssets();
		

		Connection conn = null;

		conn = DatabaseInfo.getConnection();

		String query = "select ownerId, managerId, beneficiaryId, portfolioId, portCode from Portfolio";

		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		String query2 = "select pa.assetId, pa.assetAmount from PortfolioAsset pa join Asset a on pa.assetId = a.assetId where pa.portfolioId = (?)";

		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				List<Asset> newAssets = new ArrayList<Asset>();;
				
				int portfolioId = rs.getInt("portfolioId");
				
				String portCode = rs.getString("portCode");	
				Person owner = null;
				Broker manager = null;
				Person beneficiary = null;
				
				for(Person per : persons) {
					
					if(per.getPersonId().equals(rs.getInt("ownerId"))) {
						owner = per;
					} else if(per.getPersonId().equals(rs.getInt("managerId"))) {
						manager = (Broker) per;
					} else if(per.getPersonId().equals(rs.getInt("beneficiaryId"))) {
						beneficiary = per;
					} 
				}
				
				ps2 = conn.prepareStatement(query2);
				ps2.setInt(1, portfolioId);
				rs2 = ps2.executeQuery();
				
				while(rs2.next()) {
					double assetAmount = rs2.getDouble("assetAmount");
					int assetId = rs2.getInt("assetId");
					for(Asset a : assets) {
						if(a.getAssetId().equals(assetId)) {
							a.setPortValue(assetAmount);
							newAssets.add(a);
						}
					}
				}
				
				p = new Portfolio(portfolioId, portCode, owner, manager, beneficiary);
				p.setAssetList(newAssets);
				portfolios.add(p);
				
				
			}
			
		} catch(SQLException e) {
			log.error("Failed Preparing Portfolios", e);
			throw new RuntimeException(e);
		}
		
		try{
			if(rs != null && !rs.isClosed()) {
				rs.close();
			}
			if(ps != null && !ps.isClosed()) {
				ps.close();
			}
			if(rs2 != null && !rs2.isClosed()) {
				rs2.close();
			}
			if(ps2 != null && !ps2.isClosed()) {
				ps2.close();
			}
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return portfolios;
	}
	
}