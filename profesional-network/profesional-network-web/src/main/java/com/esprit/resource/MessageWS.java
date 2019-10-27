package com.esprit.resource;


import java.sql.Timestamp;

import java.util.List;

import javax.ejb.EJB;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import com.esprit.beans.Message;
import com.esprit.services.MessageService;

@Path("message")
public class MessageWS {
	@EJB
	MessageService MessageService;
	

	private final String out = "success" ;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/add")
	public Response addMessage(@QueryParam("sender") int idSender,
			@QueryParam("recipient") int idRecipient,
			@QueryParam("body") String body,
			@QueryParam("status") int status
	) {

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());		
		MessageService.addMessage(timestamp,idSender, idRecipient, body, status);
		return Response.status(Response.Status.CREATED).entity(out).build();
	}
	

	@DELETE
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeMessage(@QueryParam("id") int id) {
		MessageService.deleteMessage(id);
		return Response.status(Status.OK).entity("the message has been deleted").build();
	}
	

	@PUT
	@Path("/edit")
	@Produces(MediaType.APPLICATION_JSON)
	public Response editMessage(@QueryParam("sender") int idSender,
			@QueryParam("recipient") int idRecipient,
			@QueryParam("body") String body,
			@QueryParam("status") int status
	) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());		
		MessageService.editMessage(timestamp,idSender, idRecipient, body, status);
		return Response.status(Status.OK).entity("message updated").build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
		public Response findMessage(@PathParam("id") int idMessage) {
			Message m = MessageService.findMessage(idMessage);	
			return Response.status(Status.OK).entity(m).build();

	}

	@GET
	@Path("/sender={sender}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findMessageBySender(@PathParam("sender") int sender) {


		List<Message> messages = MessageService.findMsgBySender(sender);

		if (messages == null)
			return Response.status(Status.NOT_FOUND).entity("No Messages Found").build();
		else
			return Response.status(Status.OK).entity(messages).build();

	}

	@GET
	@Path("/reciever={reciever}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findMessageByReciever(@PathParam("reciever") int reciever) {

		List<Message> messages = MessageService.findMsgByReciever(reciever);

		if (messages == null)
			return Response.status(Status.NOT_FOUND).entity("No Messages Found").build();
		else
			return Response.status(Status.OK).entity(messages).build();

	}


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("all")
	public Response findAllMessages() {

		List<Message> Messages = MessageService.findAllMessages();

		if (Messages == null)
			return Response.status(Status.NOT_FOUND).entity("No Messages Found").build();
		else
			return Response.ok(Messages).build();

	}
	
	/* Trash / Uncomplete
	
	@GET
	@Path("/finddate/{id}/{iddest}")
	@Produces("application/json")
	public Response findbydate(@PathParam("id") Integer iduser, @PathParam("iddest") Integer iddest) {

		String m = MessageService.findmesgbydate(iduser, iddest);
		return Response.status(Status.OK).entity(m).build();

	}*/

}
