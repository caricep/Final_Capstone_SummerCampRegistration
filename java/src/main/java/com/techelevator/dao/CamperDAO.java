package com.techelevator.dao;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import com.techelevator.camper.model.Camper;
import com.techelevator.camper.model.History;

public interface CamperDAO {
	
	List<Camper> listAllCampers();
	void addCamper(Camper camper);
	void history(String changeType, String changeSpecifics, String changeDateAndTime, String userName);
	List<History> listAllHistory();
	

}
