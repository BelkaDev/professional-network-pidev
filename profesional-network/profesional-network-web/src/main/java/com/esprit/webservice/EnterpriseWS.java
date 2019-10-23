package com.esprit.webservice;


import java.sql.Date;
import java.util.List;

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
import com.esprit.beans.EnterpriseEvent;
import com.esprit.beans.JobOffer;
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
	
	
	
	
	
	//**************************************eventws**********************************************
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("addevent")
	public Response addevent(@QueryParam("EEtitle") String EEtitle,
			@QueryParam("EEplace") String EEplace,
			@QueryParam("EESdate") Date EESdate,
			@QueryParam("EEEdate") Date EEEdate, 
			@QueryParam("EEdescription") String EEdescription,
			@QueryParam("enterpriseId") int enterpriseId

	) {
		
		EnterpriseEvent ee= new EnterpriseEvent(EEtitle,EEplace,EESdate,EEEdate,EEdescription);
		enterprisews.AddEnterpriseEvent(ee,enterpriseId);
		return Response.status(200).entity(status).build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("updateevent")
	public Response updateevent(@QueryParam("EEid") int id,@QueryParam("EEtitle") String EEtitle,
			@QueryParam("EEplace") String EEplace,
			@QueryParam("EESdate") Date EESdate,
			@QueryParam("EEEdate") Date EEEdate,
			@QueryParam("EEdescription") String EEdescription
	) {
		
		enterprisews.ModifyEnterpriseEvent(id, EEtitle, EEplace, EESdate, EEEdate, EEdescription);
		return Response.status(200).entity(status).build();
	}
	
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("deleteevent")
	public Response deleteevent(@QueryParam("EEid") int id) {
		enterprisews.DeleteEnterpriseEvent(id);;
		return Response.status(200).entity(status).build();
	}
	
	
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getevent")
	public List<EnterpriseEvent> getevent() {
		return enterprisews.getAllEnterpriseEvent();
	}
	
	
	
	
	
	
	
}
