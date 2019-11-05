package com.esprit.resource;



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
import com.esprit.services.NotificationService;
import com.esprit.utils.UserSession;

@Path("message")
public class MessageWS {
	@EJB
	MessageService MessageService;
	
	@EJB
	NotificationService notifService;
	private final String out = "success" ;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("send")
	public Response addMessage(@QueryParam("to") int idRecipient,
			@QueryParam("content") String body
	) {	

		int idSender = UserSession.getInstance().getId();
		MessageService.sendMessage(idSender, idRecipient, body);
		return Response.status(Response.Status.CREATED).entity(out).build();
	}
	

	@DELETE
	@Path("delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeMessage(@QueryParam("id") int id) {
		MessageService.deleteMessage(id);
		return Response.status(Status.OK).entity("the message has been deleted").build();
	}
	

	@PUT
	@Path("update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response editMessage(@QueryParam("id") int idMessage,
			@QueryParam("body") String body
	) {		
		MessageService.editMessage(idMessage, body);
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
	@Path("mymessages")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findMyMessages() {
		

		List<Message> messages = MessageService.findMsgByReciever(UserSession.getInstance().getId());

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
	

}
