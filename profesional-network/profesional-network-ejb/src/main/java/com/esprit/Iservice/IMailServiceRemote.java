package com.esprit.Iservice;


import java.util.Properties;
import javax.ejb.Remote;
import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


@Remote
public interface IMailServiceRemote {

	 void sendEmail(Session session, String toEmail, 
			String subject, String body);
	 Properties setProperties();
	 Authenticator setAuth(String fromEmail,String password,String toEmail);
	 boolean isValidEmailAddress(String email);

}
