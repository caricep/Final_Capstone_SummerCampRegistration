package com.techelevator.sendgrid;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import com.techelevator.camper.model.Camper;
import com.techelevator.camper.model.TemplateId;
import com.techelevator.dao.SendGridDAO;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class SendGridTemplateExample implements SendGridDAO {
	
//	Personalization p1 = new Personalization();
	
	String templateId;

	public SendGridTemplateExample() {
		
	}

	@Override
	public void sendEmails(List<Camper> campers, TemplateId templateId) throws IOException {
		// We iterate through our list of campers and add their emails to the personalization object
//		for(Camper camper : campers) {
//			Email email = new Email(camper.getEmail());
//			p1.addTo(email);
//		}
		
		System.out.println(templateId.getTemplateId());
		
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
	    mail.setTemplateId(templateId.getTemplateId());
	    
	    // Load in this API key through a .env
	    SendGrid sg = new SendGrid("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");
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
