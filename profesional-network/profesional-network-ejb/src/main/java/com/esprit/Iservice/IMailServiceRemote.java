package com.esprit.Iservice;


import java.util.Properties;
import javax.ejb.Remote;
import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


@Remote
public interface IMailServiceRemote {

	 void sendEmail(String toEmail, 
			String subject, String body);
	 boolean isValidEmailAddress(String email);

}
