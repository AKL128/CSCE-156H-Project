/**
 * Author: Anthony luu, and Brett Berg
 * Date: 2020/2/28
 *
 * This is a subclass of class Person that holds appropriate methods and data that defines a Junior Broker.
 * 
 */

package com.tbf;

import java.util.ArrayList;

public class JuniorBroker extends Broker{
	
	private static final double feeRate = 0.0125;

	public JuniorBroker(String personCode, String brokerData, String firstName, String lastName, Address address,
			ArrayList<String> email) {
		super(personCode, brokerData, firstName, lastName, address, email);
	}
	
	public double getFee() {
		return 75.0 * numberOfAsset;
	}
	
	public double getCommission() {
		return totalAnnualReturn * feeRate;
	}
	
	public void setNumberOfAsset(double assetNumber) {
		this.numberOfAsset = assetNumber;
	}

	public void setTotalAnnualReturn(double totalAnnualReturn) {
		this.totalAnnualReturn = totalAnnualReturn;
	}

}
