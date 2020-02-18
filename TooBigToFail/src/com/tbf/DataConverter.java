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
<<<<<<< HEAD
  }
	public static List<Person> parseDataFile2() {
		List<Person> result2 = new ArrayList<Person>();
		File p = new File("data/Persons.dat");
		Scanner p;
		try {
			s = new Scanner(p);
		} catch(FileNotFoundException fnfe) {
			throw new RuntimeException(fnfe);
		}
		String firstLine = p.nextLine();
		Double number = Double.parseDouble(firstLine);
		for(int j; j < number; j++) {
			String line = p.nextLine();
			if(!line.trim().isEmpty()) {
				Person b = null;
				String tokens[] = line.split(";");
				String personCode = tokens[0];
				String brokerData = tokens[1];
				String PersonName[] = line.split(",");
				String lastName = b.getLastName().setLastName(personName[0]);
				String firstName = b.getFirstName().setFirstName(personName[1]);
				Name name = new Name(lastName, firstName);
				String addressData[] = line.split(",");
				String street = b.getStreet().setStreet(addressData[0]);
				String city = b.getCity.setCity(addressData[1]);
				String state = b.getState().setState(addressData[2]);
				int zip = b.getZip().setZip(addressData[3]);
				String country = b.getCountry().setCountry(addressData[4]);
				Address address = new Address(street, city, state, zip, country);
				String email = tokens[4];
				b = new Person(personCode, brokerData, name, address, email)
				result2.add(b);
			}
		}
		return result2;
	}

	public static void main(String args[]) {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		List<Asset> port = parseDataFile();

		for(Asset a : port) {
			String json = gson.toJson(a);
			System.out.printf("%s\n", json);
		}

		try {
			File output = new File("data/Assets.json");
			PrintWriter pw = new PrintWriter(output);

			for(Asset a : port) {
				String json = gson.toJson(a);
				pw.printf("%s\n", json);
			}

			pw.close();
		} catch(FileNotFoundException fnfe) {
			throw new RuntimeException(fnfe);
		}

		List<Person> port2 = parseDataFile();

		for(Person b: port2) {
			String json2 = gson.toJson(b);
			System.out.printf("%s\n". json);
		}

		try {
			File output2 = new File("data/Persons.json");
			Printwriter pw2 = new PrintWriter(output2);

			for(Person b: port2) {
				String json2 = gson.toJson(b);
				pw.printf("%s\n", json2);
			}

			pw.close();
		} catch(FileNotFoundException fnfe) {
			throw new RuntimeException(fnfe);
		}
=======
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

				String email = "";
				if(tokens.length != 4) {
					email = tokens[4];
				}

				Name name = new Name(lastName, firstName);
				Address address = new Address(street, city, state, zip, country);
				b = new Person(personCode, brokerData, name, address, email);
				result.add(b);
			}
		}
		return result;
	}



	public static void main(String args[]) {

		Gson gsonA = new GsonBuilder().setPrettyPrinting().create();

		List<Asset> aList = parseAssetFile();

		//This writes the data into json format for Assets.json
		try {
			File outputAsset = new File("data/Assets.json");

			PrintWriter pwA = new PrintWriter(outputAsset);
			int size = aList.size();
			int counter = 0;
			for(Asset a : aList) {
				if(counter == 0) {
					pwA.printf("{\n\"assets\": [");
				}
				String jsonA = gsonA.toJson(a);
				pwA.printf("%s", jsonA);
				counter++;
				if(counter != size) {
					pwA.printf(",\n");
				} else {
					pwA.printf("\n]}");
				}
			}

			pwA.close();
		} catch(FileNotFoundException fnfe) {
			throw new RuntimeException(fnfe);
		}

		Gson gsonP = new GsonBuilder().setPrettyPrinting().create();

		List<Person> pList = parsePersonFile();

		//This writes the data into json format for Persons.json
		try {

			File outputPerson = new File("data/Persons.json");
			PrintWriter pwP = new PrintWriter(outputPerson);

			int size = pList.size();
			int counter = 0;
			for(Person p : pList) {
				if(counter == 0) {
					pwP.printf("{\n\"persons\": [");
				}
				String json2 = gsonP.toJson(p);
				pwP.printf("%s", json2);
				counter++;
				if(counter != size) {
					pwP.printf(",\n");
				} else {
					pwP.printf("\n]}");
				}
			}

			pwP.close();
		} catch(FileNotFoundException fnfe) {
			throw new RuntimeException(fnfe);
		} catch(NumberFormatException ex) {
			System.out.println("not a number");
		}

	}

}
