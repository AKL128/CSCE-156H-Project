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
	
	public static void portfolioSummaryReport(List<Portfolio> portfolioList) {
		Collections.sort(portfolioList);
		
		StringBuilder summaryReport = new StringBuilder();
		summaryReport.append(String.format("Portfolio Summary Report\n"));
		summaryReport.append(String.format("========================================================================================================================================\n"));
		summaryReport.append(String.format("%-8s %-20s %16s %16s %16s %16s %16s %16s\n", "Portfolio", "Owner", "Manager", "Fees", "Commissions", "Weighted Risk", "Return", "Total"));
		
		double summaryTotal = 0;
		for(Portfolio port : portfolioList) {
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
		return;
	}
	
	public static void portfolioDetailedReport(Portfolio portfolio) {
		StringBuilder detailedReport = new StringBuilder();
		detailedReport.append(String.format("Portfolio %s\n", portfolio.getPortCode()));
		detailedReport.append(String.format("Owner: %s\n", portfolio.getOwner().getFullName()));
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
		System.out.println(detailedReport);
		return;
	}

	public static void main(String args[]) {
		
		Configurator.initialize(new DefaultConfiguration());
		Configurator.setRootLevel(Level.INFO);
		List<Portfolio> portListFlat = DataConverter.parsePortfolioFile();
		List<Asset> aListFlat = DataConverter.parseAssetFile();
		List<Person> pListFlat = DataConverter.parsePersonFile();

		List<Portfolio> portList = DatabaseInfo.loadAllPortfolios();
		List<Asset> aList = DatabaseInfo.loadAllAssets();
		List<Person> pList = DatabaseInfo.loadAllPersons();

		portfolioSummaryReport(portList);

		//portfolioSummaryReport(portListFlat);

		Collections.sort(portList);
		
		for(Portfolio port : portList) {
			portfolioDetailedReport(port);
		}
	}
}



