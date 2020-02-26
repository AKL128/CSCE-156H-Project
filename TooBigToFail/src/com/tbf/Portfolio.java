package com.tbf;

import java.util.ArrayList;

public class Portfolio {
	private String portCode;
	private Person owner;
	private Person manager;
	private Person beneficiary;
	private ArrayList<Asset> assetList;

	public Portfolio(String portCode, Person owner, Person manager,
	 Person beneficiary, ArrayList<Asset> assetList) {
		 this.portCode = portCode;
		 this.owner = owner;
		 this.manager = manager;
		 this.beneficiary = beneficiary;
		 this.assetList = assetList;
	}

	public String getPortCode() {
		return portCode;
	}

	public Person getOwner() {
		return owner;
	}

	public Person getManager() {
		return manager;
	}

	public Person getBeneficiary() {
		return beneficiary;
	}

	public ArrayList<Asset> getAssetList() {
		return assetList;
	}
}
