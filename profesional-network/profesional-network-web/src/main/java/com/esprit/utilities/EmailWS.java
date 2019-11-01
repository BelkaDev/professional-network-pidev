package com.esprit.utilities;
import java.util.Properties;

import javax.ejb.EJB;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.esprit.services.QuizService;
import com.esprit.utils.EmailUtil;


@Path("mail")
public class EmailWS {
	
	@EJB
	EmailUtil emailUtil = new EmailUtil();
	@GET
	@Path("send/{to}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response sendMail(@PathParam("to")String toEmail) {

		if (!emailUtil.isValidEmailAddress(toEmail))
		return Response.status(Response.Status.METHOD_NOT_ALLOWED).entity("Email is not valid").build();
		else
		emailUtil.sendEmail(toEmail,"Notification alert", "<p>You have a new notification</p></br><b>Automatic message sent by Professional Network</b>");
		return Response.status(Response.Status.ACCEPTED).entity("Your email was sent to " +toEmail).build();
	}
}