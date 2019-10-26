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
import com.esprit.beans.Comment;
import com.esprit.services.CommentService;


@Path("comment")
public class CommentWS {
	@EJB
	CommentService CommentService;
	

	private final String out = "success" ;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/add")	
	public Response addComment(@QueryParam("idUser") int idUser,
			@QueryParam("idPost") int idPost,
			@QueryParam("content") String content
	) {

		Timestamp date = new Timestamp(System.currentTimeMillis());		
		CommentService.addComment(content,date,idPost,idUser);
		return Response.status(Response.Status.CREATED).entity(out).build();
	}
	

	@DELETE
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeComment(@QueryParam("id") int id) {
		CommentService.deleteComment(id);
		return Response.status(Status.OK).entity("the comment has been deleted").build();
	}
	

	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response editComment(@QueryParam("idUser") int idUser,
			@QueryParam("idPost") int idPost,
			@QueryParam("content") String content
	) {
		Timestamp date = new Timestamp(System.currentTimeMillis());		
		CommentService.updateComment(content,date,idPost,idUser);
		return Response.status(Status.OK).entity("comment updated").build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
		public Response findComment(@PathParam("id") int idComment) {
			Comment c = CommentService.findComment(idComment);	
			return Response.status(Status.OK).entity(c).build();
	}


}