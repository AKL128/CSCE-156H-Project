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
import java.util.Comparator;
import java.util.List;

public class Portfolio{
	private Integer portfolioId;
	private String portCode;
	private Person owner;
	private Broker manager;
	private Person beneficiary;
	private List<Asset> assetList = new ArrayList<Asset>();

	

	public Portfolio(Integer portfolioId, String portCode, Person owner, Broker manager,
	 Person beneficiary) {
		 this.portfolioId = portfolioId;
		 this.portCode = portCode;
		 this.owner = owner;
		 this.manager = manager;
		 this.beneficiary = beneficiary;
	}
	
	public Portfolio(Integer portfolioId, String portCode, Person owner, Broker manager,
			 Person beneficiary, List<Asset> assetList) {
				 this.portfolioId = portfolioId;
				 this.portCode = portCode;
				 this.owner = owner;
				 this.manager = manager;
				 this.beneficiary = beneficiary;
				 this.assetList = assetList;
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
	
	public void setAssetList(List<Asset> assetList) {
		this.assetList = assetList;
	}
	
	
	
	public class ownerComparator implements Comparator<Portfolio> {

		public int compare(Portfolio p1, Portfolio p2) {
			int lastNameCompare = p1.getOwner().getLastName().compareTo(p2.getOwner().getLastName());
			int firstNameCompare = p1.getOwner().getFirstName().compareTo(p2.getOwner().getFirstName());
			
			if(lastNameCompare == 0) {
				return ((firstNameCompare == 0) ? lastNameCompare : firstNameCompare);
			} else {
				return lastNameCompare;
			}
		}
		
	}
	
	public class valueComparator implements Comparator<Portfolio> {

		public int compare(Portfolio p1, Portfolio p2) {
			if(p1.getTotalValue() < p2.getTotalValue()) {
				return -1;
			} else if(p1.getTotalValue() > p2.getTotalValue()) {
				return 1;
			} else {
				return 0;
			}
		}
		
	}
	
	public class managerComparator implements Comparator<Portfolio> {

		public int compare(Portfolio p1, Portfolio p2) {
			int typeCompare = p1.getManager().getBrokerData().compareTo(p2.getManager().getBrokerData());
			int lastNameCompare = p1.getManager().getLastName().compareTo(p2.getManager().getLastName());
			int firstNameCompare = p1.getManager().getFirstName().compareTo(p2.getManager().getFirstName());
			
			if(typeCompare == 0) {
				return  ((lastNameCompare == 0) ? firstNameCompare : lastNameCompare);
			} else {
				return typeCompare;
			}
		}
		
	}


	


}