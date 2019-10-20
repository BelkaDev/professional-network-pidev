package com.esprit.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.esprit.beans.Pack;



@Path("/pack")
public class PackService {

	public static List<Pack>listpack=new ArrayList<Pack>();
	
	
	//(A)add pack
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response addPack(Pack p)
	{
		com.esprit.services.PackService packserv=new com.esprit.services.PackService();
		packserv.ajouterPack(p);
		listpack.add(p);
		return Response.status(Status.CREATED).entity("Add succesful").build();
	}
	
	
}
