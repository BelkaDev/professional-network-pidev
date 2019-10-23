package com.esprit.webservice;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.esprit.beans.EnterpriseAdmin;
import com.esprit.beans.HumanResources;
import com.esprit.services.EnterpriseAdminService;
import com.esprit.services.HumanResourcesService;

@Path("humanresouces")
public class HumanResoucesWS {

	
	@EJB
	HumanResourcesService humanresourcesws;
	private final String status = "{\"status\":\"ok\"}" ;
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("addhumanresouces")
	public Response addHumanResources(@QueryParam("HRname") String HRname,
			@QueryParam("HRpassword") String HRpassword,
			@QueryParam("HRemail") String HRemail

	) {
		HumanResources hr =new HumanResources(HRname, HRpassword, HRemail);
		humanresourcesws.AddHumanResources(hr);

		return Response.status(200).entity(status).build();
	}
	
	
}
