package com.esprit.webservice;

import java.sql.Date;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.esprit.beans.EnterpriseAdmin;
import com.esprit.services.EnterpriseAdminService;

@Path("enterpriseadmin")
public class EnterpriseAdminWS {

	@EJB
	EnterpriseAdminService enterpriseadminws;
	private final String status = "{\"status\":\"ok\"}" ;
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("addenterpriseadmin")
	public Response addenterpriseadmin(@QueryParam("EAname") String EAname,
			@QueryParam("EApassword") String EApassword,
			@QueryParam("EAemail") String EAemail

	) {
		EnterpriseAdmin ea =new EnterpriseAdmin(EAname, EApassword, EAemail);
		enterpriseadminws.AddEnterpriseAdmin(ea);

		return Response.status(200).entity(status).build();
	}
	
	
	
	
	
}
