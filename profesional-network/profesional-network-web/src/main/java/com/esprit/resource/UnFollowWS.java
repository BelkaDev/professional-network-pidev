package com.esprit.resource;



import javax.ejb.EJB;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.esprit.services.FollowingService;

@Path("unfollow")
public class UnFollowWS {
	@EJB
	FollowingService followService;
	
	private final String out = "success" ;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("user")
	public Response followUser(@QueryParam("follower") int idFollower,
			@QueryParam("followed") int idFollowed
	) {	
		followService.unFollowUser(idFollower, idFollowed);
		return Response.status(Response.Status.CREATED).entity(out).build();
	}
	


	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("post")
	public Response followPost(@QueryParam("follower") int idFollower,
			@QueryParam("post") int idPost
	) {	
		followService.unFollowPost(idFollower, idPost);
		return Response.status(Response.Status.CREATED).entity(out).build();
	}
	
	

}
