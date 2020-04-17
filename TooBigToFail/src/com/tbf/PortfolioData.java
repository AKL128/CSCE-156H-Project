package com.tbf;

/**
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 *
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import org.apache.logging.log4j.Logger;

import org.apache.logging.log4j.LogManager;


public class PortfolioData {

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

	/**
	 * Method that removes every person record from the database
	 * @throws SQLException
	 */
	public static void removeAllPersons() {
		log.info("Removing all Persons. . .");
		Connection conn = null;
		conn = PortfolioData.getConnection();
		
		String query = "delete From Email";
		String query2 = "delete from Person";
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		try {
			ps = conn.prepareStatement(query);
			ps.executeUpdate();
			ps2 = conn.prepareStatement(query2);
			ps2.executeUpdate();

		} catch(SQLException e) {
			throw new RuntimeException(e);
		} 
		try {
			if(ps2 != null && !ps2.isClosed()) {
				ps2.close();
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
	}
	
	public static int getPersonId(String personCode) {
		Connection conn = null;
		conn = DatabaseInfo.getConnection();
		String query = "select personId from Person where personCode = (?)";
		PreparedStatement ps = null;
		ResultSet rs = null;
		int personId;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, personCode);
			rs = ps.executeQuery();
			personId = rs.getInt("personId");
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
		return personId;
	}

	/**
	 * Removes the person record from the database corresponding to the
	 * provided <code>personCode</code>
	 * @param personCode
	 * @throws SQLException
	 */
	public static void removePerson(String personCode) {
		log.info("Removing Person . . .");
		Connection conn = null;
		conn = PortfolioData.getConnection();	
		String query = "delete from Email where personId = (?)";
		PreparedStatement ps = null;
		String query2 = "delete from Person where personCode = (?)";
		PreparedStatement ps2 = null;
		
		int personId;
		try {
			
			personId = getPersonId(personCode);
			ps = conn.prepareStatement(query);
			ps.setInt(1, personId);
			ps.executeUpdate();
			
			ps2 = conn.prepareStatement(query2);
			ps2.setString(1, personCode);
			ps2.executeUpdate();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
				
		try {
			if(ps2 != null && !ps2.isClosed()) {
				ps2.close();
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
	}

	/**
	 * Method to add a person record to the database with the provided data. The
	 * <code>brokerType</code> will either be "E" or "J" (Expert or Junior) or
	 * <code>null</code> if the person is not a broker.
	 * @param personCode
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 * @param brokerType
	 * @throws SQLException
	 */
	
	public static int getStateId(String stateName) {
		Connection conn = null;
		conn = DatabaseInfo.getConnection();
		String query = "select stateId from State where stateName = (?)";
		PreparedStatement ps = null;
		ResultSet rs = null;
		int stateId;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, stateName);
			rs = ps.executeQuery();
			stateId = rs.getInt("stateId");
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
		return stateId;
	}
	
	public static void addState(String stateName) {
		Connection conn = null;
		conn = DatabaseInfo.getConnection();
		String query = "insert into State (stateName) values (?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, stateName);
			ps.executeUpdate();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		try {
			if(ps != null && !ps.isClosed()) {
				ps.close();
			}
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	public static int getCountryId(String countryName) {
		Connection conn = null;
		conn = DatabaseInfo.getConnection();
		String query = "select countryId from Country where countryName = (?)";
		PreparedStatement ps = null;
		ResultSet rs = null;
		int countryId;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, countryName);
			rs = ps.executeQuery();
			countryId = rs.getInt("countryId");
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
		return countryId;
	}
	
	public static void addCountry(String countryName) {
		Connection conn = null;
		conn = DatabaseInfo.getConnection();
		String query = "insert into Country (countryName) values (?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, countryName);
			ps.executeUpdate();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		try {
			if(ps != null && !ps.isClosed()) {
				ps.close();
			}
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void addAddress(String street, String city, int stateId, String zipCode, int countryId) {
		Connection conn = null;
		conn = DatabaseInfo.getConnection();
		String query = "insert into Address (street, city, stateId, zipCode, countryId) values (?, ?, ?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setInt(3, stateId);
			ps.setString(4, zipCode);
			ps.setInt(5, countryId);
			ps.executeUpdate();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		try{
			if(ps != null && !ps.isClosed()) {
				ps.close();
			}
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static int getAddressId(String street) {
		Connection conn = null;
		conn = DatabaseInfo.getConnection();
		String query = "select addressId from Address where street = (?)";
		PreparedStatement ps = null;
		ResultSet rs = null;
		int addressId;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, street);
			rs = ps.executeQuery();
			addressId = rs.getInt("addressId");
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
		return addressId;
	}
	
	
	public static void addPerson(String personCode, String firstName, String lastName, String street, String city, String state, String zip, String country, String brokerType, String secBrokerId) {
		log.info("Adding Person . . .");
		Connection conn = null;
		conn = PortfolioData.getConnection();
		String brokerData = brokerType + "," + secBrokerId;
		
		String query = "insert into Person (personCode, brokerData, firstName, lastName, addressId) values (?, ?, ?, ?, ?)";
		PreparedStatement ps = null;
		int stateId;
		int countryId;
		try {
			addState(state);
			addCountry(country);
			stateId = getStateId(state);
			countryId = getCountryId(country);
			
			addAddress(street, city, stateId, zip, countryId);
			ps = conn.prepareStatement(query);
			ps.setString(1, personCode);
			ps.setString(2, brokerData);
			ps.setString(3, firstName);
			ps.setString(4, lastName);
			ps.setInt(5, getAddressId(street));
			ps.executeUpdate();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		try {
			if(ps != null && !ps.isClosed()) {
				ps.close();
			}
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 * @param personCode
	 * @param email
	 * @throws SQLException
	 */
	public static void addEmail(String personCode, String email) {
		log.info("Adding Email to Person. . .");
		Connection conn = null;
		conn = DatabaseInfo.getConnection();
		String query = "insert into Email (emailName, personId) values (?, ?)";
		PreparedStatement ps = null;
		try {
			
			ps = conn.prepareStatement(query);
			ps.setString(1, personCode);
			ps.setString(2, email);
			ps.executeUpdate();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		try {
			if(ps != null && !ps.isClosed()) {
				ps.close();
			}
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Removes all asset records from the database
	 * @throws SQLException
	 */
	public static void removeAllAssets() {
		log.info("Removing all Assets. . .");
		Connection conn = null;
		conn = DatabaseInfo.getConnection();
		
		String query = "delete from PortfolioAsset";
		PreparedStatement ps = null;
		String query2 = "delete from Asset";
		PreparedStatement ps2 = null;
		try {
			ps = conn.prepareStatement(query);
			ps.executeUpdate();
			ps2 = conn.prepareStatement(query2);
			ps2.executeUpdate();

		} catch(SQLException e) {
			throw new RuntimeException(e);
		} 
		try {
			if(ps2 != null && !ps2.isClosed()) {
				ps2.close();
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
	}

	/**
	 * Removes the asset record from the database corresponding to the
	 * provided <code>assetCode</code>
	 * @param assetCode
	 * @throws SQLException
	 */
	public static void removeAsset(String assetCode) {
		log.info("Removing Asset. . .");
		Connection conn = null;
		conn = DatabaseInfo.getConnection();
		String query = "update PortfolioAsset set assetId = null where assetId = (?)";
		PreparedStatement ps = null;
		String query2 = "delete from Asset where assetCode = (?)";
		PreparedStatement ps2 = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, assetCode);
			ps.executeUpdate();
			
			ps2 = conn.prepareStatement(query2);
			ps2.setString(1, assetCode);
			ps2.executeUpdate();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
				
		try {
			if(ps2 != null && !ps2.isClosed()) {
				ps2.close();
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
	}

	/**
	 * Adds a deposit account asset record to the database with the
	 * provided data.
	 * @param assetCode
	 * @param label
	 * @param apr
	 * @throws SQLException
	 */
	public static void addDepositAccount(String assetCode, String label, double apr) {
		log.info("Adding Deposit Account. . .");
		Connection conn = null;
		conn = DatabaseInfo.getConnection();
		String query = "insert into Asset (assetCode, assetType, assetLabel, apr) values (?, ?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, assetCode);
			ps.setString(2, "D");
			ps.setString(3, label);
			ps.setDouble(4, apr);
			ps.executeUpdate();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		try {
			if(ps != null && !ps.isClosed()) {
				ps.close();
			}
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adds a private investment asset record to the database with the
	 * provided data.
	 * @param assetCode
	 * @param label
	 * @param quarterlyDividend
	 * @param baseRateOfReturn
	 * @param baseOmega
	 * @param totalValue
	 * @throws SQLException
	 */
	public static void addPrivateInvestment(String assetCode, String label, Double quarterlyDividend,
			Double baseRateOfReturn, Double baseOmega, Double totalValue) {
				log.info("Adding Private Investment. . .");
				Connection conn = null;
				conn = PortfolioData.getConnection();
				String query = "insert into Asset (assetCode, assetType, assetLabel, quarterlyDividend, baseRateOfReturn, baseOmegaMeasure, totalValue) "
						+ "values (?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement ps = null;
				try {
					ps = conn.prepareStatement(query);
					ps.setString(1, assetCode);
					ps.setString(2, "P");
					ps.setString(3, label);
					ps.setDouble(4, quarterlyDividend);
					ps.setDouble(5,  baseRateOfReturn);
					ps.setDouble(6, baseOmega);
					ps.setDouble(7, totalValue);
					ps.executeUpdate();
				} catch(SQLException e) {
					throw new RuntimeException(e);
				}
				
				try {
					if(ps != null && !ps.isClosed()) {
						ps.close();
					}
					if(conn != null && !conn.isClosed()) {
						conn.close();
					}
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
		}

	/**
	 * Adds a stock asset record to the database with the
	 * provided data.
	 * @param assetCode
	 * @param label
	 * @param quarterlyDividend
	 * @param baseRateOfReturn
	 * @param beta
	 * @param stockSymbol
	 * @param sharePrice
	 * @throws SQLException
	 */
	public static void addStock(String assetCode, String label, Double quarterlyDividend,
			Double baseRateOfReturn, Double beta, String stockSymbol, Double sharePrice) {
				log.info("Adding Stock. . .");
				Connection conn = null;
				conn = PortfolioData.getConnection();
				String query = "insert into Asset (assetCode, assetType, assetLabel, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice)"
						+ "values (?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement ps = null;
				try {
					ps = conn.prepareStatement(query);
					ps.setString(1, assetCode);
					ps.setString(2, "S");
					ps.setString(3, label);
					ps.setDouble(4, quarterlyDividend);
					ps.setDouble(5, baseRateOfReturn);
					ps.setDouble(6, beta);
					ps.setString(7, stockSymbol);
					ps.setDouble(8, sharePrice);
					ps.executeUpdate();
				} catch(SQLException e) {
					throw new RuntimeException(e);
				}
				
				try {
					if(ps != null && !ps.isClosed()) {
						ps.close();
					}
					if(conn != null && !conn.isClosed()) {
						conn.close();
					}
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}

	/**
	 * Removes all portfolio records from the database
	 * @throws SQLException
	 */
	public static void removeAllPortfolios() {
		log.info("Removing all Portfolios. . .");
		Connection conn = null;
		conn = DatabaseInfo.getConnection();
		
		String query = "delete from PortfolioAsset";
		PreparedStatement ps = null;
		String query2 = "delete from Portfolio";
		PreparedStatement ps2 = null;
		
		try {
			ps = conn.prepareStatement(query);
			ps.executeUpdate();
			ps2 = conn.prepareStatement(query2);
			ps2.executeUpdate();

		} catch(SQLException e) {
			throw new RuntimeException(e);
		} 
		try {
			if(ps2 != null && !ps2.isClosed()) {
				ps2.close();
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
	}

	/**
	 * Removes the portfolio record from the database corresponding to the
	 * provided <code>portfolioCode</code>
	 * @param portfolioCode
	 * @throws SQLException
	 */
	public static void removePortfolio(String portfolioCode) {
		log.info("Removing Portfolio. . .");
		Connection conn = null;
		conn = PortfolioData.getConnection();
		String query = "update PortfolioAsset set portfolioId = null where portfolioId = (?)";
		String query2 = "delete from Portfolio where portCode = (?)";
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, portfolioCode);
			ps.executeUpdate();
			
			ps2 = conn.prepareStatement(query2);
			ps2.setString(1, portfolioCode);
			ps2.executeUpdate();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}

		try {
			if(ps2 != null && !ps2.isClosed()) {
				ps2.close();
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
	}
	
	public static int getPortfolioId(String portfolioCode) {
		Connection conn = null;
		conn = DatabaseInfo.getConnection();
		String query = "select portfolioId from Portfolio where portCode = (?)";
		PreparedStatement ps = null;
		ResultSet rs = null;
		int portfolioId;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, portfolioCode);
			rs = ps.executeQuery();
			portfolioId = rs.getInt("portfolioId");
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
		return portfolioId;
	}
	
	public static int getAssetId(String assetCode) {
		Connection conn = null;
		conn = DatabaseInfo.getConnection();
		String query = "select assetId from Asset where assetCode = (?)";
		PreparedStatement ps = null;
		ResultSet rs = null;
		int assetId;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, assetCode);
			rs = ps.executeQuery();
			assetId = rs.getInt("assetId");
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
		return assetId;
	}
	


	/**
	 * Adds a portfolio records to the database with the given data.  If the portfolio has no
	 * beneficiary, the <code>beneficiaryCode</code> will be <code>null</code>
	 * @param portfolioCode
	 * @param ownerCode
	 * @param managerCode
	 * @param beneficiaryCode
	 * @throws SQLException
	 */
	public static void addPortfolio(String portfolioCode, String ownerCode, String managerCode, String beneficiaryCode) throws SQLException {
		log.info("Adding Portfolio. . .");
		Connection conn = null;
		conn = PortfolioData.getConnection();
		String query = "insert into Portfolio (portCode, ownerId, managerId, beneficiaryId)"
				+ "values (?, ?, ?, ?)";
		PreparedStatement ps = null;
		
		int ownerId;
		int managerId;
		int beneficiaryId;
		
		try {
			
			ownerId = getPersonId(ownerCode);
			managerId = getPersonId(managerCode);
			beneficiaryId = getPersonId(beneficiaryCode);
			
			ps = conn.prepareStatement(query);
			ps.setString(1, portfolioCode);
			ps.setInt(2, ownerId);
			ps.setInt(3, managerId);
			ps.setInt(4, beneficiaryId);
			ps.executeUpdate();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		try {
			if(ps != null && !ps.isClosed()) {
				ps.close();
			}
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Associates the asset record corresponding to <code>assetCode</code> with
	 * the portfolio corresponding to the provided <code>portfolioCode</code>.
	 * The third parameter, <code>value</code> is interpreted as a <i>balance</i>,
	 * <i>number of shares</i> or <i>stake percentage</i> (on the scale [0, 1])
	 * depending on the type of asset the <code>assetCode</code> is associated
	 * with.
	 *
	 * @param portfolioCode
	 * @param assetCode
	 * @param value
	 * @throws SQLException
	 */
	public static void addAsset(String portfolioCode, String assetCode, double value) {
		log.info("Adding Asset to Portfolio. . .");
		Connection conn = null;
		conn = DatabaseInfo.getConnection();
		String query = "insert into PortfolioAsset (portfolioId, assetId, assetAmount)"
				+ "values (?, ?, ?)";
		PreparedStatement ps = null;
		int portfolioId;
		int assetId;
		try {
			portfolioId = getPortfolioId(portfolioCode);
			assetId = getAssetId(assetCode);
			
			ps = conn.prepareStatement(query);
			ps.setInt(1, portfolioId);
			ps.setInt(2, assetId);
			ps.setDouble(3, value);
			ps.executeUpdate();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		try {
			if(ps != null && !ps.isClosed()) {
				ps.close();
			}
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	


}
