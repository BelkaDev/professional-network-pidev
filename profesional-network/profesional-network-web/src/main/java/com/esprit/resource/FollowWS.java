package com.esprit.resource;



import java.util.List;
import javax.ejb.EJB;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.esprit.beans.User;
import com.esprit.services.FollowingService;

@Path("follow")
public class FollowWS {
	@EJB
	FollowingService followService;
	
	private final String out = "success" ;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("user")
	public Response followUser(@QueryParam("follower") int idFollower,
			@QueryParam("followed") int idFollowed
	) {	
		followService.followUser(idFollower, idFollowed);
		return Response.status(Response.Status.CREATED).entity(out).build();
	}
	


	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("post")
	public Response followPost(@QueryParam("follower") int idFollower,
			@QueryParam("post") int idPost
	) {	
		followService.followPost(idFollower, idPost);
		return Response.status(Response.Status.CREATED).entity(out).build();
	}
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("postFollowers")
	public Response findPostFollowers(@QueryParam("id") int idPost) {
		List<User> followers = followService.PostFollowers(idPost);

		if (followers == null)
			return Response.status(Status.NOT_FOUND).entity("The post has no followers").build();
		else
			return Response.ok(followers).build();

	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("userFollowers")
	public Response findUserFollowers(@QueryParam("id") int idFollowed) {
		List<User> followers = followService.UserFollowers(idFollowed);

		if (followers == null)
			return Response.status(Status.NOT_FOUND).entity("The user has no followers").build();
		else
			return Response.ok(followers).build();

	}
	

}
