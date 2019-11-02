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

import com.esprit.beans.Blacklist;
import com.esprit.beans.User;
import com.esprit.services.BlockingService;
import com.esprit.services.FollowingService;

@Path("block")
public class BlockWS {
	@EJB
	BlockingService blockingService;
	
	private final String out = "success" ;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("user")
	public Response followUser(@QueryParam("blocking") int idBlocking,
			@QueryParam("blocked") int idBlocked
	) {	
		blockingService.blockUser(idBlocking, idBlocked);
		return Response.status(Response.Status.CREATED).entity(out).build();
	}
	
	

}
