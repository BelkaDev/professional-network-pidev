package com.esprit.resource;



import javax.ejb.EJB;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.esprit.services.FollowingService;
import com.esprit.utils.UserSession;

@Path("unfollow")
public class UnFollowWS {
	@EJB
	FollowingService followService;
	
	private final String out = "success" ;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("user")
	public Response followUser(@QueryParam("followed") int idFollowed
	) {	
		int idFollower = UserSession.getInstance().getId();
		followService.unFollowUser(idFollower, idFollowed);
		return Response.status(Response.Status.CREATED).entity(out).build();
	}
	

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("post")
	public Response followPost(@QueryParam("post") int idPost
	) {	
		int idFollower = UserSession.getInstance().getId();
		followService.unFollowPost(idFollower, idPost);
		return Response.status(Response.Status.CREATED).entity(out).build();
	}
	
	

}
