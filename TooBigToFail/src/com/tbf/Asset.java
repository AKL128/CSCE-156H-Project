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

public abstract class Asset{
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

			String query = "select * from Asset a "
					+ "join PortfolioAsset pa on pa.assetId = a.assetId";
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
				
				double portValue = rs.getDouble("assetAmount");
				
				if(assetType.contains("D")) {
					double apr = rs.getDouble("apr");
					
					a = new DepositAccount(assetId, assetCode, assetType, assetLabel, apr);
					a.setPortValue(portValue);
				} else if(assetType.contains("P")) {
					double quarterlyDividend = rs.getDouble("quarterlyDividend");
					double baseRateOfReturn = rs.getDouble("baseRateOfReturn");
					double baseOmegaMeasure =  rs.getDouble("baseOmegaMeasure");
					double totalValue = rs.getDouble("totalValue");
					
					a = new PrivateInvestment(assetId, assetCode, assetType, assetLabel, quarterlyDividend, baseRateOfReturn, baseOmegaMeasure, totalValue);
					a.setPortValue(portValue);
				} else if(assetType.contains("S")) {
					double quarterlyDividend = rs.getDouble("quarterlyDividend");
					double baseRateOfReturn = rs.getDouble("baseRateOfReturn");
					double betaMeasure = rs.getDouble("betaMeasure");
					String stockSymbol = rs.getString("stockSymbol");
					double sharePrice = rs.getDouble("sharePrice");
					
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