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

import com.esprit.beans.Reaction;

import com.esprit.enums.REACTION_TYPE;
import com.esprit.services.ReactionService;


@Path("reaction")
public class ReactionWS {
	@EJB
	ReactionService ReactionService;
	private final String out = "success";

	@POST
		@Consumes(MediaType.APPLICATION_JSON)
	@Path("add")	
	public Response addReaction(@QueryParam("idUser") int idUser,
			@QueryParam("idPost") int idPost,
			@QueryParam("type") REACTION_TYPE reactionType
	) {
	
		if(ReactionService.addReaction(idUser,idPost, reactionType))
		{
		return Response.status(Response.Status.CREATED).entity(out).build();
		}
		return Response.status(Status.NOT_FOUND).entity("post or user don't exist").build();
	}
	

	@DELETE
	@Path("delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeReaction(@QueryParam("id") int id) {
		if(ReactionService.deleteReaction(id))
		{
		return Response.status(Status.OK).entity("the reaction has been deleted").build();
		}
		return Response.status(Status.NOT_FOUND).entity("reaction doesn't exist").build();
		
	}
	

	@PUT
	@Path("update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response editReaction(@QueryParam("id") int idReaction,
	@QueryParam("type") REACTION_TYPE reactionType
	) {	
		if (ReactionService.updateReaction(idReaction,reactionType))
		{
		return Response.status(Status.OK).entity("reaction updated").build();
		}
		return Response.status(Status.NOT_FOUND).entity("reaction doesn't exist").build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
		public Response findReaction(@PathParam("id") int idReaction) {
			Reaction c = ReactionService.findReaction(idReaction);	
			return Response.status(Status.OK).entity(c).build();
	}

	
	@GET
	@Path("user={user}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findReactionsByUser(@PathParam("user") int id) {

		List<Reaction> reactions = ReactionService.findUserReactions(id);

		if (reactions == null)
			return Response.status(Status.NOT_FOUND).entity("No Reactions Found").build();
		else
			return Response.status(Status.OK).entity(reactions).build();

	}

	@GET
	@Path("post={post}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findReactionsOnPost(@PathParam("post") int id) {

		List<Reaction> reactions = ReactionService.findPostReactions(id);

		if (reactions == null)
			return Response.status(Status.NOT_FOUND).entity("No Reactions Found").build();
		else
			return Response.status(Status.OK).entity(reactions).build();

	}
	
}
