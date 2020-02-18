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
		super();
		this.portCode = portCode;
		this.owner = owner;
		this.manager = manager;
		this.beneficiary = beneficiary;
		this.assetList = assetList;
	}

	public String getPortCode() {
		return portCode;
	}

	public void setPortCode(String newPortCode) {
		this.portCode = newPortCode;
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner (Person newOwner) {
		this.owner = newOwner;
	}

	public Person getManager() {
		return manager;
	}

	public void setManager(Person newManager) {
		this.manager = newManager;
	}

	public Person getBeneficiary() {
		return beneficiary;
	}

	public void setBeneficiary (Person newBeneficiary) {
		this.beneficiary = newBeneficiary;
	}

	public ArrayList<Asset> getAssetList() {
		return assetList;
	}

	public void setAssetList(ArrayList<Asset> newAssetList) {
		this.assetList = newAssetList;
	}
}
