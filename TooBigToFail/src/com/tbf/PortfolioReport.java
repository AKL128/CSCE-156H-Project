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

public class PortfolioReport {

	//This takes the aggregate sum of risk
	public static double getAggregateRisk(ArrayList<Double> riskAtI, double totalValue) {
		double aggregateRisk = 0;
		for(double r : riskAtI) {
			aggregateRisk += r/totalValue;
		}

		return aggregateRisk;
	}

	public static void main(String args[]) {
		List<Portfolio> portList = DataConverter.parsePortfolioFile();
		List<Asset> aList = DataConverter.parseAssetFile();
		List<Person> pList = DataConverter.parsePersonFile();

		Collections.sort(portList);
		
		StringBuilder summaryReport = new StringBuilder();
		for(Portfolio port : portList) {
			port.getManager().setNumberOfAsset(port.getAssetList().size());
			port.getManager().setTotalAnnualReturn(port.getTotalAnnualReturn());
			summaryReport.append(String.format("%-8s %-20s %16s %16.2f %16.2f %16.5f %16.2f %16.2f\n"
					, port.getPortCode(), port.getOwner().getFullName(), port.getManager().getFullName()
					, port.getManager().getFee(), port.getManager().getCommission(), port.getAggregateRisk(), port.getTotalAnnualReturn()
					, port.getTotalValue()));
		}
		System.out.println(summaryReport);
		
	}
	
}
public static List<Porfolio> loadSummaryReport {

	List<Portfolio> portfolios = new ArrayList<>();

	String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
	try {
		Class.forName(DRIVER_CLASS).getDeclaredConstructor().newInstance();
	} catch(Exception e) {
		throw new RuntimeException(e);
	}

	Connection conn = null;
	String url = "jdbc:mysql://cse.unl.edu:3306/?user=bberg";
	String username = "bberg";
	String password = "Y3t:PU";

	try {
		conn = DriverManager.getConnection(url, username, password);
	} catch(SQLException e) {
		throw new RuntimeException(e);
	}

	String query = "select po.portCode, p.firstName, p.lastName, m.firstName, m.lastName"; // TODO: Prepare the proper query
	PreparedStatement ps = null;
  ResultSet rs = null;
	try {
		ps = conn.prepareStatement(query);
		rs = ps.executeQuery();
		while(rs.next()) {
			// Process the Person record
			int personId = rs.getInt("personId");
			String personCode = rs.getString("personCode");
			String brokerData = rs.getString("brokerData");
		}
	} catch(SQLException e) {
		throw new RuntimeException(e);
	}
	rs.close();
	ps.close();
	conn.close();
}
		
		
//		try {
//			List<Portfolio> portList = DataConverter.parsePortfolioFile();
//			List<Asset> aList = DataConverter.parseAssetFile();
//			List<Person> pList = DataConverter.parsePersonFile();
//
//			Collections.sort(portList);
//
//			File outputReport = new File("data/output.txt");
//			PrintWriter pwR = new PrintWriter(outputReport);
//
//			//This builds the print for summary reports
//			StringBuilder summaryReport = new StringBuilder();
//
//			summaryReport.append(String.format("Portfolio Summary Report\n"));
//			summaryReport.append(String.format("=================================================================================\n"));
//			summaryReport.append(String.format("%-8s %-20s %16s %16s %16s %16s %16s %16s\n", "Portfolio", "Owner", "Manager", "Fees", "Commissions", "Weighted Risk", "Return", "Total"));
//
//			for(Portfolio port : portList) {
//				double aggregateRisk = 0;
//				ArrayList<Double> riskAtI = new ArrayList<Double>();
//
//				double totalValue = 0;
//				double totalAnnualReturn = 0;
//
//				int numberOfAsset = port.getAssetList().size();
//				ArrayList<Asset> currentAssetList = port.getAssetList();
//
//				for(Asset a : currentAssetList) {
//					double value = a.getValue();
//					totalAnnualReturn += a.getAnnualReturn();
//					port.getManager().setTotalAnnualReturn(totalAnnualReturn);
//					totalValue += value;
//					riskAtI.add((a.getRisk() * a.getValue()));
//					aggregateRisk = getAggregateRisk(riskAtI, totalValue);
//				}
//
//					port.getManager().setNumberOfAsset(numberOfAsset);
//
//					summaryReport.append(String.format("%-8s %-20s %16s %16.2f %16.2f %16.5f %16.2f %16.2f\n"
//						, port.getPortCode(), port.getOwner().getFullName(), port.getManager().getFullName()
//						, port.getManager().getFee(), port.getManager().getCommission(), aggregateRisk, totalAnnualReturn
//						, totalValue));
//			}
//			System.out.println(summaryReport);
//			pwR.append(summaryReport);
//
//			//This builds the print for detailed report
//			for(Portfolio port : portList) {
//
//				String portCode = port.getPortCode();
//
//				StringBuilder detailedReport = new StringBuilder();
//
//				detailedReport.append(String.format("Portfolio %s\n", portCode));
//
//				String ownerString = "";
//				String managerString = "";
//				String beneficiaryString = "";
//
//				Person owner = port.getOwner();
//				Person manager = port.getManager();
//				Person beneficiary = port.getBeneficiary();
//
//				ownerString = port.getOwner().getPersonCode();
//				managerString = port.getManager().getPersonCode();
//				if(port.getBeneficiary() != null) {
//					beneficiaryString = port.getBeneficiary().getPersonCode();
//				}
//
//
//				for(Person p : pList) {
//					String personCode = p.getPersonCode();
//					if(personCode.equals(ownerString)) {
//						detailedReport.append(String.format("Owner:\n%s", p));
//					}
//				}
//				for(Person p : pList) {
//					String personCode = p.getPersonCode();
//					if(personCode.equals(managerString)) {
//						detailedReport.append(String.format("Manager:\n%s", p));
//					}
//				}
//				for(Person p : pList) {
//					String personCode = p.getPersonCode();
//					if(personCode.equals(beneficiaryString)) {
//						detailedReport.append(String.format("Beneficiary:\n%s", p));
//					}
//				}
//
//				ArrayList<Asset> assetData = port.getAssetList();
//
//				detailedReport.append(String.format("Assets\n"));
//				detailedReport.append(String.format("%-8s %-20s %16s %16s %16s %16s\n",
//						"Code", "Asset", "Return Rate", "Risk", "Annual Return", "Value"));
//
//				double aggregateRisk = 0;
//				double annualReturnTotal = 0;
//				ArrayList<Double> riskAtI = new ArrayList<Double>();
//				double totalValue = 0;
//
//				for(Asset a : assetData) {
//					double apy = 0.0;
//					double value = 0.0;
//					value = a.getValue();
//					apy = a.getAnnualReturn();
//
//					riskAtI.add((a.getRisk() * a.getValue()));
//					annualReturnTotal += a.getAnnualReturn();
//					totalValue += a.getValue();
//
//					detailedReport.append(String.format("%-8s %-20s %16.2f%% %16.2f %16.2f %16.2f\n",
//						a.getCode(), a.getLabel(), a.getReturnRate(), a.getRisk(), a.getAnnualReturn(), a.getValue()));
//
//					aggregateRisk = getAggregateRisk(riskAtI, totalValue);
//
//				}
//				detailedReport.append(String.format("-----------------------------------------------------------------------------------------------\n"));
//				detailedReport.append(String.format("%64.5f %16.2f % 16.2f\n", aggregateRisk, annualReturnTotal, totalValue));
//				//summaryReport.append(String.format("", ));
//				System.out.println(detailedReport);
//				pwR.append(detailedReport);
//			}
//		} catch(FileNotFoundException fnfe) {
//			throw new RuntimeException(fnfe);
//		}



