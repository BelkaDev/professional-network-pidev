package com.esprit.resource;


import java.sql.Timestamp;

import java.util.Date;
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

import com.esprit.beans.Post;
import com.esprit.beans.Post;
import com.esprit.beans.Question;
import com.esprit.beans.User;
import com.esprit.enums.Posts;
import com.esprit.services.PostService;


@Path("post")
public class PostWS {
	@EJB
	PostService PostService;
	

	private final String out = "success" ;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("add")
	public Response addPost(@QueryParam("idUser") int idUser,
			@QueryParam("content") String content
	) {
		System.out.print("id user = " + idUser +" content = " + content);
		Timestamp date = new Timestamp(System.currentTimeMillis());
		Posts typePost = Posts.Text;
		PostService.addPost(content,date ,idUser,typePost);
		return Response.status(Response.Status.CREATED).entity(out).build();
		
	}
	

	@DELETE
	@Path("delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removePost(@QueryParam("id") int id) {
		PostService.deletePost(id);
		return Response.status(Status.OK).entity("the post has been deleted").build();
	}
	

	@PUT
	@Path("update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePost(@QueryParam("idUser") int idUser,
			@QueryParam("content") String content
	) {
		Posts typePost = Posts.Text;
		Timestamp date = new Timestamp(System.currentTimeMillis());		
		PostService.updatePost(content,date ,idUser,typePost);
		return Response.status(Status.OK).entity("post updated").build();
	}
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response displayQusetion(@PathParam("id")int id
			) {
		Post p = PostService.findPost(id);
		return Response.status(Status.OK).entity(p).build();
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("all")
	public Response findAllPosts() {

		List<Post> Posts = PostService.findAllPosts();

		if (Posts == null)
			return Response.status(Status.NOT_FOUND).entity("No Posts Found").build();
		else
			return Response.ok(Posts).build();

	}
}
