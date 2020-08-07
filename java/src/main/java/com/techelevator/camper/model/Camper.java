package com.techelevator.camper.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Camper {

	private int camperID;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String streetAddress;
	private String streetAddressLineTwo;
	private String city;
	private String stateCode;
	private String zipCode;
	private boolean paymentStatus;
	private int familyContact;
	private String additionalNotes;
	private int familyContactId;
	private String familyContactName;
	private String email;
	private String phoneNumber;
	
	
	public Camper() {
		
	}
	
	public int getCamperID() {
		return camperID;
	}
	
	public void setCamperID(int camperID) {
		this.camperID = camperID;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getStreetAddress() {
		return streetAddress;
	}
	
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getStreetAddressLineTwo() {
		return streetAddressLineTwo;
	}
	
	public void setStreetAddressLineTwo(String streetAddressLineTwo) {
		this.streetAddressLineTwo = streetAddressLineTwo;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getStateCode() {
		return stateCode;
	}
	
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	
	public String getZipCode() {
		return zipCode;
	}
	
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	public boolean isPaymentStatus() {
		return paymentStatus;
	}
	
	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	public String getAdditionalNotes() {
		return additionalNotes;
	}
	
	public int getFamilyContact() {
		return familyContact;
	}

	public void setFamilyContact(int familyContact) {
		this.familyContact = familyContact;
	}

	public int getFamilyContactId() {
		return familyContactId;
	}

	public void setFamilyContactId(int familyContactId) {
		this.familyContactId = familyContactId;
	}

	public String getFamilyContactName() {
		return familyContactName;
	}

	public void setFamilyContactName(String familyContactName) {
		this.familyContactName = familyContactName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setAdditionalNotes(String additionalNotes) {
		this.additionalNotes = additionalNotes;
	}
	
	public Camper mapper(String row) {
		Camper camper = new Camper();
		String[] rows = row.split("\n");
		ArrayList<String> attributes = new ArrayList<>();
		for (String aRow : rows) {
			String[] camperAttributes = aRow.split(",");
			for(String camperAttribute : camperAttributes) {
				attributes.add(camperAttribute);
			}
		}
				
		for(int i = 0; i < attributes.size(); i = i + 15) {
			camper.setFirstName(attributes.get(i));
			camper.setLastName(attributes.get(i+1));
			camper.setDateOfBirth(LocalDate.parse(attributes.get(i+2)));
			camper.setStreetAddress(attributes.get(i+3));
			camper.setStreetAddressLineTwo(attributes.get(i+4));
			camper.setCity(attributes.get(i+5));
			camper.setStateCode(attributes.get(i+6));
			camper.setZipCode(attributes.get(i+7));
			camper.setPaymentStatus(convertPaymentStatus(attributes.get(i+8)));
			camper.setFamilyContact(Integer.parseInt((attributes.get(i+9))));
			camper.setAdditionalNotes(attributes.get(i+10));
			camper.setFamilyContactId(Integer.parseInt(attributes.get(i+11)));
			camper.setFamilyContactName(attributes.get(i+12));
			camper.setEmail(attributes.get(i+13));
			camper.setPhoneNumber(attributes.get(i+14));
	}
		return camper;
	}
	
	private boolean convertPaymentStatus(String paymentStatus) {
		System.out.println(paymentStatus);
		if(paymentStatus.equals("Paid")) {
			return true;
		}
		return false;
	}
}
