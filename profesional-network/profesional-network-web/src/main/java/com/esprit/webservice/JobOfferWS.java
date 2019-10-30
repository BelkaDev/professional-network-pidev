package com.esprit.webservice;


import java.sql.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.esprit.beans.JobOffer;
import com.esprit.services.JobOfferService;

@Path("joboffer")
public class JobOfferWS {

	
	@EJB
	JobOfferService jobofferws;
	private final String status = "{\"status\":\"ok\"}" ;
	
	
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getjoboffer")
	public List<JobOffer> getjooboffer() {
		return jobofferws.getAllJobOffer();
	}
	
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getjobofferbyexp")
	public List<JobOffer> getjoobofferbyexp(@QueryParam("JOexperience") int JOexperience) {
		return jobofferws.getJobofferByExperience(JOexperience);
	}
	
	
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("addjoboffer")
	public Response addjoboffer(@QueryParam("JOtitle") String JOtitle,
			@QueryParam("JOarea") String JOarea,
			@QueryParam("JOdate") Date JOdate,
			@QueryParam("JOdescription") String JOdescription, 
			@QueryParam("JOexperience") int JOexperience,
			@QueryParam("hrId") int hrId,
			@QueryParam("plId") int plId

	) {
		JobOffer j = new JobOffer(JOtitle, JOarea, JOdate, JOdescription,JOexperience);

		jobofferws.AddJobOffer(j,hrId,plId);
		return Response.status(200).entity(status).build();
	}
	
	
	
	@PUT
	@Path("updatejoboffer")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatejoboffer(@QueryParam("JOid") int id,@QueryParam("JOtitle") String JOtitle,
			@QueryParam("JOarea") String JOarea,
			@QueryParam("JOdescription") String JOdescription
	) {
		
		jobofferws.ModifyJobOffer(id, JOtitle, JOarea, JOdescription);

		return Response.status(200).entity(status).build();
	}
	
	

	@DELETE
	@Path("deletejoboffer")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletejoboffer(@QueryParam("id") int id) {
		jobofferws.DeleteJobOffer(id);
		return Response.status(200).entity(status).build();
	}
	
	
}
