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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;

public class PortfolioReport {
	
	public static StringBuilder portfolioSummaryReport(List<Portfolio> portfolioList, Comparator<Portfolio> comparator) {
		try {
		
		LinkedList<Portfolio> linkedPort = new LinkedList<Portfolio>(comparator);
		for(Portfolio p : portfolioList) {
			linkedPort.addElementBySort(p);
		}
		
		StringBuilder summaryReport = new StringBuilder();
		summaryReport.append(String.format("Portfolio Summary Report\n"));
		summaryReport.append(String.format("========================================================================================================================================\n"));
		summaryReport.append(String.format("%-8s %-20s %16s %16s %16s %16s %16s %16s\n", "Portfolio", "Owner", "Manager", "Fees", "Commissions", "Weighted Risk", "Return", "Total"));
		
		double summaryTotal = 0;
		double summaryFee = 0;
		double summaryCommission = 0;
		double summaryReturn = 0;

		for(Portfolio port : linkedPort) {
			
			port.getManager().setNumberOfAsset(port.getAssetList().size());
			
			port.getManager().setTotalAnnualReturn(port.getTotalAnnualReturn());
			summaryReport.append(String.format("%-8s %-20s %16s %16.2f %16.2f %16.5f %16.2f %16.2f\n"
				, port.getPortCode(), port.getOwner().getFullName(), port.getManager().getFullName()
				, port.getManager().getFee(), port.getManager().getCommission(), port.getAggregateRisk(), port.getTotalAnnualReturn()
				, port.getTotalValue()));
			summaryTotal += port.getTotalValue();
			summaryFee += port.getManager().getFee();
			summaryCommission += port.getManager().getCommission();
			summaryReturn += port.getTotalAnnualReturn();
		}
		summaryReport.append(String.format("========================================================================================================================================\n"));
		summaryReport.append(String.format("Totals %56.2f %16.2f %33.2f %16.2f", summaryFee, summaryCommission, summaryReturn, summaryTotal));
		System.out.println(summaryReport);
		return summaryReport;
		} catch (NullPointerException e) {
			System.out.println("Bad input portfolio: Possibly no manager input");
			throw new RuntimeException(e);
		}
	}
	
	public static StringBuilder portfolioDetailedReport(Portfolio portfolio) {
		try {
		StringBuilder detailedReport = new StringBuilder();
		detailedReport.append(String.format("Portfolio %s\n", portfolio.getPortCode()));
		detailedReport.append(String.format("Owner: %s\n", portfolio.getOwner().getFullName()));
		detailedReport.append(String.format("Owner Email: %s\n", portfolio.getOwner().getEmail()));
		detailedReport.append(String.format("Manager: %s\n", portfolio.getManager().getFullName()));
		if(portfolio.getBeneficiary() != null) {
			detailedReport.append(String.format("Beneficiary: %s\n", portfolio.getBeneficiary().getFullName()));
		} else {
			detailedReport.append(String.format("Beneficiary: none\n"));
		}
		
		detailedReport.append(String.format("Assets\n"));
		detailedReport.append(String.format("%-8s %-20s %16s %16s %16s %16s\n",
				"Code", "Asset", "Return Rate", "Risk", "Annual Return", "Value"));
		
		List<Asset> assetList = portfolio.getAssetList();
		for(Asset a : assetList) {
			detailedReport.append(String.format("%-8s %-20s %16.2f%% %16.2f %16.2f %16.2f\n",
					a.getCode(), a.getLabel(), a.getReturnRate(), a.getRisk(), a.getAnnualReturn(), a.getValue()));
		}
		detailedReport.append(String.format("-----------------------------------------------------------------------------------------------\n"));
		detailedReport.append(String.format("%64.5f %16.2f % 16.2f\n", portfolio.getAggregateRisk(), portfolio.getTotalAnnualReturn(), portfolio.getTotalValue()));
		return detailedReport;
	} catch (NullPointerException e) {
		System.out.println("Bad input portfolio: Possibly no manager input DETAILED");
		throw new RuntimeException(e);
	}
	}

	public static void main(String args[]) {
	
	try {
		
		Configurator.initialize(new DefaultConfiguration());
		Configurator.setRootLevel(Level.INFO);
		
		File outputReport = new File("data/output.txt");
		PrintWriter pwR = new PrintWriter(outputReport);
		
		List<Portfolio> portListFlat = DataConverter.parsePortfolioFile();
		List<Asset> aListFlat = DataConverter.parseAssetFile();
		List<Person> pListFlat = DataConverter.parsePersonFile();
		List<Person> pList = DatabaseInfo.loadAllPersons();

		List<Portfolio> portList = DatabaseInfo.loadAllPortfolios();
		List<Asset> aList = DatabaseInfo.loadAllAssets();

		portfolioSummaryReport(portList, ComparatorMethods.ownerComparator);
		portfolioSummaryReport(portList, ComparatorMethods.valueComparator);
		portfolioSummaryReport(portList, ComparatorMethods.managerComparator);

		
		for(Portfolio port : portList) {
			portfolioDetailedReport(port);
		}
		
		for(Portfolio port : portListFlat) {
			portfolioDetailedReport(port);
		}
	
		for(Portfolio port : portList) {
			pwR.println(portfolioDetailedReport(port));
			System.out.println(portfolioDetailedReport(port));
		}
		
		pwR.close();
		

		
	} catch(FileNotFoundException fnfe) {
		throw new RuntimeException(fnfe);
	}
	}
}







