package com.techelevator.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.camper.model.Camper;
import com.techelevator.dao.CamperDAO;

@RestController
@CrossOrigin
public class CamperController {

	private CamperDAO camperDAO;
	
	public CamperController(CamperDAO camperDao) {
		this.camperDAO = camperDao;
	}
	
	@RequestMapping(path="/campers", method=RequestMethod.GET)
	public List<Camper> getAllCampers() {
		return camperDAO.listAllCampers();
	}
	
	
}
