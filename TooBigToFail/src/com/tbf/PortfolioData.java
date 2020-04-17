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
	public static void removeAllPersons() throws SQLException {
		log.info("Removing all Persons. . .");
		Connection conn = null;
		conn = PortfolioData.getConnection();
 		try {
			conn.setAutoCommit(false);
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
				stmt.addBatch("delete from State");
				stmt.addBatch("delete from Country");
				stmt.addBatch("delete from Address");
				stmt.addBatch("delete from Person");
				stmt.addBatch("delete from Email");
				int[] recordsAffected = stmt.executeBatch();
			} finally {
				if(stmt == null) {
					stmt.close();
				}
			}
			conn.commit();
		} catch(Exception e) {
			conn.rollback();
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
		} catch(Exception e) {
			conn.rollback();
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
				stmt.addBatch("insert into Address (street, city, stateId, zipCode, countryId)"
					+"values ("+street+", "+city+", (select stateId from State where stateName = "+state+"), "+zipCode+", (select countryId from Country where countryName = "+country+"))");
				stmt.addBatch("insert into Person (personCode, brokerData, firstName, lastName, addressId)"
					+"values ("+personCode+", "+brokerData+", "+firstName+", "+lastName+", (select addressId from Address where street = "+street+"))");
				int[] recordsAffected = stmt.executeBatch();
			} finally {
				if(stmt == null) {
					stmt.close();
				}
			}
			conn.commit();
		} catch(Exception e) {
			conn.rollback();
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
		} catch(Exception e) {
			conn.rollback();
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
	public static void removeAllAssets() throws SQLException {
		log.info("Removing all Assets. . .");
		Connection conn = null;
		conn = PortfolioData.getConnection();
		try {
			conn.setAutoCommit(false);
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
				stmt.addBatch("delete from PortfolioAsset");
				stmt.addBatch("delete from Asset");
				int[] recordsAffected = stmt.executeBatch();
			} finally {
				if(stmt == null) {
					stmt.close();
				}
			}
			conn.commit();
		} catch(Exception e) {
			conn.rollback();
		} finally {
			if(conn == null) {
				conn.close();
			}
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
				stmt.addBatch("delete from PortfolioAsset where assetId = (select assetId from Asset where assetCode = "+assetCode+")");
				stmt.addBatch("delete from Asset where assetCode = "+assetCode+"");
				int[] recordsAffected = stmt.executeBatch();
			} finally {
				if(stmt == null) {
					stmt.close();
				}
			}
			conn.commit();
		} catch(Exception e) {
			conn.rollback();
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
		} catch(Exception e) {
			conn.rollback();
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
				} catch(Exception e) {
					conn.rollback();
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
				} catch(Exception e) {
					conn.rollback();
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
	public static void removeAllPortfolios() throws SQLException {
		log.info("Removing all Portfolios. . .");
		Connection conn = null;
		conn = PortfolioData.getConnection();
		try {
			conn.setAutoCommit(false);
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
				stmt.addBatch("delete from PortfolioAsset");
				stmt.addBatch("delete from Portfolio");
				int[] recordsAffected = stmt.executeBatch();
			} finally {
				if(stmt == null) {
					stmt.close();
				}
			}
			conn.commit();
		} catch(Exception e) {
			conn.rollback();
		} finally {
			if(conn == null) {
				conn.close();
			}
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
				stmt.addBatch("delete from PortfolioAsset where portfolioId = (select portfolioId from Portfolio where portCode = "+portfolioCode+")");
				stmt.addBatch("delete from Portfolio where portCode = "+portfolioCode+"");
				int[] recordsAffected = stmt.executeBatch();
			} finally {
				if(stmt == null) {
					stmt.close();
				}
			}
			conn.commit();
		} catch(Exception e) {
			conn.rollback();
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
		} catch(Exception e) {
			conn.rollback();
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
		} catch(Exception e) {
			conn.rollback();
		} finally {
			if(conn == null) {
				conn.close();
			}
		}
	}


}
