package com.tbf;

public abstract class Asset implements AnnualReturn{
	protected String code;
	protected String id;
	protected String label;

	public Asset(String code, String id, String label) {
		super();
		this.code = code;
		this.id = id;
		this.label = label;
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
	
	public double getAnnualReturn() {
		return 0;
	}
	
	public double getTotalBalance() {
		return 0;
	}
	public double getShares() {
		return 0;
	}
	public double getStake() {
		return 0;
	}
}