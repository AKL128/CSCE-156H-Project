package com.tbf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DataConverter {
	public static List<Asset> parseDataFile(){
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
	public static List<Person> parseDataFile() {
		List<Person> result2 = new ArrayList<Person>();
		File p = new File("data/Persons.dat");
		Scanner s;
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
				Name name = tokens[2];
				Address address = tokens[3];
				String email = tokens[4];
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

	}

}
