package com.tbf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class DataConverter {

	//This parses the asset data into a readable array
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

	//This parses the person data into a readable array
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
				b = new Person(personCode, brokerData, firstName, lastName, address, email);
				result.add(b);
			}
		}
		return result;
	}



	public static void main(String args[]) {

		toJson.personJson();
			
		toXML.personXML();
		
		toXML.assetXML();


	}

}
