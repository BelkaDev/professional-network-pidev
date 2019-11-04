package com.esprit.resource.candidate;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.esprit.service.candidate.ContactService;
import com.esprit.service.candidate.JobService;

@Path("job")
public class JobWs {

	@EJB
	JobService js;
	
	@GET
	@Path("searchJob")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchJob(@QueryParam("criteria") String criteria) {
		return Response.status(Status.FOUND).entity(js.searchJob(criteria)).build();
	}
	
	@GET
	@Path("getJobApplications")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getJobApplications(@QueryParam("candidateId") int candidateId) {
		return Response.status(Status.FOUND).entity(js.getJobApplications(candidateId)).build();
	}
	
	@POST
	@Path("applyForAJob")
	@Produces(MediaType.APPLICATION_JSON)
	public Response applyForAJob(@QueryParam("candidateId") int candidateId,
			@QueryParam("jobId") int jobId) {
		js.applyForAJob(candidateId, jobId);
		return Response.status(Status.CREATED).entity("Applied for a job").build();
	}
	
}
