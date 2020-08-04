package com.techelevator.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path="/file-upload", method=RequestMethod.POST)
	public void uploadFile(@RequestBody MultipartFile file) {
		if (!file.isEmpty()) {
	        try {
	            byte[] bytes = file.getBytes();
	            String completeData = new String(bytes);
	            String[] rows = completeData.split("#");
	            System.out.println(rows);
	            for(String row : rows) {
	            	System.out.println(row);
	            	System.out.println("----------");
	            }
	            System.out.println("END OF ROWS --------");
	        } catch(IOException error) {
	        	System.out.println(error.getMessage());
	        }
		}
	}
	
	
}
