/**
 * Author: Anthony luu, and Brett Berg
 * Date: 2020/2/28
 *
 * This is a subclass of class Person that holds appropriate methods and data that defines a Expert Broker.
 *
 */

package com.tbf;

import java.util.ArrayList;

public class ExpertBroker extends Broker{

	private static final double feeRate = 0.0375;

	public ExpertBroker(Integer brokerId, String personCode, String brokerData, String firstName, String lastName, Address address) {
		super(brokerId, personCode, brokerData, firstName, lastName, address);
	}
	
	public ExpertBroker(String firstName, String lastName) {
		this(null, null, null, firstName, lastName, null);
	}

	public double getFee() {
		return 0.0;
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