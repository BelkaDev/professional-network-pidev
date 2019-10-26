package com.esprit.resource;


import java.sql.Timestamp;

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

import com.esprit.beans.Reaction;
import com.esprit.enums.Reactions;
import com.esprit.services.ReactionService;


@Path("reaction")
public class ReactionWS {
	@EJB
	ReactionService ReactionService;
	private final String out = "success";

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/add")	
	public Response addReaction(@QueryParam("idUser") int idUser,
			@QueryParam("idPost") int idPost
	) {

		Timestamp date = new Timestamp(System.currentTimeMillis());		
		Reactions typeReaction = Reactions.None;
		ReactionService.addReaction(date, typeReaction ,idPost,idUser);
		return Response.status(Response.Status.CREATED).entity(out).build();
	}
	

	@DELETE
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeReaction(@QueryParam("id") int id) {
		ReactionService.deleteReaction(id);
		return Response.status(Status.OK).entity("the reaction has been deleted").build();
	}
	

	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response editReaction(@QueryParam("idUser") int idUser,
	@QueryParam("idPost") int idPost
	) {
		Timestamp date = new Timestamp(System.currentTimeMillis());		
		Reactions typeReaction = Reactions.None;
		ReactionService.updateReaction(date, typeReaction ,idPost,idUser);
		return Response.status(Status.OK).entity("reaction updated").build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
		public Response findReaction(@PathParam("id") int idReaction) {
			Reaction c = ReactionService.findReaction(idReaction);	
			return Response.status(Status.OK).entity(c).build();
	}

	
}
