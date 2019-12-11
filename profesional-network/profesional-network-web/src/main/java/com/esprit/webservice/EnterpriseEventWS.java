package com.esprit.webservice;

import java.sql.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.TypedQuery;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.esprit.beans.Enterprise;
import com.esprit.beans.EnterpriseEvent;
import com.esprit.beans.FileUpload;
import com.esprit.beans.JobOffer;
import com.esprit.services.EnterpriseEventService;

@Path("enterpriseevent")
public class EnterpriseEventWS {

	@EJB
	EnterpriseEventService enterpriseeventws;
	private final String status = "{\"status\":\"ok\"}" ;
	
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("addevent")
	public Response addevent(@QueryParam("EEtitle") String EEtitle,
			@QueryParam("EEplace") String EEplace,
			@QueryParam("EESdate") Date EESdate,
			@QueryParam("EEEdate") Date EEEdate, 
			@QueryParam("EEdescription") String EEdescription,
			@QueryParam("EEminparticipants") int EEminparticipants,
			@QueryParam("EEmaxparticipants") int EEmaxparticipants,
			@QueryParam("EEprice") float EEprice,
			@QueryParam("user") int user,
			@QueryParam("file") String filename
			
			

	) {
		FileUpload fileS = new FileUpload();
	    fileS.setPath(filename);
	    
		EnterpriseEvent ee= new EnterpriseEvent(EEtitle,EEplace,EESdate,EEEdate,EEdescription,EEminparticipants,EEmaxparticipants,EEprice);
		enterpriseeventws.AddEnterpriseEvent(ee,user,filename);
		return Response.status(Status.CREATED).entity(status).build();
	}
	
	
	
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("updateevent")
	public Response updateevent(@QueryParam("EEid") int id,
			@QueryParam("EEtitle") String EEtitle,
			@QueryParam("EEplace") String EEplace,
			@QueryParam("EESdate") Date EESdate,
			@QueryParam("EEEdate") Date EEEdate,
			@QueryParam("EEdescription") String EEdescription,
			@QueryParam("EEminparticipants") int EEminparticipants,
			@QueryParam("EEmaxparticipants") int EEmaxparticipants,
			@QueryParam("EEprice") float EEprice
	) {
		
		enterpriseeventws.ModifyEnterpriseEvent(id, EEtitle, EEplace, EESdate, EEEdate, EEdescription,EEminparticipants,EEmaxparticipants,EEprice);
		return Response.status(200).entity(status).build();
	}
	
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("deleteevent")
	public Response deleteevent(@QueryParam("EEid") int id) {
		enterpriseeventws.DeleteEnterpriseEvent(id);;
		return Response.status(Status.OK).entity(status).build();
	}
	
	
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getevent")
	public Response getevent() {
		//return enterpriseeventws.getAllEnterpriseEvent();
		return Response.status(Status.OK).entity(enterpriseeventws.getAllEnterpriseEvent()).build();
	}
	
	
	
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	@Path("geteventbyid")
	public EnterpriseEvent geteventbyid(@QueryParam("EEid") int EEid) {
		return enterpriseeventws.geteventById(EEid);
	}
	
	
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	@Path("geteventbyent")
	public Response getjoobofferbyent(@QueryParam("entid") int entid) {
		return Response.status(Status.OK).entity(enterpriseeventws.getEventByEnt(entid)).build();
	}
	
	
	
	
	
}
