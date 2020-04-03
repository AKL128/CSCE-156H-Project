/**
 * Author: Anthony luu, and Brett Berg
 * Date: 2020/2/28
 *
 * This is a class for Portfolio that holds data of a client and their assets.
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

public class Portfolio implements Comparable<Portfolio>{
	private Integer portfolioId;
	private String portCode;
	private Person owner;
	private Broker manager;
	private Person beneficiary;
	private List<Asset> assetList = new ArrayList<Asset>();

	public int compareTo(Portfolio that) {
		return this.getOwner().getLastName().compareTo(that.getOwner().getLastName());
	}

	public Portfolio(Integer portfolioId, String portCode, Person owner, Broker manager,
	 Person beneficiary) {
		 this.portfolioId = portfolioId;
		 this.portCode = portCode;
		 this.owner = owner;
		 this.manager = manager;
		 this.beneficiary = beneficiary;
	}

	public Integer getPortfolioId() {
		return portfolioId;
	}

	public String getPortCode() {
		return portCode;
	}

	public Person getOwner() {
		return owner;
	}

	public Broker getManager() {
		return manager;
	}

	public Person getBeneficiary() {
		return beneficiary;
	}

	public List<Asset> getAssetList() {
		return assetList;
	}
	

	public double getTotalValue() {
		double totalValue = 0;
		for(Asset a : assetList) {
			totalValue += a.getValue();
		}
		return totalValue;
	}

	public double getAggregateRisk() {
		double aggregateRisk = 0;
		for(Asset a : assetList) {
			aggregateRisk += ((a.getRisk() * a.getValue()) / getTotalValue());
		}
		return aggregateRisk;
	}

	public double getTotalAnnualReturn() {
		double totalAnnualReturn = 0;
		for(Asset a : assetList) {
			totalAnnualReturn += a.getAnnualReturn();
		}
		return totalAnnualReturn;
	}
	

	public void addAsset(Asset a) {
		this.assetList.add(a);
	}

	public static List<Portfolio> loadAllPortfolios() {
		Portfolio p = null;

		List<Portfolio> portfolios = new ArrayList<>();
		List<Person> persons = Person.loadAllPersons();
		List<Asset> assets = Asset.loadAllAssets();

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

			String query = "select o.personId, m.personId, b.personId, po.portfolioId, po.portCode from Portfolio po "
					+ "join Person o on o.personId = po.ownerId "
					+ "join Person m on m.personId = po.managerId "
					+ "join Person b on b.personId = po.beneficiaryId";
			PreparedStatement ps = null;
			ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				 
				int portfolioId = rs.getInt("po.portfolioId");
				String portCode = rs.getString("po.portCode");	
				Person owner = null;
				Broker manager = null;
				Person beneficiary = null;
				
				for(Person per : persons) {
					if(per.getPersonId().equals(rs.getInt("o.personId"))) {
						owner = per;
					} else if(per.getPersonId().equals(rs.getInt("m.personId"))) {
						manager = (Broker) per;
					} else if(per.getPersonId().equals(rs.getInt("b.personId"))) {
						beneficiary = per;
					} 
				}
				
				p = new Portfolio(portfolioId, portCode, owner, manager, beneficiary);
				

				
				portfolios.add(p);
			}
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		query = "select a.assetId, po.portfolioId from Portfolio po "
				+ "join PortfolioAsset pa on pa.portfolioId = po.portfolioId "
				+ "join Asset a on a.assetId = pa.assetId";
		
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				for(Portfolio port : portfolios) {
					for(Asset a : assets) {
						if(a.getAssetId().equals(rs.getInt("a.assetId")) && port.getPortfolioId().equals(rs.getInt("po.portfolioId"))) {
							port.addAsset(a);
						}
					}
				}
				
			}
		} catch (SQLException e) {
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
		return portfolios;
	}


}