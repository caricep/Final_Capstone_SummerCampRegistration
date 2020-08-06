package com.techelevator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.camper.model.Dorm;
import com.techelevator.dao.DormDAO;

//@PreAuthorize("isAuthenticated()")
@RestController
@CrossOrigin
public class DormController {

	private DormDAO dormDAO;
	
	public DormController(DormDAO dormDAO) {
		this.dormDAO = dormDAO;
	}
	
	@RequestMapping(path="/dorms", method=RequestMethod.GET)
	public List<Dorm> getListOfDorms() {
		return dormDAO.getListOfDorms();
	}
	
	
	@RequestMapping(path="/dorms", method=RequestMethod.POST)
	public void addCamperToDorm(@RequestBody Dorm dorm) {
		dormDAO.addCamperToDorm(dorm);
	}
	
	@RequestMapping(path="/dorms", method=RequestMethod.DELETE)
	public void removeCamperFromDorm(@RequestBody Dorm dorm) {
		dormDAO.removeCamperFromDorm(dorm);
	}
	
}
