package com.tbf;

import java.util.Comparator;

public class ComparatorMethods {
	
	public class ownerComparator implements Comparator<Portfolio> {

		public int compare(Portfolio p1, Portfolio p2) {
			int lastNameCompare = p1.getOwner().getLastName().compareTo(p2.getOwner().getLastName());
			int firstNameCompare = p1.getOwner().getFirstName().compareTo(p2.getOwner().getFirstName());
			
			if(lastNameCompare == 0) {
				return ((firstNameCompare == 0) ? lastNameCompare : firstNameCompare);
			} else {
				return lastNameCompare;
			}
		}
		
	}
	
	public class valueComparator implements Comparator<Portfolio> {

		public int compare(Portfolio p1, Portfolio p2) {
			if(p1.getTotalValue() < p2.getTotalValue()) {
				return -1;
			} else if(p1.getTotalValue() > p2.getTotalValue()) {
				return 1;
			} else {
				return 0;
			}
		}
		
	}
	
	public class managerComparator implements Comparator<Portfolio> {

		public int compare(Portfolio p1, Portfolio p2) {
			int typeCompare = p1.getManager().getBrokerData().compareTo(p2.getManager().getBrokerData());
			int lastNameCompare = p1.getManager().getLastName().compareTo(p2.getManager().getLastName());
			int firstNameCompare = p1.getManager().getFirstName().compareTo(p2.getManager().getFirstName());
			
			if(typeCompare == 0) {
				return  ((lastNameCompare == 0) ? firstNameCompare : lastNameCompare);
			} else {
				return typeCompare;
			}
		}
		
	}
	
	public static Comparator<Portfolio> ownerComparator;
	public static Comparator<Portfolio> valueComparator;
	public static Comparator<Portfolio> managerComparator;

}
