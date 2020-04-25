/**
 * Author: Anthony luu, and Brett Berg
 * Date: 2020/2/28
 *
 * This is a subclass of class Person that holds appropriate methods and data that defines a Expert Broker.
 *
 */


package com.tbf;

import java.util.ArrayList;
import java.util.List;

public class ExpertBroker extends Broker{

	private static final double feeRate = 0.0375;

	public ExpertBroker(Integer personId, String personCode, String brokerData, String firstName, String lastName, Address address, List<String> email) {
		super(personId, personCode, brokerData, firstName, lastName, address, email);
	}
	

	public ExpertBroker(Integer personId, String personCode, String brokerData, String firstName, String lastName,
			Address address) {
		super(personId, personCode, brokerData, firstName, lastName, address, null);
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