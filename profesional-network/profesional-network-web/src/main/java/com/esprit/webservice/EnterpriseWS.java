package com.esprit.webservice;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.esprit.beans.Enterprise;
import com.esprit.beans.EnterpriseEvent;
import com.esprit.beans.FileUpload;
import com.esprit.beans.User;
import com.esprit.beans.candidate.Experience;
import com.esprit.services.EnterpriseService;
import com.esprit.utils.UserSession;





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
			@QueryParam("Edescription") String Edescription,
			@QueryParam("userid") int userid,
			@QueryParam("file") String filename
	) {
	    FileUpload fileS = new FileUpload();
	    fileS.setPath(filename);
	    
		Enterprise e =new Enterprise(Ename, Edomain, Elocation, Employeesnumber,Edescription);
		enterprisews.AddEnterprise(e,userid,filename);
		
		return Response.status(Status.CREATED).entity("enterprise added").build();
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
		return Response.status(Status.CREATED).entity(status).build();
	}
	
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("deleteenterprise")
	public Response deleteenterprise(@QueryParam("Eid") int id) {
		enterprisews.DeleteEnterprise(id);
		return Response.status(200).entity(status).build();
	}
	
	
	
	
	
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getallent")
	public Response getallent() {
		//return enterprisews.getAllEnterprise();
		return Response.status(Status.OK).entity(enterprisews.getAllEnterprise()).build();
	}
	
	
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getentbyid")
	public Enterprise getentbyid(@QueryParam("Eid") int Eid) {
		return enterprisews.getenterpriseById(Eid);
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
