package com.techelevator.controller;

import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techelevator.camper.model.Camper;
import com.techelevator.camper.model.FamilyContact;
import com.techelevator.camper.model.History;
import com.techelevator.dao.CamperDAO;

@PreAuthorize("isAuthenticated()")
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
	
	@RequestMapping(path="/campers", method=RequestMethod.PUT)
	public void updateCamper(@RequestBody Camper camper, Principal principal) {
		camperDAO.updateCamper(camper);
		camperDAO.history("UPDATE", "Camper Updated", dateAndTimeGetter(), principal.getName(), 1);
	}
	
	@RequestMapping(path="/campers/address", method=RequestMethod.PUT)
	public void updateCamperAddress(@RequestBody Camper camper, Principal principal) {
		camperDAO.updateCamperAddress(camper);
		camperDAO.history("UPDATE", "Updated Address", dateAndTimeGetter(), principal.getName(), 1);
	}
	
	@RequestMapping(path="/campers/payment-status", method=RequestMethod.PUT)
	public void updateCamperPaymentStatus(@RequestBody Camper camper, Principal principal) {
		camperDAO.updateCamperPaymentStatus(camper);
		camperDAO.history("UPDATE", "Updated Payment Status", dateAndTimeGetter(), principal.getName(), 1);
	}
	
	@RequestMapping(path="/campers/notes", method=RequestMethod.PUT)
	public void updateCamperNotes(@RequestBody Camper camper, Principal principal) {
		camperDAO.updateCamperNotes(camper);
		camperDAO.history("UPDATE", "Updated Additional Notes", dateAndTimeGetter(), principal.getName(), 1);
	}
	
	@RequestMapping(path="/family-contacts/contact-name", method=RequestMethod.PUT)
	public void updateFamilyContactName(@RequestBody FamilyContact familyContact, Principal principal) {
		camperDAO.updateFamilyContactName(familyContact);
		camperDAO.history("UPDATE", "Updated Emergency Contact Name", dateAndTimeGetter(), principal.getName(), 1);
	}
	
	@RequestMapping(path="/family-contacts/contact-email", method=RequestMethod.PUT)
	public void updateFamilyContactEmailAddress(@RequestBody FamilyContact familyContact, Principal principal) {
		camperDAO.updateFamilyContactEmailAddress(familyContact);
		camperDAO.history("UPDATE", "Updated Emergency Contact Email Address", dateAndTimeGetter(), principal.getName(), 1);
	}
	
	@RequestMapping(path="/family-contacts/contact-phone", method=RequestMethod.PUT)
	public void updateFamilyContactPhoneNumber(@RequestBody FamilyContact familyContact, Principal principal) {
		camperDAO.updateFamilyContactPhoneNumber(familyContact);
		camperDAO.history("UPDATE", "Updated Emergency Contact Phone Number", dateAndTimeGetter(), principal.getName(), 1);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path="/file-upload", method=RequestMethod.POST)
	public void uploadFile(@RequestBody MultipartFile file, Principal principal) {
        List<Camper> campers = new ArrayList();
		if (!file.isEmpty()) {
	        try {
	            byte[] bytes = file.getBytes();
	            String completeData = new String(bytes);
	            String[] rows = completeData.split("\n");
	            System.out.println(rows.length);
	            for(int i= 1; i < rows.length; i++) {
	            	Camper camper = new Camper();
	            	camper = camper.mapper(rows[i]);
	            	campers.add(camper);
	            	}
	        } catch(IOException error) {
	        	System.out.println(error.getMessage());
	        }
		}
		for (Camper camper : campers) {
			camperDAO.addCamper(camper);
		}
		camperDAO.history("UPLOAD","Uploaded Campers", dateAndTimeGetter(), principal.getName(), 2);
		
	}
	
	@RequestMapping(path="/history", method=RequestMethod.GET)
	public List<History> getHistory() {
		return camperDAO.listAllHistory();
	}
	
	
	
	public String dateAndTimeGetter() {
		String dateAndTime = (new SimpleDateFormat("MM/dd/YYYY hh:mm:ss a")).format(new Date());
		return dateAndTime;
	}
	
}
