package com.esprit.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.esprit.utils.EmailUtil;
import com.esprit.utils.UserSession;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;


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
		else {
		//emailUtil.sendAttachmentEmail(toEmail, "payment done", "payment blabla", "/home/belka/lastone/profesional-network/profesional-network-ejb/src/main/java/com/esprit/beans/Interview.java");
		//emailUtil.sendEmail(toEmail,"Notification alert", "<p>You have a new notification</p></br><b>Automatic message sent by Professional Network</b>");
			
			/* CREATE NEW PDF + SEND IT TO EMAIL */

		    File dir = new File(System.getProperty("user.dir")+"/payments/");
		    if (!dir.exists()) dir.mkdirs();
			
			String filename =  System.getProperty("user.dir")+"/payments/test.pdf";
			Document document = new Document();

			try {
				PdfWriter.getInstance(document, new FileOutputStream(filename));
			} catch (FileNotFoundException | DocumentException e1) {
				e1.printStackTrace();
			}
			document.open();
			Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			Chunk chunk = new Chunk("Hello World", font);
			try {
				document.add(chunk);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			document.close();
			
			//mail.sendEmail(UserSession.getInstance().getEmail(), "your payment will be treated ", "instead of your payment :"+up.getPack().getTitle()+" /n"+"the Adminastrator will treat your payment details");
			emailUtil.sendAttachmentEmail(toEmail, "Professional Network : Your payment has been scheduled","Your payment has been scheduled\nYou can find the details in the attachment." ,
					filename,"osef");
			
		return Response.status(Response.Status.ACCEPTED).entity("Your email was sent to " +toEmail).build();
	}
}
}