package com.techelevator.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.camper.model.Camper;
import com.techelevator.camper.model.Template;
import com.techelevator.dao.SendGridDAO;

@PreAuthorize("isAuthenticated()")
@RestController
@CrossOrigin
public class SendGridController {
	
	private SendGridDAO sendGridDAO;
	
	public SendGridController(SendGridDAO sendGridDAO) {
		this.sendGridDAO = sendGridDAO;
	}
	
	
	@RequestMapping(path="/emails/welcome", method=RequestMethod.POST)
	public void sendWelcome(@RequestBody List<Camper> campers, Template templateId) throws IOException {
		System.out.println(templateId.getTemplate());
		sendGridDAO.sendWelcome(campers, templateId);
	}
	
	@RequestMapping(path="/emails/invoice", method=RequestMethod.POST)
	public void sendInvoice(@RequestBody List<Camper> campers, Template templateId) throws IOException {
		System.out.println(templateId.getTemplate());
		sendGridDAO.sendInvoice(campers, templateId);
	}
	
	@RequestMapping(path="/emails/newsletter", method=RequestMethod.POST)
	public void sendNews(@RequestBody List<Camper> campers, Template templateId) throws IOException {
		System.out.println(templateId.getTemplate());
		sendGridDAO.sendNews(campers, templateId);
	}
	

}
