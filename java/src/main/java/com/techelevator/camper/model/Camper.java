package com.techelevator.camper.model;

import java.time.LocalDate;

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
	private String additionalNotes;
	
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
	
	public void setAdditionalNotes(String additionalNotes) {
		this.additionalNotes = additionalNotes;
	}
	
}
