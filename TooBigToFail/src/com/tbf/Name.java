package com.tbf;

public class Name {
	private String lastName;
	private String firstName;

	public Name(String lastName, String firstName) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String newLastName) {
		this.lastName = newLastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String newFirstName) {
		this.firstName = newFirstName;
	}
}
