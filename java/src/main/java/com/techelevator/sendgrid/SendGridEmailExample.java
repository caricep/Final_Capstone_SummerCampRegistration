package com.techelevator.sendgrid;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;

public class SendGridEmailExample {

	public static void main(String[] args) throws IOException {
	    Email from = new Email("littleottersummercamp@gmail.com");
	    String subject = "Sending with SendGrid is Fun";
	    Email to = new Email("jearnold22@gmail.com");
	    Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
	    Mail mail = new Mail(from, subject, to, content);
	    mail.setTemplateId("d-63c038f49692471fa75ca3f746dd7104");

	    SendGrid sg = new SendGrid("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");
	    System.out.println("API Key is " + System.getenv("SENDGRID_API_KEY"));
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
