package com.esprit.resource;

import java.sql.Date;

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

import com.esprit.beans.candidate.Experience;
import com.esprit.service.candidate.CandidateService;

@Path("candidate")
public class CandidateWs {

	@EJB
	CandidateService cs;

	@POST
	@Path("addExperience")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addExperience(@QueryParam("designation") String designation,
			@QueryParam("type") String type,@QueryParam("startDate") Date startDate,@QueryParam("startDate") Date endDate) {
		Experience e = new Experience();
		e.setDesignation(designation);
		e.setType(type);
		e.setStartDate(startDate);
		e.setEndDate(endDate);
		cs.addExperience(e);
		return Response.status(Status.CREATED).entity("Experience Added").build();
	}
	
	@DELETE
	@Path("deleteExperience")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteExperience(@QueryParam("id")int id) {
		cs.deleteExperience(id);
		return Response.status(Status.OK).entity("the experience has been deleted").build();
	}
	
	@GET
	@Path("getExperience")
	@Produces(MediaType.APPLICATION_JSON)
	public Response displayQuiz(@QueryParam("id")int id) {
		return Response.status(Status.FOUND).entity(cs.displayExperience(id)).build();
	}
	
	@PUT
	@Path("updateExperience")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateQuestion(@QueryParam("id") int id, @QueryParam("designation") String designation) {
		cs.updateExperience(id, designation);
		return Response.status(Status.OK).entity("Experience updated").build();
	}
}
