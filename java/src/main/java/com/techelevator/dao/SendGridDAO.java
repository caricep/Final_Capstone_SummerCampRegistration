package com.techelevator.dao;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.techelevator.camper.model.Camper;
import com.techelevator.camper.model.TemplateId;


public interface SendGridDAO {
	
	void sendEmails(List<Camper> camper, TemplateId templateId) throws IOException;

}
