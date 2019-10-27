package com.esprit.resource;


import java.util.List;

import javax.ejb.EJB;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
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

import com.esprit.services.UserService;
import com.esprit.beans.User;


@Path("user")
public class UserWS {
	
	@EJB
	UserService UserService = new UserService();
		
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("show")
	public Response showUser(@QueryParam("id")int id) {	
		User user = UserService.findUser(id);
		if (user == null)
		return Response.status(Response.Status.NOT_FOUND).entity("User doesn't exist").build();
		else
		return Response.status(Status.OK).entity(user).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("all")
	public Response showAllUsers() {

		List<User> Users = UserService.findAllUsers();

		if (Users == null)
			return Response.status(Status.NOT_FOUND).entity("No Users Found").build();
		else
			return Response.ok(Users).build();

	}

	
}

