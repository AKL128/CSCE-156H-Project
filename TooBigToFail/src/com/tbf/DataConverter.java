/**
 * Author: Anthony luu, and Brett Berg
 * Date: 2020/2/28
 *
 * This class holds all methods that parses data files into usable Lists.
 * 
 */

package com.tbf;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DataConverter {

	//This parses the asset data into a usable List
	public static List<Asset> parseAssetFile(){
		List<Asset> result = new ArrayList<Asset>();
		File f = new File("data/Assets.dat");
		Scanner s;
		try {
			s = new Scanner(f);
		} catch(FileNotFoundException fnfe) {
			throw new RuntimeException(fnfe);
		}
		String firstLine = s.nextLine();
		double number = Double.parseDouble(firstLine);
		for(int i=0; i < number; i++) {
			String line = s.nextLine();
			if(!line.trim().isEmpty()) {
				Asset a = null;
				String tokens[] = line.split(";");
				String code = tokens[0];
				String id = tokens[1];
				String label = tokens[2];
				if(tokens[1].equals("D")) {
					double apr = Double.parseDouble(tokens[3]);
					a = new DepositAccount(code, id, label, apr);
				}
				if(tokens[1].equals("S")) {
					double quarterlyDividend = Double.parseDouble(tokens[3]);
					double baseRateOfReturn = Double.parseDouble(tokens[4]);
					double betaMeasure = Double.parseDouble(tokens[5]);
					String stockSymbol = tokens[6];
					double sharePrice = Double.parseDouble(tokens[7]);
					a = new Stock(code, id, label, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice);
				}
				if(tokens[1].equals("P")) {
					double quarterlyDividend = Double.parseDouble(tokens[3]);
					double baseRateOfReturn = Double.parseDouble(tokens[4]);
					double baseOmegaMeasure = Double.parseDouble(tokens[5]);
					double totalValue = Double.parseDouble(tokens[6]);
					a = new PrivateInvestment(code, id, label, quarterlyDividend, baseRateOfReturn, baseOmegaMeasure, totalValue);
				}
				result.add(a);
			}
		}
		return result;
    }

	//This parses the person data into a usable List
	public static List<Person> parsePersonFile() {
		List<Person> result = new ArrayList<Person>();
		File f = new File("data/Persons.dat");
		Scanner s;
		try {
			s = new Scanner(f);
		} catch(FileNotFoundException fnfe) {
			throw new RuntimeException(fnfe);
		}

		String firstLine = s.nextLine();
		Double number = Double.parseDouble(firstLine);

		for(int i = 0; i < number; i++) {
			String line = s.nextLine();
			if(!line.trim().isEmpty()) {
				Person b = null;
				String tokens[] = line.split(";");

				String personCode = tokens[0];
				String brokerData = tokens[1];
				String brokerToken[] = tokens[1].split(",");

				String personName[] = tokens[2].split(",");
				String lastName = personName[0];
				String firstName = personName[1];

				String addressData[] = tokens[3].split(",");
				String street = addressData[0];
				String city = addressData[1];
				String state = addressData[2];
				String zip = addressData[3];
				String country = addressData[4];

				ArrayList<String> email = null;
				if(tokens.length != 4) {
					String emailData[] = tokens[4].split(",");
					email = new ArrayList<String>(Arrays.asList(emailData));
				}

				Address address = new Address(street, city, state, zip, country);
				if(brokerToken[0].contains("E")) {
					b = new ExpertBroker(personCode, brokerData, firstName, lastName, address, email);
				} else if(brokerToken[0].contains("J")) {
					b = new JuniorBroker(personCode, brokerData, firstName, lastName, address, email);
				} else {
					b = new Person(personCode, brokerData, firstName, lastName, address, email);
				}
				
				result.add(b);
			}
		}
		return result;
	}
	
	//This parses the portfolio data into a usable List
	public static List<Portfolio> parsePortfolioFile() {
		
		List<Person> personList = new ArrayList<Person>();
		personList = parsePersonFile();
		
		List<Asset> assetList = new ArrayList<Asset>();
		assetList = parseAssetFile();
		
		List<Portfolio> result = new ArrayList<Portfolio>();
		File f = new File("data/Portfolios.dat");
		Scanner s;
		try {
			s = new Scanner(f);
		} catch(FileNotFoundException fnfe) {
			throw new RuntimeException(fnfe);
		}
		
		String firstLine = s.nextLine();
		Double number = Double.parseDouble(firstLine);

		for(int i = 0; i < number; i++) {
			String line = s.nextLine();
			if(!line.trim().isEmpty()) {
				Portfolio c = null;
				Person owner = null;
				Broker manager = null;
				Person beneficiary = null;
				
				String tokens[] = line.split(";", -1);
				String portCode = tokens[0];
				String ownerString = tokens[1];
				String managerString = tokens[2];
				String beneficiaryString = tokens[3];
				
				for(Person p : personList) {
					if(p.getPersonCode().equals(ownerString)) {
						owner = p;
					}
					if(p.getPersonCode().equals(managerString) && managerString != null) {
						manager = (Broker) p;
					}
					if(p.getPersonCode().equals(beneficiaryString)) {
						beneficiary = p;
					}
 				}
				
				
				ArrayList<Asset> assetDataList = new ArrayList<Asset>();
				String assetData[] = tokens[4].split(",");
				int counter = 0;
				for(String assetCodeNum : assetData) {
					if(assetCodeNum.isEmpty() == false && assetCodeNum != "") {
						String assetToken[] = assetCodeNum.split(":");
						String code = assetToken[0];
						double portValue = Double.parseDouble(assetToken[1]);
						
						for(Asset a : assetList) {
							String fromAssetCode = a.getCode();
							if(fromAssetCode.equals(code)) {
								Asset currentAsset = null;
								if(a.getId().equals("D")) {
									currentAsset = new DepositAccount((DepositAccount) a);
									currentAsset.setPortValue(portValue);
									assetDataList.add(currentAsset);	
								} else if(a.getId().equals("S")) {
									currentAsset = new Stock((Stock) a);
									currentAsset.setPortValue(portValue);
									assetDataList.add(currentAsset);	
								} else if(a.getId().equals("P")) {
									currentAsset = new PrivateInvestment((PrivateInvestment) a);
									currentAsset.setPortValue(portValue);
									assetDataList.add(currentAsset);	
								}
							}
						}
					}
				}
				c = new Portfolio(portCode, owner, manager, beneficiary, assetDataList);
				result.add(c);
		
			}
		}
		return result;
	}
	
	
}
