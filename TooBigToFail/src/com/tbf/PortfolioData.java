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
import java.util.ArrayList;
import java.util.List;
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
	 */
	public static void removeAllPersons() {
		log.info("Deleting all Persons. . .");
		Connection conn = null;
		conn = PortfolioData.getConnection();
 		try {
			conn.setAutoCommit(false);
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
				String query = "delete from Person";
				int recordsAffected = stmt.executeUpdate(query);
			} finally {
				if(stmt == null) {
					stmt.close();
				}
			}
			conn.commit();
		} catch(Exception e) {
			connection.rollback();
		} finally {
			if(conn == null) {
				conn.close();
			}
		}
	}

	/**
	 * Removes the person record from the database corresponding to the
	 * provided <code>personCode</code>
	 * @param personCode
	 */
	public static void removePerson(String personCode) {
		log.info("Deleting Person . . .");
		Connection conn = null;
		conn = PortfolioData.getConnection();
		try {
			conn.setAutoCommit(false);
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
				String query = "delete from Person"
					+ "where personCode = " + personCode;
				int recordsAffected = stmt.executeUpdate(query);
			} finally {
				if(stmt == null) {
					stmt.close();
				}
			}
			conn.commit();
		} catch(Exception e) {
			connection.rollback();
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
	 */
	public static void addPerson(String personCode, String firstName, String lastName, String street, String city, String state, String zip, String country, String brokerType, String secBrokerId) {
		log.info("Adding Person . . .");
		Connection conn = null;
		conn = PortfolioData.getConnection();
		String brokerData = brokerType + ", " + secBrokerId;
		try {
			conn.setAutoCommit(false);
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
				stmt.addBatch("insert into Address (street, city, stateId, zipCode, countryId)
					values ("+street+", "+city+", (select stateId from State where stateName = "+state+"), "+zipCode+", (select countryId from Country where countryName = "+country+"))");
				stmt.addBatch("insert into Person (personCode, brokerData, firstName, lastName, addressId)
					values ("+personCode+", "+brokerData+", "+firstName+", "+lastName+", (select addressId from Address where street = "+street+"))");
				int[] recordsAffected = stmt.executeBatch();
			} finally {
				if(stmt == null) {
					stmt.close();
				}
			}
			conn.commit();
		} catch(Exception e) {
			connection.rollback();
		} finally {
			if(conn == null) {
				conn.close();
			}
		}
	}

	/**
	 * Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 * @param personCode
	 * @param email
	 */
	public static void addEmail(String personCode, String email) {}

	/**
	 * Removes all asset records from the database
	 */
	public static void removeAllAssets() {}

	/**
	 * Removes the asset record from the database corresponding to the
	 * provided <code>assetCode</code>
	 * @param assetCode
	 */
	public static void removeAsset(String assetCode) {}

	/**
	 * Adds a deposit account asset record to the database with the
	 * provided data.
	 * @param assetCode
	 * @param label
	 * @param apr
	 */
	public static void addDepositAccount(String assetCode, String label, double apr) {}

	/**
	 * Adds a private investment asset record to the database with the
	 * provided data.
	 * @param assetCode
	 * @param label
	 * @param quarterlyDividend
	 * @param baseRateOfReturn
	 * @param baseOmega
	 * @param totalValue
	 */
	public static void addPrivateInvestment(String assetCode, String label, Double quarterlyDividend,
			Double baseRateOfReturn, Double baseOmega, Double totalValue) {}

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
	 */
	public static void addStock(String assetCode, String label, Double quarterlyDividend,
			Double baseRateOfReturn, Double beta, String stockSymbol, Double sharePrice) {}

	/**
	 * Removes all portfolio records from the database
	 */
	public static void removeAllPortfolios() {}

	/**
	 * Removes the portfolio record from the database corresponding to the
	 * provided <code>portfolioCode</code>
	 * @param portfolioCode
	 */
	public static void removePortfolio(String portfolioCode) {}

	/**
	 * Adds a portfolio records to the database with the given data.  If the portfolio has no
	 * beneficiary, the <code>beneficiaryCode</code> will be <code>null</code>
	 * @param portfolioCode
	 * @param ownerCode
	 * @param managerCode
	 * @param beneficiaryCode
	 */
	public static void addPortfolio(String portfolioCode, String ownerCode, String managerCode, String beneficiaryCode) {}

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
	 */
	public static void addAsset(String portfolioCode, String assetCode, double value) {}


}
