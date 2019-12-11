package com.esprit.resource;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.esprit.beans.Pack;
import com.esprit.beans.User;
import com.esprit.beans.UserPack;
import com.esprit.enums.PackType;
import com.esprit.services.PackService;
import com.esprit.utils.UserSession;

@Path("pack")
public class PackServiceWs {
	@EJB
	PackService ps;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("allPacks")
	public List<Pack> getPacks() {
		List<Pack> list=ps.allpacks();
		
		
		return list;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("user/{idPack}")
	public List<String> getUser(@PathParam(value = "idPack") int idPack) {
		List<String> l = new ArrayList<String>();

		Pack p = ps.findPackById(idPack);

		String a = "Users ayant le pack " + p.getTitle();
		l.add(a);
		for (User u : ps.getUsers()) {
			if (u.getPacks().contains(p)) {

				a = "User : " + u.getFirstName() + "," + u.getLastName() + ", bought :" + p.getTitle() + ", type:"
						+ p.getType() + ",from " + p.getStartDate() + " au " + p.getEndDate();
				l.add(a);
			}
		}

		return l;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("user")
	public List<String> getUsers() {
		List<String> l = new ArrayList<String>();

		String a;
		List<Pack> lp = ps.allpacks();
		for (Pack p : lp) {
			a = "Users ayant le pack :" + p.getTitle();
			l.add(a);
			for (User u : ps.getUsers()) {
				if (u.getPacks().contains(p)) {
					
					a = "User : " + u.getFirstName() + "," + u.getLastName() + ", bought :" + p.getTitle() + ", type:"
							+ p.getType() + ",from " + p.getStartDate() + " au " + p.getEndDate();
					l.add(a);
				}
			}
		}

		return l;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("pack/{id}")
	public Pack getPack(@PathParam(value = "id") int id) {
		return ps.getPack(id);
	}

	@POST
	@Path("addPack")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPack(@QueryParam("titre") String titre,
			@QueryParam("description") String description,
			@QueryParam("prix") double prix,
			@QueryParam("type") PackType type

	) {
		Pack p=new Pack(titre, description, prix, type);
		ps.addPack(titre, description, prix, type);
		return Response.status(Status.CREATED).entity(p).build();
	}

	@PUT
	@Path("addReduction/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatepromotion(@PathParam(value = "id") int id,
			@QueryParam("reduction") double reduction,
			@QueryParam("dateDebut") Date dateDebut, 
			@QueryParam("dateFin") Date dateFin

	) {
		Date d=Date.valueOf(LocalDate.now());
		ps.addReduction(id, reduction, d,d);

		return Response.status(200).entity(ps.findPackById(id)).build();
	}

	@DELETE
	@Path("deletePack")
	@Produces(MediaType.APPLICATION_JSON)
	public Response DeletePack(@QueryParam("id") int id) {

		ps.deletePack(id);
		return Response.status(200).entity("Deleted Pack with " + id).build();
	}

	@PUT
	@Path("payPack/{idP}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response payPack( @PathParam(value = "idP") int idP) {
		ps.addPackToPayIt( idP);
		return Response.status(200).entity(ps.findPackById(idP)).build();
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("userByPack/{id}")
	public List<UserPack> getUserByPack(@PathParam(value = "id") int id) {
		return ps.getUsersByPack(id);
		
	}
	


}
