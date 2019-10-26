package com.esprit.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import javax.ws.rs.core.Response.Status;

import com.esprit.beans.Answer;
import com.esprit.beans.Question;
import com.esprit.beans.User;
import com.esprit.services.UserService;

@Path("user")
public class UserWS {
	
	@EJB
	UserService UserService = new UserService();
	
	@GET
	@Path("show")
	@Produces(MediaType.APPLICATION_JSON)
	public Response displayUser(@QueryParam("id")int id) {
		System.out.println("the fucking id : "+ id);
		return Response.status(Status.OK).entity(UserService.findUser(id).getPack().getListUser()).build();
	}

}

