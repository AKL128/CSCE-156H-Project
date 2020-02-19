package com.tbf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PortfolioReport {

  public static List<Portfolio> parsePortfolioFile(){
    List<Portfolio> result = new ArrayList<Portfolio>();
    File f = new File("data/Portfolios.dat");
    Scanner s;
    try {
      s = new Scanner(f);
    } catch(FileNotFoundException fnfe) {
			throw new RuntimeException(fnfe);
		}
    String firstLine = s.nextLine();
    double number = Double.parseDouble(firstLine);
    for(int i = 0; i < number; i++) {
      String line = s.nextLine();
      if(!line.trim().isEmpty()) {
        Portfolio p = null;
        String tokens[] = line.split(";");
        String portCode = tokens[0];
        Person owner = tokens[1];
        Person manager = tokens[2];
        Person beneficiary = tokens[3];

        String assetData[] = tokens[4].split(",");
        ArrayList assetList;
        for(int j = 0; j < assetData.length; j++) {
          assetList.add(j, assetData[j]);
        }

        
      }
    }

  }

  public static void main(String args[]) {



  }


}
