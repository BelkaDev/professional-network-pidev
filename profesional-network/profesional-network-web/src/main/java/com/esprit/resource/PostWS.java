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

import com.esprit.beans.Post;
import com.esprit.enums.POST_TYPE;
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
			@QueryParam("content") String content,
			@QueryParam("type") POST_TYPE typePost
	) {
		PostService.addPost(idUser,content,typePost);
		return Response.status(Response.Status.CREATED).entity(out).build();
		
	}
	

	@DELETE
	@Path("delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removePost(@QueryParam("id") int id) {
		
		if(PostService.deletePost(id))
		{
		return Response.status(Status.OK).entity("the Post has been deleted").build();
		}
		return Response.status(Status.NOT_FOUND).entity("Post doesn't exist").build();
		
	}
	

	@PUT
	@Path("update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePost(@QueryParam("idPost") int idPost,
			@QueryParam("content") String content,
			@QueryParam("type") POST_TYPE typePost
	) {
		if (!PostService.checkPostType(typePost))
		{
		return Response.status(Status.NOT_ACCEPTABLE).entity("invalid type").build();
		}
		if(PostService.updatePost(idPost,content,typePost))
		{
		return Response.status(Status.OK).entity("post updated").build();
		}
		return Response.status(Status.NOT_FOUND).entity("Post doesn't exist").build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response displayQusetion(@PathParam("id")int id
			) {
		Post p = PostService.findPost(id);
		if (p!= null)
		{
		return Response.status(Status.OK).entity(p).build();
		}
		return Response.status(Status.NOT_FOUND).entity("Post not found").build();
	}
	

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("share")
	public Response sharePost(@QueryParam("idPost") int idPost,
			@QueryParam("idUser") int idUser) {
		if (!PostService.sharePost(idPost,idUser))
		{
		return Response.status(Status.NOT_FOUND).entity("post doesn't exist").build();
		}
		return Response.status(Response.Status.CREATED).entity(out).build();
		
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
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("user")
	public Response findUserPosts(@QueryParam("id") int idUser) {
		List<Post> Posts = PostService.findAllUserPosts(idUser);

		if (Posts == null)
			return Response.status(Status.NOT_FOUND).entity("No Posts Found").build();
		else
			return Response.ok(Posts).build();

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("shared")
	public Response sharingPost(@QueryParam("idPost") int idPost) {
		List<Post> Posts = PostService.sharingPost(idPost);
		if (Posts == null)
			return Response.status(Status.NOT_FOUND).entity("No Posts Found").build();
		else
			return Response.ok(Posts).build();

	}
	
}
