/**
 * Author: Anthony luu, and Brett Berg
 * Date: 2020/2/28
 *
 * This is a class for Portfolio that holds data of a client and their assets.
 *
 */

package com.tbf;

import java.util.ArrayList;

public class Portfolio implements Comparable<Portfolio>{
	private int portfolioId;
	private String portCode;
	private Person owner;
	private Broker manager;
	private Person beneficiary;
	private ArrayList<Asset> assetList;

	public int compareTo(Portfolio that) {
		return this.getOwner().getLastName().compareTo(that.getOwner().getLastName());
	}

	public Portfolio(int portfolioId, String portCode, Person owner, Broker manager,
	 Person beneficiary, ArrayList<Asset> assetList) {
		 this.portfolioId = portfolioId;
		 this.portCode = portCode;
		 this.owner = owner;
		 this.manager = manager;
		 this.beneficiary = beneficiary;
		 this.assetList = assetList;
	}

	public int getPortfolioId() {
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

	public ArrayList<Asset> getAssetList() {
		return assetList;
	}
}
