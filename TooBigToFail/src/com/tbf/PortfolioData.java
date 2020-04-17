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

	/**
	 * Removes the person record from the database corresponding to the
	 * provided <code>personCode</code>
	 * @param personCode
	 * @throws SQLException
	 */
	public static void removePerson(String personCode) throws SQLException {
		log.info("Removing Person . . .");
		Connection conn = null;
		conn = PortfolioData.getConnection();
		try {
			conn.setAutoCommit(false);
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
				stmt.addBatch("delete from Email where personId = (select personId from Person where personCode = "+personCode+")");
				stmt.addBatch("delete from Address where addressid = (select addressId from Person where personCode = "+personCode+")");
				stmt.addBatch("delete from Person where personCode = "+personCode+"");
				int[] recordsAffected = stmt.executeBatch();
			} finally {
				if(stmt == null) {
					stmt.close();
				}
			}
			conn.commit();
		} catch(SQLException e) {
			conn.rollback();
			throw new RuntimeException(e);
		} finally {
			if(conn == null) {
				conn.close();
			}
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
		String query = "select stateId from state where stateName = (state) values (?)";
		PreparedStatement ps = null;
		ResultSet rs = null;
		int stateId = (Integer) null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, stateName);
			rs = ps.executeQuery();
			stateId = rs.getInt(rs.getInt("stateId"));
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
	
	public static int getCountryId(String countryName) {
		Connection conn = null;
		conn = DatabaseInfo.getConnection();
		String query = "select countryId from country where countryName = (country) values (?)";
		PreparedStatement ps = null;
		ResultSet rs = null;
		int countryId = (Integer) null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, countryName);
			rs = ps.executeQuery();
			countryId = rs.getInt(rs.getInt("countryId"));
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
		String query = "select addressId from address where street = (street) values (?)";
		PreparedStatement ps = null;
		ResultSet rs = null;
		int addressId = (Integer) null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, street);
			rs = ps.executeQuery();
			addressId = rs.getInt(rs.getInt("addressId"));
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
		int stateId = (Integer) null;
		int countryId = (Integer) null;
		try {
			
			stateId = getStateId(state);
			countryId = getCountryId(country);
			
			addAddress(street, city, stateId, zip, countryId);
			ps = conn.prepareStatement(query);
			ps.setString(1, personCode);
			ps.setString(2, brokerData);
			ps.setString(3, firstName);
			ps.setString(4, lastName);
			ps.setInt(5, getAddressId(street));
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
	public static void addEmail(String personCode, String email) throws SQLException {
		log.info("Adding Email to Person. . .");
		Connection conn = null;
		conn = PortfolioData.getConnection();
		try {
			conn.setAutoCommit(false);
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
				String query = "insert into Email (emailName, personId)"
					+ "values ("+email+", (select personId from Person where personCode = "+personCode+"))";
				int recordsAffected = stmt.executeUpdate(query);
			} finally {
				if(stmt == null) {
					stmt.close();
				}
			}
			conn.commit();
		} catch(SQLException e) {
			conn.rollback();
			throw new RuntimeException(e);
		} finally {
			if(conn == null) {
				conn.close();
			}
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
	public static void removeAsset(String assetCode) throws SQLException {
		log.info("Removing Asset. . .");
		Connection conn = null;
		conn = PortfolioData.getConnection();
		try {
			conn.setAutoCommit(false);
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
				stmt.addBatch("update PortfolioAsset set assetId = null where assetId = (select assetId from Asset where assetCode = "+assetCode+")");
				stmt.addBatch("delete from Asset where assetCode = "+assetCode+"");
				int[] recordsAffected = stmt.executeBatch();
			} finally {
				if(stmt == null) {
					stmt.close();
				}
			}
			conn.commit();
		} catch(SQLException e) {
			conn.rollback();
			throw new RuntimeException(e);
		} finally {
			if(conn == null) {
				conn.close();
			}
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
	public static void addDepositAccount(String assetCode, String label, double apr) throws SQLException {
		log.info("Adding Deposit Account. . .");
		Connection conn = null;
		conn = PortfolioData.getConnection();
		try {
			conn.setAutoCommit(false);
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
				String query = "insert into Asset (assetCode, assetType, assetLabel, apr)"
					+ "values ("+assetCode+", D, "+label+", "+apr+")";
				int recordsAffected = stmt.executeUpdate(query);
			} finally {
				if(stmt == null) {
					stmt.close();
				}
			}
			conn.commit();
		} catch(SQLException e) {
			conn.rollback();
			throw new RuntimeException(e);
		} finally {
			if(conn == null) {
				conn.close();
			}
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
			Double baseRateOfReturn, Double baseOmega, Double totalValue) throws SQLException {
				log.info("Adding Private Investment. . .");
				Connection conn = null;
				conn = PortfolioData.getConnection();
				try {
					conn.setAutoCommit(false);
					Statement stmt = null;
					try {
						stmt = conn.createStatement();
						String query = "insert into Asset (assetCode, assetType, assetLabel, quarterlyDividend, baseRateOfReturn, baseOmegaMeasure, totalValue)"
							+ "values ("+assetCode+", P, "+label+", "+quarterlyDividend+", "+baseRateOfReturn+", "+baseOmega+", "+totalValue+")";
						int recordsAffected = stmt.executeUpdate(query);
					} finally {
						if(stmt == null) {
							stmt.close();
						}
					}
					conn.commit();
				} catch(SQLException e) {
					conn.rollback();
					throw new RuntimeException(e);
				} finally {
					if(conn == null) {
						conn.close();
					}
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
			Double baseRateOfReturn, Double beta, String stockSymbol, Double sharePrice) throws SQLException {
				log.info("Adding Stock. . .");
				Connection conn = null;
				conn = PortfolioData.getConnection();
				try {
					conn.setAutoCommit(false);
					Statement stmt = null;
					try {
						stmt = conn.createStatement();
						String query = "insert into Asset (assetCode, assetType, assetLabel, balance, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice)"
							+ "values ("+assetCode+", S, "+label+", "+quarterlyDividend+", "+baseRateOfReturn+", "+beta+", "+stockSymbol+", "+sharePrice+")";
						int recordsAffected = stmt.executeUpdate(query);
					} finally {
						if(stmt == null) {
							stmt.close();
						}
					}
					conn.commit();
				} catch(SQLException e) {
					conn.rollback();
					throw new RuntimeException(e);
				} finally {
					if(conn == null) {
						conn.close();
					}
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
	public static void removePortfolio(String portfolioCode) throws SQLException {
		log.info("Removing Portfolio. . .");
		Connection conn = null;
		conn = PortfolioData.getConnection();
		try {
			conn.setAutoCommit(false);
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
				stmt.addBatch("update PortfolioAsset set portfolioId = null where portfolioId = (select portfolioId from Portfolio where portCode = "+portfolioCode+")");
				stmt.addBatch("delete from Portfolio where portCode = "+portfolioCode+"");
				int[] recordsAffected = stmt.executeBatch();
			} finally {
				if(stmt == null) {
					stmt.close();
				}
			}
			conn.commit();
		} catch(SQLException e) {
			conn.rollback();
			throw new RuntimeException(e);
		} finally {
			if(conn == null) {
				conn.close();
			}
		}
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
		try {
			conn.setAutoCommit(false);
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
				String query = "insert into Portfolio (portCode, ownerId, managerId, beneficiaryId)"
					+ "values ("+portfolioCode+", (select personId from Person where personCode = "+ownerCode+"), (select personId from Person where personCode = "+managerCode+"), (select personId from Person where personCode = "+beneficiaryCode+"))";
				int recordsAffected = stmt.executeUpdate(query);
			} finally {
				if(stmt == null) {
					stmt.close();
				}
			}
			conn.commit();
		} catch(SQLException e) {
			conn.rollback();
			throw new RuntimeException(e);
		} finally {
			if(conn == null) {
				conn.close();
			}
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
	public static void addAsset(String portfolioCode, String assetCode, double value) throws SQLException {
		log.info("Adding Asset to Portfolio. . .");
		Connection conn = null;
		conn = PortfolioData.getConnection();
		try {
			conn.setAutoCommit(false);
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
				String query = "insert into PortfolioAsset (portfolioId, assetId, assetAmount)"
					+ "values ((select portfolioId from Portfolio where portCode = "+portfolioCode+"), (select assetId from Asset where assetCode = "+assetCode+"), "+value+")";
				int recordsAffected = stmt.executeUpdate(query);
			} finally {
				if(stmt == null) {
					stmt.close();
				}
			}
			conn.commit();
		} catch(SQLException e) {
			conn.rollback();
			throw new RuntimeException(e);
		} finally {
			if(conn == null) {
				conn.close();
			}
		}
	}
	


}
