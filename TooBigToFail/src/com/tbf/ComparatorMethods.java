package com.tbf;
/**
 * Author: Anthony luu, and Brett Berg
 * Date: 2020/4/23
 *
 * This is a class that holds all of the comparators for our ADT Linked List
 *
 */

import java.util.Comparator;

public class ComparatorMethods {
	
	public static final Comparator<Portfolio> ownerComparator = new Comparator<Portfolio>() {
		public int compare(Portfolio p1, Portfolio p2) {
			int lastNameCompare = p1.getOwner().getLastName().compareTo(p2.getOwner().getLastName());
			int firstNameCompare = p1.getOwner().getFirstName().compareTo(p2.getOwner().getFirstName());
			int portCompare = p1.getPortCode().compareTo(p2.getPortCode());
			
			if(lastNameCompare == 0) {
				return ((firstNameCompare == 0) ? portCompare : firstNameCompare);
			} else {
				return lastNameCompare;
			}
		}
	};
	
	public static final Comparator<Portfolio> valueComparator = new Comparator<Portfolio>() {

		public int compare(Portfolio p1, Portfolio p2) {
			int valueCompare;
			if(p1.getTotalValue() < p2.getTotalValue()) {
				valueCompare = 1;
			} else if(p1.getTotalValue() > p2.getTotalValue()) {
				valueCompare = -1;
			} else {
				valueCompare = 0;
			}
			
			int portCompare = p1.getPortCode().compareTo(p2.getPortCode());
			
			if(valueCompare == 0) {
				return portCompare;
			} else {
				return valueCompare;
			}
		}
		
	};
	
	public static final Comparator<Portfolio> managerComparator = new Comparator<Portfolio>() {

		public int compare(Portfolio p1, Portfolio p2) {
			int typeCompare = p1.getManager().getBrokerData().compareTo(p2.getManager().getBrokerData());
			int lastNameCompare = p1.getManager().getLastName().compareTo(p2.getManager().getLastName());
			int firstNameCompare = p1.getManager().getFirstName().compareTo(p2.getManager().getFirstName());
			int portCompare = p1.getPortCode().compareTo(p2.getPortCode());
			
			if(typeCompare == 0) {
				return  ((lastNameCompare == 0) ? portCompare : firstNameCompare);
			} else {
				return typeCompare;
			}
		}
		
	};


}
