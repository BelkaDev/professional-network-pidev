package com.esprit.webservice;

import java.sql.Date;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

// import com.esprit.beans.Candidate;
import com.esprit.beans.EnterpriseEvent;
import com.esprit.beans.EventParticipation;
import com.esprit.services.EnterpriseEventService;
import com.esprit.services.EventParticipationService;
// import com.esprit.services.EventParticipationService;

@Path("eventparticipation")
public class EventPartcipationWS {

	
	@EJB
	EventParticipationService eventparticipationws;
	private final String status = "{\"status\":\"ok\"}" ;
	private final String statusfailed = "{\"status\":\"already participated\"}" ;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("addparticipation")
	public Response addparticipation( 
			@QueryParam("eventId") int eventId,
			@QueryParam("userid") int userid
			

	) {
		EventParticipation ep = new EventParticipation();
		if(eventparticipationws.UniquePart(eventId,userid)) {
			

		
		eventparticipationws.addParticipation(ep, eventId,userid);
		return Response.status(Status.CREATED).entity(status).build();
 	}
	return Response.status(Status.NOT_ACCEPTABLE).entity(status).build();
 	}
	
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getuniquepart")
	public Response getjoobofferbyent(@QueryParam("eventId") int eventId,
									  @QueryParam("userid") int userid) {
		if(eventparticipationws.UniquePart(eventId, userid)){
			return Response.status(Status.CREATED).entity(status).build();
		}
		return Response.status(Status.NOT_ACCEPTABLE).entity(statusfailed).build();
	}
	
	
	
	
}
