package com.esprit.webservice;

import java.sql.Date;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// import com.esprit.beans.Candidate;
import com.esprit.beans.EnterpriseEvent;
import com.esprit.beans.EventParticipation;
import com.esprit.services.EnterpriseEventService;
// import com.esprit.services.EventParticipationService;

@Path("eventparticipation")
public class EventPartcipationWS {

	/*
	@EJB
	EventParticipationService eventparticipationws;
	private final String status = "{\"status\":\"ok\"}" ;
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("addparticipation")
	public Response addparticipation( 
			@QueryParam("eventId") int eventId,
			@QueryParam("candidateId") int candidateId

	) {
		EventParticipation ep = new EventParticipation();
		eventparticipationws.addParticipation(ep, eventId,candidateId);
		return Response.status(200).entity(status).build();
	}
	
	*/
}
