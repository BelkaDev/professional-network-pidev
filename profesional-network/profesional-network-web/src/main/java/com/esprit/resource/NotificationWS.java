package com.esprit.resource;


import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import com.esprit.services.NotificationService;
import com.esprit.beans.Notification;
import com.esprit.enums.NOTIFICATION_TYPE;

@Stateless
@Path("notifs")
public class NotificationWS { 
	
	@EJB
    NotificationService notificationService = new NotificationService();

    @GET
    @Path("all")
    @Asynchronous
    @Produces(MediaType.APPLICATION_JSON)
    public void getNotifications(@Suspended final AsyncResponse ar) {
        ar.setTimeout(10, TimeUnit.SECONDS);
        
        System.out.println("notifs");
        List<Notification> result = notificationService.listNotifications();
        Response resp = Response.ok(result).build();
        ar.resume(resp);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("add")
    public void CreateNotification(@QueryParam("reciever")int idReciever
    		,@QueryParam("body")String body
    		,@QueryParam("by")int trigger,@QueryParam("target")int target) {
		NOTIFICATION_TYPE type = NOTIFICATION_TYPE.Comment;
    	String message = notificationService.parseText(type,body,trigger,target);
    	notificationService.CreateNotification(idReciever, message, type,trigger,target);		
    }
    
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("show")
	public Response showNotification(@QueryParam("id")int id) {	
		Notification notif = notificationService.get(id);
		if (notif == null)
		return Response.status(Response.Status.NOT_FOUND).entity("Notification doesn't exist").build();
		else
		return Response.status(Status.OK).entity(notif).build();
	}
}

