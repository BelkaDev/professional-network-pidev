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
import com.esprit.beans.Comment;
import com.esprit.services.CommentService;
import com.esprit.utils.UserSession;


@Path("comment")
public class CommentWS {
	@EJB
	CommentService CommentService;
	

	private final String out = "success" ;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("add")	
	public Response addComment(@QueryParam("post") int idPost,
			@QueryParam("content") String content
	) {
		int idUser = UserSession.getInstance().getId();
		if(CommentService.addComment(idUser,idPost,content)) {
		return Response.status(Response.Status.CREATED).entity(out).build();
	}
		return Response.status(Response.Status.NOT_FOUND).entity("post or user not found.").build();
	}
	

	@DELETE
	@Path("delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeComment(@QueryParam("id") int id) {
		CommentService.deleteComment(id);
		return Response.status(Status.OK).entity("the comment has been deleted").build();
	}
	

	@PUT
	@Path("update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response editComment(@QueryParam("id") int idCom,
			@QueryParam("content") String content
	) {		
		CommentService.updateComment(idCom,content);
		return Response.status(Status.OK).entity("comment updated").build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
		public Response findComment(@PathParam("id") int idComment) {
			Comment c = CommentService.findComment(idComment);	
			return Response.status(Status.OK).entity(c).build();
	}
	
	
	@GET
	@Path("/user={user}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findCommentsByUser(@PathParam("user") int id) {

		List<Comment> reactions = CommentService.findUserComments(id);

		if (reactions == null)
			return Response.status(Status.NOT_FOUND).entity("No Comments Found").build();
		else
			return Response.status(Status.OK).entity(reactions).build();

	}

	@GET
	@Path("/post={post}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findCommentsOnPost(@PathParam("post") int id) {

		List<Comment> reactions = CommentService.findPostComments(id);

		if (reactions == null)
			return Response.status(Status.NOT_FOUND).entity("No Comments Found").build();
		else
			return Response.status(Status.OK).entity(reactions).build();

	}	
}
