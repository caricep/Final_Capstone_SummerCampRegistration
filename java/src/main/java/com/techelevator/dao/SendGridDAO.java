package com.techelevator.dao;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.techelevator.camper.model.Camper;
import com.techelevator.camper.model.Template;


public interface SendGridDAO {
	
	void sendWelcome(List<Camper> camper, Template templateId) throws IOException;
	void sendInvoice(List<Camper> camper, Template templateId) throws IOException;
	void sendNews(List<Camper> camper, Template templateId) throws IOException;

}
