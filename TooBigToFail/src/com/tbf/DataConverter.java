package com.tbf;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
	
	
	public static void main(String args[]) {

		List<Asset> port = parseDataFile();

		StringBuilder sb = new StringBuilder();
		
		sb.append(String.format("%-8s %-20s %-10s %-30s %9s %9s %9s\n", 
				"ID", "Name", "Type", "Title", "Gross", "Taxes", "Net"));

		//for each employee
//		for(Asset a : port) {
//			//format their information
//			sb.append(String.format("%-8s %-20s %-10s %-30s $%8.2f $%8.2f $%8.2f\n", 
//					e.getId(), e.getFirstName(), e.getType(), e.getTitle(), e.getGrossPay(), e.getTaxes(), e.getNetPay())); //TODO: replace these
//		}
		
		System.out.println(sb);
	}

}