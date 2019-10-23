package com.esprit.resource;



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

import com.esprit.beans.Claim;
import com.esprit.beans.Etat;

import com.esprit.services.ClaimService;








@Path("claim")
public class ClaimServiceWb {
	@EJB
	ClaimService cs;
	
	
	 @POST
	    @Path("addClaim")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response addClaim(
	            @QueryParam("id")int id,
	            @QueryParam("description")String description,
	            @QueryParam("etat")Etat etat,
	            @QueryParam("type")String type,
	            @QueryParam("whoClaim")int whoClaim,
	            @QueryParam("claimsOn")int claimsOn
	       
	    
	    ){
		 
		 cs.addClaim(id, description, etat, type, whoClaim, claimsOn);
		 return Response.status(Status.CREATED).entity("Add succesful").build();
	    }
	 
	 
	 
	 @GET
		@Produces(MediaType.APPLICATION_JSON)
		@Path("allClaims")
	    public List<Claim> getClaims()
	    {
	        return cs.afficherClaim();
	    }
	 @DELETE
	 @Path("deleteClaim")
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response DeletePromotion(
			 @QueryParam("id")int id
			 ) {
		 
		 cs.removeClaim(id);
	     return Response.status(200).entity("Deleted claim with "+id).build();
	 }
	 
	 
	 @PUT
     @Path("treateClaim/{id}")
     @Produces(MediaType.APPLICATION_JSON)
	 public Response updatepromotion(
			 @PathParam(value="id")int id ,
			 @QueryParam("etat")Etat etat
		
			 )
	 {
		Claim p = cs.findClaimById(id);
	p.setEtat(etat);
		 cs.treatClaim(id,etat);
	
		 return Response.status(200).entity(cs.findClaimById(id)).build();
	 }
	 
	 @GET
		@Produces(MediaType.APPLICATION_JSON)
		@Path("treatedClaims")
	    public List<Claim> getClaimsTreated()
	    {
	        return cs.afficherClaimTreated();
	    }
	 @GET
		@Produces(MediaType.APPLICATION_JSON)
		@Path("untreatedClaims")
	    public List<Claim> getClaimsUntreated()
	    {
	        return cs.afficherClaimUntreated();
	    }
	 @GET
		@Produces(MediaType.APPLICATION_JSON)
		@Path("inProgressClaims")
	    public List<Claim> getClaimsInProgress()
	    {
	        return cs.afficherClaimInProgress();
	    }
		
		 
	
	

}
