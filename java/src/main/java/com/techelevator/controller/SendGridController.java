package com.techelevator.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.camper.model.Camper;
import com.techelevator.camper.model.TemplateId;
import com.techelevator.dao.SendGridDAO;

@RestController
@CrossOrigin
public class SendGridController {
	
	private SendGridDAO sendGridDAO;
	
	public SendGridController(SendGridDAO sendGridDAO) {
		this.sendGridDAO = sendGridDAO;
	}
	
	
	@RequestMapping(path="/emails", method=RequestMethod.POST)
	public void sendEmails(@RequestBody List<Camper> campers, TemplateId templateId) throws IOException {
		
		sendGridDAO.sendEmails(campers, templateId);
	}

}
