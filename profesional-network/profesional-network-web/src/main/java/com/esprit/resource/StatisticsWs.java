package com.esprit.resource;

import java.sql.Date;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.esprit.services.StatistiqueService;

@Path("stat")
public class StatisticsWs {
	@EJB
	StatistiqueService cs;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("projectsAdded")
	public Response projectsAdded(@QueryParam("start") Date start, @QueryParam("end") Date end

	) {
		return Response.status(200).entity("the number of the added projects from " + start + " for " + end + " , is :"
				+ cs.numProjectsadded(start, end)).build();
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("enterprisesAdded")
	public Response enterprisesAdded(@QueryParam("start") Date start, @QueryParam("end") Date end

	) {
		return Response.status(200).entity("the number of the added enterprises from " + start + " for " + end + " , is :"
				+ cs.numEntreprisesAdded(start, end)).build();
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("condidatsAdded")
	public Response condidatsAdded(@QueryParam("start") Date start, @QueryParam("end") Date end

	) {
		return Response.status(200).entity("the number of the added Candidats from " + start + " for " + end + " , is :"
				+ cs.numCandidateAdded(start, end)).build();
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("condidatsRecruted")
	public Response condidatsRecruted(@QueryParam("start") Date start, @QueryParam("end") Date end

	) {
		return Response.status(200).entity("the number of the added Candidats from " + start + " for " + end + " , is :"
				+ cs.recrutedCandidat(start, end)).build();
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("enterpriseMostActif")
	public Response enterpriseMostActif(@QueryParam("start") Date start, @QueryParam("end") Date end

	) {
		return Response.status(200).entity("the most actif company  from " + start + " for " + end + " , is :"
				+ cs.enterpriseMostActif(start, end).getEname()).build();
	}

}
