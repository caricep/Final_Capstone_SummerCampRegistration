package com.techelevator.dao;

import java.io.File;
import java.util.List;

import com.techelevator.camper.model.Camper;

public interface CamperDAO {
	
	List<Camper> listAllCampers();
	void uploadFile(File file);
	

}
