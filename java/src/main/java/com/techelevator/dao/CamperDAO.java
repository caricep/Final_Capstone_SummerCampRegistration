package com.techelevator.dao;

import java.util.List;

import com.techelevator.camper.model.Camper;
import com.techelevator.camper.model.FamilyContact;
import com.techelevator.camper.model.History;

public interface CamperDAO {
	
	List<Camper> listAllCampers();
	void addCamper(Camper camper);
	void history(String changeType, String changeSpecifics, String changeDateAndTime, String userName, int updateStatusId);
	List<History> listAllHistory();
	void updateCamper(Camper camper);
	void updateCamperAddress(Camper camper);
	void updateCamperPaymentStatus(Camper camper);
	void updateCamperNotes(Camper camper);
	void updateFamilyContactName(FamilyContact familyContact);
	void updateFamilyContactEmailAddress(FamilyContact familyContact);
	void updateFamilyContactPhoneNumber(FamilyContact familyContact);

}
