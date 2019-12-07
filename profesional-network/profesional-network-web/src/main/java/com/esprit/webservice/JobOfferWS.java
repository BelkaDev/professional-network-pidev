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
import javax.ws.rs.core.Response.Status;

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
	public Response getjooboffer() {
		//return jobofferws.getAllJobOffer();
		return Response.status(Status.OK).entity(jobofferws.getAllJobOffer()).build();
	}
	
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getjobofferbyent")
	public Response getjoobofferbyent(@QueryParam("entid") int entid) {
		return Response.status(Status.OK).entity(jobofferws.getJobofferByEnt(entid)).build();
	}
	
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getjobofferbyexp")
	public List<JobOffer> getjoobofferbyexp(@QueryParam("JOexperience") int JOexperience) {
		return jobofferws.getJobofferByExperience(JOexperience);
	}
	
	
	
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getjobofferbyid")
	public JobOffer getjoobofferbyid(@QueryParam("JOid") int JOid) {
		return jobofferws.getJobofferById(JOid);
	}
	
	
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getjobofferorderbyvues")
	public List<JobOffer> getjoobofferorderbyvues() {
		return jobofferws.getJobofferOrderByVues();
	}
	
	
	
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	@Path("searchjoboffer")
	public List<JobOffer> searchjoboffer(@QueryParam("search") String search) {
		return jobofferws.SearchJoboffer(search);
	}
	
	
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("addjoboffer")
	public Response addjoboffer(@QueryParam("JOtitle") String JOtitle,
			@QueryParam("JOarea") String JOarea,
			@QueryParam("JOdescription") String JOdescription, 
			@QueryParam("JOexperience") int JOexperience,
			@QueryParam("interests") String interests
			

	) {
		JobOffer j = new JobOffer(JOtitle, JOarea, JOdescription,JOexperience,interests);

		jobofferws.AddJobOffer(j);
		return Response.status(200).entity(status).build();
	}
	
	
	
	@PUT
	@Path("updatejoboffer")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatejoboffer(@QueryParam("JOid") int id,
			@QueryParam("JOtitle") String JOtitle,
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
	
	
	
	@PUT
	@Path("validatejoboffer")
	@Produces(MediaType.APPLICATION_JSON)
	public Response validatejoboffer(@QueryParam("JOid") int id) {
		jobofferws.ValidateJoboffer(id);
		
		return Response.status(200).entity(status).build();
	}
	
	
	
	
	
}
