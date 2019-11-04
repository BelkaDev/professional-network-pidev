package com.esprit.resource;



import javax.ejb.EJB;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.esprit.services.BlockingService;
import com.esprit.utils.UserSession;

@Path("unblock")
public class UnBlockWS {
	@EJB
	BlockingService blockingService;
	
	private final String out = "success" ;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("user")
	public Response followUser(@QueryParam("blocked") int idBlocked
	) {	
		int idBlocking = UserSession.getInstance().getId();
		blockingService.unblockUser(idBlocking, idBlocked);
		return Response.status(Response.Status.CREATED).entity(out).build();
	}
	
	

}
