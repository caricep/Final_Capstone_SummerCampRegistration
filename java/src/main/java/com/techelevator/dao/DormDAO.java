package com.techelevator.dao;

import java.util.List;

import com.techelevator.camper.model.Dorm;

public interface DormDAO {

	List<Dorm> getListOfDorms();
	void addCamperToDorm(Dorm dorm);
	void removeCamperFromDorm(Dorm dorm);
	
}
