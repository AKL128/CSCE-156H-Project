/**
 * Author: Anthony luu, and Brett Berg
 * Date: 2020/2/28
 *
 * This is a subclass of class Person that holds appropriate methods and data that defines a Expert Broker.
 * 
 */

package com.tbf;

import java.util.ArrayList;

public class ExpertBroker extends Person{

	public ExpertBroker(String personCode, String brokerData, String firstName, String lastName, Address address,
			ArrayList<String> email) {
		super(personCode, brokerData, firstName, lastName, address, email);
	}

	public double getFee() {
		return 0.0;
	}
	
	public double getCommission() {
		return totalAnnualReturn * 0.0375;
	}
	
	public void setNumberOfAsset(double assetNumber) {
		this.numberOfAsset = assetNumber;
	}

	public void setTotalAnnualReturn(double totalAnnualReturn) {
		this.totalAnnualReturn = totalAnnualReturn;
		
	}
}