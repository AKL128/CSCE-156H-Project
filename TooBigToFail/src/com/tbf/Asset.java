/**
 * Author: Anthony luu, and Brett Berg
 * Date: 2020/2/28
 *
 * This is a abstract super class for Asset that is a parent class that holds all common data between subclasses of Asset.
 *
 */

package com.tbf;

public abstract class Asset{
	protected int assetId;
	protected String code;
	protected String id;
	protected String label;

	public Asset(int assetId, String code, String id, String label) {
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

	public int getAssetId() {
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



}
