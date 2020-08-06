package com.techelevator.camper.model;

public class FamilyContact {

	private int familyContactID;
	private String familyContactName;
	private String emailAddress;
	private String phoneNumber;
	
	public FamilyContact() {
		
	}

	public int getFamilyContactID() {
		return familyContactID;
	}
	
	public void setFamilyContactID(int familyContactID) {
		this.familyContactID = familyContactID;
	}
	
	public String getFamilyContactName() {
		return familyContactName;
	}
	
	public void setFamilyContactName(String familyContactName) {
		this.familyContactName = familyContactName;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	
}
