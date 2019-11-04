package com.esprit.utils;


import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.esprit.Iservice.IMailServiceLocal;
import com.esprit.Iservice.IMailServiceRemote;

@Stateless
@LocalBean
public class EmailUtil implements IMailServiceLocal, IMailServiceRemote {


	public void sendEmail(String toEmail, String subject, String body){
		try
	    {

			final String fromEmail = "ProfessionalNetowkPidev@gmail.com"; 
			final String password = "professionalnetworkpidev"; 
			Authenticator auth = new Authenticator() {
				//override the getPasswordAuthentication method
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail, password);
				}
			};
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
			props.put("mail.smtp.port", "587"); //TLS Port
			props.put("mail.smtp.auth", "true"); //enable authentication
			props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

			Session session = Session.getInstance(props, auth);
			
	      MimeMessage msg = new MimeMessage(session);
	      //set message headers
	      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
	      msg.addHeader("format", "flowed");
	      msg.addHeader("Content-Transfer-Encoding", "8bit");

	      msg.setFrom(new InternetAddress("no_reply@example.com", "Professional Network"));

	      msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

	      msg.setSubject(subject, "UTF-8");

	      msg.setText(body, "utf-8", "html");

	      msg.setSentDate(new Date());

	      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
    	  Transport.send(msg);  

	      System.out.println("Sent Successfully");
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	}

	public void sendAttachmentEmail(String toEmail, String subject, String body,String filepath, String filename){
		try{
			
			final String fromEmail = "ProfessionalNetowkPidev@gmail.com"; 
			final String password = "professionalnetworkpidev"; 
			Authenticator auth = new Authenticator() {
				//override the getPasswordAuthentication method
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail, password);
				}
			};
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
			props.put("mail.smtp.port", "587"); //TLS Port
			props.put("mail.smtp.auth", "true"); //enable authentication
			props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

			Session session = Session.getInstance(props, auth);
			

			
	          MimeMessage msg = new MimeMessage(session);
	          msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
		      msg.addHeader("format", "flowed");
		      msg.addHeader("Content-Transfer-Encoding", "8bit");

		      msg.setFrom(new InternetAddress("no_reply@example.com", "Professional Network"));

		      msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

		      msg.setSubject(subject, "UTF-8");

		      msg.setText(body, "utf-8", "html");

		      msg.setSentDate(new Date());

		      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
		      
	         // Create the message body part
	         BodyPart messageBodyPart = new MimeBodyPart();

	         // Fill the message
	         messageBodyPart.setText(body);
	         
	         // Create a multipart message for attachment
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Second part is attachment
	         messageBodyPart = new MimeBodyPart();
	         
	         DataSource source = new FileDataSource(filepath);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(filename);
	         multipart.addBodyPart(messageBodyPart);

	         // Send the complete message parts
	         msg.setContent(multipart);

	         // Send message
	         Transport.send(msg);
	         System.out.println("EMail Sent Successfully with attachment!!");
	      }catch (MessagingException e) {
	         e.printStackTrace();
	      } catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
		}
	}
	
	@Override
	public boolean isValidEmailAddress(String email) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
		}
}