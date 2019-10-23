package com.esprit.webservice;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.esprit.beans.HumanResources;
import com.esprit.beans.ProjectLeader;
import com.esprit.services.HumanResourcesService;
import com.esprit.services.ProjectLeaderService;

@Path("projectleader")
public class ProjectLeaderWS {


	@EJB
	ProjectLeaderService projectleaderws;
	private final String status = "{\"status\":\"ok\"}" ;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("addprojectleader")
	public Response addProjectLeader(@QueryParam("PLname") String PLname,
			@QueryParam("PLpassword") String PLpassword,
			@QueryParam("PLemail") String PLemail

	) {
		ProjectLeader pl =new ProjectLeader(PLname, PLpassword, PLemail);
		projectleaderws.AddProjectLeader(pl);
 
		return Response.status(200).entity(status).build();
	}
	
}
