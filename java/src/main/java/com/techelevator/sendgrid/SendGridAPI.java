package com.techelevator.sendgrid;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import com.techelevator.camper.model.Camper;
import com.techelevator.camper.model.Template;
import com.techelevator.dao.SendGridDAO;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class SendGridAPI implements SendGridDAO {
	
//	Personalization p1 = new Personalization();
	
	String templateId;

	public SendGridAPI() {
		
	}

	@Override
	public void sendWelcome(List<Camper> campers, Template templateId) throws IOException {
		System.out.println(templateId.getTemplate());
		
	    Email from = new Email("littleottersummercamp@gmail.com");
	    String subject = "I'm replacing the subject tag";
//	    Email to = new Email("jearnold22@gmail.com");
	    Content content = new Content("text/html", "I'm replacing the <strong>body tag</strong>");
//	    Mail mail = new Mail(from, subject, p1, content);
	    Mail mail = new Mail();
	    mail.setFrom(from);
	    mail.addContent(content);
		
		for(int i = 0; i < campers.size(); i++) {
			Personalization p1 = new Personalization();
			Camper camper = campers.get(i);
			Email email = new Email(camper.getEmail());
			p1.addTo(email);
//			p1.addDynamicTemplateData("firstName", camper.getFirstName());
		    mail.addPersonalization(p1);
		}
		


	    // Load our template ID variable
	    mail.setTemplateId("d-63c038f49692471fa75ca3f746dd7104");
	    
	    // Load in this API key through a .env
	    SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
	    Request request = new Request();
	    try {
	      request.setMethod(Method.POST);
	      request.setEndpoint("mail/send");
	      request.setBody(mail.build());
	      Response response = sg.api(request);
	      System.out.println(response.getStatusCode());
	      System.out.println(response.getBody());
	      System.out.println(response.getHeaders());
	    } catch (IOException ex) {
	      throw ex;
	    }
	}
	
	@Override
	public void sendInvoice(List<Camper> campers, Template templateId) throws IOException {
		// We iterate through our list of campers and add their emails to the personalization object
//		for(Camper camper : campers) {
//			Email email = new Email(camper.getEmail());
//			p1.addTo(email);
//		}
		
		System.out.println(templateId.getTemplate());
		
	    Email from = new Email("littleottersummercamp@gmail.com");
	    String subject = "I'm replacing the subject tag";
//	    Email to = new Email("jearnold22@gmail.com");
	    Content content = new Content("text/html", "I'm replacing the <strong>body tag</strong>");
//	    Mail mail = new Mail(from, subject, p1, content);
	    Mail mail = new Mail();
	    mail.setFrom(from);
	    mail.addContent(content);
		
		for(int i = 0; i < campers.size(); i++) {
			Personalization p1 = new Personalization();
			Camper camper = campers.get(i);
			Email email = new Email(camper.getEmail());
			p1.addTo(email);
			p1.addDynamicTemplateData("firstName", camper.getFirstName());
			p1.addDynamicTemplateData("lastName", camper.getLastName());
		    mail.addPersonalization(p1);
		}
		


	    // Load our template ID variable
	    mail.setTemplateId("d-47c5b37df1bd47c8bc6b77bbd4908e05");
	    
	    // Load in this API key through a .env
	    SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
	    Request request = new Request();
	    try {
	      request.setMethod(Method.POST);
	      request.setEndpoint("mail/send");
	      request.setBody(mail.build());
	      Response response = sg.api(request);
	      System.out.println(response.getStatusCode());
	      System.out.println(response.getBody());
	      System.out.println(response.getHeaders());
	    } catch (IOException ex) {
	      throw ex;
	    }
	}
	
	@Override
	public void sendNews(List<Camper> campers, Template templateId) throws IOException {
		// We iterate through our list of campers and add their emails to the personalization object
//		for(Camper camper : campers) {
//			Email email = new Email(camper.getEmail());
//			p1.addTo(email);
//		}
		
		System.out.println(templateId.getTemplate());
		
	    Email from = new Email("littleottersummercamp@gmail.com");
	    String subject = "I'm replacing the subject tag";
//	    Email to = new Email("jearnold22@gmail.com");
	    Content content = new Content("text/html", "I'm replacing the <strong>body tag</strong>");
//	    Mail mail = new Mail(from, subject, p1, content);
	    Mail mail = new Mail();
	    mail.setFrom(from);
	    mail.addContent(content);
		
		for(int i = 0; i < campers.size(); i++) {
			Personalization p1 = new Personalization();
			Camper camper = campers.get(i);
			Email email = new Email(camper.getEmail());
			p1.addTo(email);
//			p1.addDynamicTemplateData("firstName", camper.getFirstName());
		    mail.addPersonalization(p1);
		}
		


	    // Load our template ID variable
	    mail.setTemplateId("d-ebbdf134325e4a8182b03282ad577e22");
	    
	    // Load in this API key through a .env
	    SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
	    Request request = new Request();
	    try {
	      request.setMethod(Method.POST);
	      request.setEndpoint("mail/send");
	      request.setBody(mail.build());
	      Response response = sg.api(request);
	      System.out.println(response.getStatusCode());
	      System.out.println(response.getBody());
	      System.out.println(response.getHeaders());
	    } catch (IOException ex) {
	      throw ex;
	    }
	}
}
