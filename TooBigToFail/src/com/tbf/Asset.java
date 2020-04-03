/**
 * Author: Anthony luu, and Brett Berg
 * Date: 2020/2/28
 *
 * This is a abstract super class for Asset that is a parent class that holds all common data between subclasses of Asset.
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

public abstract class Asset{
	
	private static final Logger log = LogManager.getLogger(Asset.class);
	
	protected Integer assetId;
	protected String code;
	protected String id;
	protected String label;

	public Asset(Integer assetId, String code, String id, String label) {
		super();
		this.assetId = assetId;
		this.code = code;
		this.id = id;
		this.label = label;
	}

	Asset(Asset a) {
		assetId = a.assetId;
		code = a.code;
		id = a.id;
		label = a.label;
	}

	public Integer getAssetId() {
		return assetId;
	}

	public String getCode() {
		return code;
	}

	public String getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public abstract double getAnnualReturn();

	public abstract double getReturnRate();

	public abstract void setPortValue(double portValue);

	public double getRisk() {
		return 0.0;
	}

	public double getValue() {
		return 0;
	}
	
	public static List<Asset> loadAllAssets() {
		Asset a = null;

		List<Asset> assets = new ArrayList<>();
		
		Connection conn = null;

		conn = DatabaseInfo.getConnection();

		String query = "select a.assetId, a.assetCode, a.assetLabel, a.assetType, pa.assetAmount,"
				+ " a.apr, a.quarterlyDividend, a.baseRateOfReturn, a.baseOmegaMeasure, a.totalValue"
				+ ", a.betaMeasure, a.stockSymbol, a.sharePrice from Asset a "
				+ "join PortfolioAsset pa on pa.assetId = a.assetId";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				
				int assetId = rs.getInt("a.assetId");
				String assetCode = rs.getString("a.assetCode");
				String assetLabel = rs.getString("a.assetLabel");
				
				String assetType = rs.getString("a.assetType");
				
				double portValue = rs.getDouble("pa.assetAmount");
				
				if(assetType.contains("D")) {
					double apr = rs.getDouble("a.apr");
					
					a = new DepositAccount(assetId, assetCode, assetType, assetLabel, apr);
					a.setPortValue(portValue);
				} else if(assetType.contains("P")) {
					double quarterlyDividend = rs.getDouble("a.quarterlyDividend");
					double baseRateOfReturn = rs.getDouble("a.baseRateOfReturn");
					double baseOmegaMeasure =  rs.getDouble("a.baseOmegaMeasure");
					double totalValue = rs.getDouble("a.totalValue");
					
					a = new PrivateInvestment(assetId, assetCode, assetType, assetLabel, quarterlyDividend, baseRateOfReturn, baseOmegaMeasure, totalValue);
					a.setPortValue(portValue);
				} else if(assetType.contains("S")) {
					double quarterlyDividend = rs.getDouble("a.quarterlyDividend");
					double baseRateOfReturn = rs.getDouble("a.baseRateOfReturn");
					double betaMeasure = rs.getDouble("a.betaMeasure");
					String stockSymbol = rs.getString("a.stockSymbol");
					double sharePrice = rs.getDouble("a.sharePrice");
					
					a = new Stock(assetId, assetCode, assetType, assetLabel, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice);
					a.setPortValue(portValue);
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



}