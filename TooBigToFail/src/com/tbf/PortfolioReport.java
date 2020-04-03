/**
 * Author: Anthony luu, and Brett Berg
 * Date: 2020/2/28
 *
 * This is a program that reports a summary report and a detailed report of Portfolios.
 *
 */

package com.tbf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;

public class PortfolioReport {

	public static void main(String args[]) {
		
		Configurator.initialize(new DefaultConfiguration());
		Configurator.setRootLevel(Level.INFO);
//		try {
			
			List<Portfolio> portListFlat = DataConverter.parsePortfolioFile();
			List<Asset> aListFlat = DataConverter.parseAssetFile();
			List<Person> pListFlat = DataConverter.parsePersonFile();
		
			List<Portfolio> portList = Portfolio.loadAllPortfolios();
			List<Asset> aList = Asset.loadAllAssets();
			List<Person> pList = Person.loadAllPersons();
		
		
			Collections.sort(portList);

			StringBuilder summaryReport = new StringBuilder();
			summaryReport.append(String.format("Portfolio Summary Report\n"));
			summaryReport.append(String.format("========================================================================================================================================\n"));
			summaryReport.append(String.format("%-8s %-20s %16s %16s %16s %16s %16s %16s\n", "Portfolio", "Owner", "Manager", "Fees", "Commissions", "Weighted Risk", "Return", "Total"));
			
			double summaryTotal = 0;
			for(Portfolio port : portList) {
				port.getManager().setNumberOfAsset(port.getAssetList().size());
				port.getManager().setTotalAnnualReturn(port.getTotalAnnualReturn());
				summaryReport.append(String.format("%-8s %-20s %16s %16.2f %16.2f %16.5f %16.2f %16.2f\n"
					, port.getPortCode(), port.getOwner().getFullName(), port.getManager().getFullName()
					, port.getManager().getFee(), port.getManager().getCommission(), port.getAggregateRisk(), port.getTotalAnnualReturn()
					, port.getTotalValue()));
				summaryTotal += port.getTotalValue();
			}
			summaryReport.append(String.format("========================================================================================================================================\n"));
			summaryReport.append(String.format("%131.2f", summaryTotal));
			System.out.println(summaryReport);
		
			Collections.sort(portListFlat);

//		StringBuilder summaryReport2 = new StringBuilder();
//		summaryReport2.append(String.format("Portfolio Summary Report\n"));
//		summaryReport2.append(String.format("=================================================================================\n"));
//		summaryReport2.append(String.format("%-8s %-20s %16s %16s %16s %16s %16s %16s\n", "Portfolio", "Owner", "Manager", "Fees", "Commissions", "Weighted Risk", "Return", "Total"));
//		
//		for(Portfolio port : portListFlat) {
//			port.getManager().setNumberOfAsset(port.getAssetList().size());
//			port.getManager().setTotalAnnualReturn(port.getTotalAnnualReturn());
//			summaryReport2.append(String.format("%-8s %-20s %16s %16.2f %16.2f %16.5f %16.2f %16.2f\n"
//					, port.getPortCode(), port.getOwner().getFullName(), port.getManager().getFullName()
//					, port.getManager().getFee(), port.getManager().getCommission(), port.getAggregateRisk(), port.getTotalAnnualReturn()
//					, port.getTotalValue()));
//		}
//		
//		System.out.println(summaryReport2);
		
		
//		for(Portfolio port : portList) {
//		
//			String portCode = port.getPortCode();
//		
//			StringBuilder detailedReport = new StringBuilder();
//		
//			detailedReport.append(String.format("Portfolio %s\n", portCode));
//		
//			String ownerString = "";
//			String managerString = "";
//			String beneficiaryString = "";
//		
//			Person owner = port.getOwner();
//			Person manager = port.getManager();
//			Person beneficiary = port.getBeneficiary();
//		
//			ownerString = port.getOwner().getPersonCode();
//			managerString = port.getManager().getPersonCode();
//			if(port.getBeneficiary() != null) {
//				beneficiaryString = port.getBeneficiary().getPersonCode();
//			}
//		
//		
//			for(Person p : pList) {
//				String personCode = p.getPersonCode();
//				if(personCode.equals(ownerString)) {
//					detailedReport.append(String.format("Owner:\n%s", p));
//				}
//			}
//			for(Person p : pList) {
//				String personCode = p.getPersonCode();
//				if(personCode.equals(managerString)) {
//					detailedReport.append(String.format("Manager:\n%s", p));
//				}
//			}
//			for(Person p : pList) {
//				String personCode = p.getPersonCode();
//				if(personCode.equals(beneficiaryString)) {
//					detailedReport.append(String.format("Beneficiary:\n%s", p));
//				}
//			}
//		
//			List<Asset> assetData = port.getAssetList();
//		
//			detailedReport.append(String.format("Assets\n"));
//			detailedReport.append(String.format("%-8s %-20s %16s %16s %16s %16s\n",
//					"Code", "Asset", "Return Rate", "Risk", "Annual Return", "Value"));
//		
//			double aggregateRisk = 0;
//			double annualReturnTotal = 0;
//			ArrayList<Double> riskAtI = new ArrayList<Double>();
//			double totalValue = 0;
//		
//			for(Asset a : assetData) {
//				double apy = 0.0;
//				double value = 0.0;
//				value = a.getValue();
//				apy = a.getAnnualReturn();
//		
//				riskAtI.add((a.getRisk() * a.getValue()));
//				annualReturnTotal += a.getAnnualReturn();
//				totalValue += a.getValue();
//		
//				detailedReport.append(String.format("%-8s %-20s %16.2f%% %16.2f %16.2f %16.2f\n",
//						a.getCode(), a.getLabel(), a.getReturnRate(), a.getRisk(), a.getAnnualReturn(), a.getValue()));
//		
//				aggregateRisk = getAggregateRisk(riskAtI, totalValue);
//		
//			}
//			detailedReport.append(String.format("-----------------------------------------------------------------------------------------------\n"));
//			detailedReport.append(String.format("%64.5f %16.2f % 16.2f\n", aggregateRisk, annualReturnTotal, totalValue));
//			System.out.println(detailedReport);
//			pwR.append(detailedReport);
//			}
//			} catch(FileNotFoundException fnfe) {
//				throw new RuntimeException(fnfe);
//		}
	}
}



