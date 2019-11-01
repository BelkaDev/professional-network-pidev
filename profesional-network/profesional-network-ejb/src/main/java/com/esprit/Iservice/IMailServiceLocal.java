package com.esprit.Iservice;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import javax.ejb.Local;
import javax.mail.Authenticator;
import javax.mail.Session;

import com.esprit.beans.Comment;
import com.esprit.beans.Reaction;


@Local
public interface IMailServiceLocal {
	
	 void sendEmail(String toEmail, 
			String subject, String body);
	 boolean isValidEmailAddress(String email);
}
