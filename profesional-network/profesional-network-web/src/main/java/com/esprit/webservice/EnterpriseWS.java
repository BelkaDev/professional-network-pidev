package com.esprit.webservice;


import java.security.Key;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;
import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.esprit.beans.Enterprise;

import com.esprit.services.EnterpriseService;




@Path("enterprise")
public class EnterpriseWS {

	@EJB
	EnterpriseService enterprisews;
	private final String status = "{\"status\":\"ok\"}" ;
	
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("addenterprise")
	public Response addenterprise(@QueryParam("Ename") String Ename,
			@QueryParam("Edomain") String Edomain,
			@QueryParam("Elocation") String Elocation,
			@QueryParam("Employeesnumber") int Employeesnumber, 
			@QueryParam("Edescription") String Edescription

	) {
		Enterprise e =new Enterprise(Ename, Edomain, Elocation, Employeesnumber,Edescription);
		enterprisews.AddEnterprise(e);
		return Response.status(200).entity(status).build();
	}
	
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("updateenterprise")
	public Response updateenterprise(@QueryParam("Eid") int id,@QueryParam("Ename") String Ename,
			@QueryParam("Edomain") String Edomain,
			@QueryParam("Elocation") String Elocation,
			@QueryParam("Employeesnumber") int Employeesnumber,
			@QueryParam("Edescription") String Edescription
	) {
		
		enterprisews.ModifyEnterprise(id, Ename, Edomain, Elocation, Employeesnumber,Edescription);
		return Response.status(200).entity(status).build();
	}
	
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("deleteenterprise")
	public Response deleteenterprise(@QueryParam("Eid") int id) {
		enterprisews.DeleteEnterprise(id);
		return Response.status(200).entity(status).build();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
