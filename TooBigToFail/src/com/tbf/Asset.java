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
		this.assetId = a.assetId;
		this.code = a.code;
		this.id = a.id;
		this.label = a.label;
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
	
	



}