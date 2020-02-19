package com.tbf;
import java.io.File;
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
		int number = s.hasNext();
		for(int i=0; i < number; i++) {
			String line = s.nextLine();
			if(!line.trim().isEmpty()) {
				Asset a = null;
				String tokens[] = line.split(";");
				String code = tokens[0];
				String id = tokens[1];
				String label = tokens[2];
				if(tokens[1].equals("D")) {
					Double apr = tokens[3];
					a = new DepositAccount(code, id, label, apr);
				}
				if(tokens[1].equals("S")) {
					Double quarterlyDividend = tokens[3];
					Double baseRateOfReturn = tokens[4];
					Double betaMeasure = tokens[5];
					String stockSymbol = tokens[6];
					Double sharePrice = tokens[7];
					a = new Stock(code, id, label, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice);
				}
				if(tokens[1].equals("P")) {
					Double quarterlyDividend = tokens[3];
					Double baseRateOfReturn = tokens[4];
					Double baseOmegaMeasure = tokens[5];
					Double totalValue = tokens[6];
					a = new PrivateInvestment(code, id, label, quarterlyDividend, baseRateOfReturn, baseOmegaMeasure, totalValue);
				}
				result.add(a);
			}
		}

		return result;
  }


}
