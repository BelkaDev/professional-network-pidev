package com.esprit.resource;




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

import com.esprit.beans.Claim;
import com.esprit.beans.State;
import com.esprit.enums.NOTIFICATION_TYPE;
import com.esprit.services.ClaimService;
import com.esprit.services.NotificationService;








@Path("claim")
public class ClaimServiceWb {
	@EJB
	ClaimService cs;
	@EJB
	NotificationService ns=new NotificationService();
	
	 @POST
	    @Path("addClaim")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response addClaim(
	    		@QueryParam("description")String description,
	            @QueryParam("type")String type,
	            @QueryParam("whoClaim")int whoClaim,
	            @QueryParam("claimsOn")int claimsOn
	    ){
		 Claim c =new Claim();
		 c.setClaimsOn(cs.findUser(claimsOn));
		 c.setWhoClaim(cs.findUser(whoClaim));
		 c.setDescription(description);
		 c.setType(type);
		 cs.addClaim( description, type, whoClaim, claimsOn);
		// ns.CreateNotification(4, "claim", NOTIFICATION_TYPE.Message, 0);
		 return Response.status(Status.CREATED).entity(c).build();
	    }
	 
	 
	 
	 @GET
		@Produces(MediaType.APPLICATION_JSON)
		@Path("allClaims")
	    public List<String> getClaims()
	    {
		 List<String> all=new ArrayList<String>();
		 List<Claim> c=cs.allClaims();
		 for(Claim a : c)
		 {
			 
			 all.add(a.toString());
		 }
		 
		 return all;
		 
		 
		 
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
			 @QueryParam("etat")State etat
		
			 )
	 {
		Claim p = cs.findClaimById(id);
	
		 cs.treatClaim(id,etat);
	
		 return Response.status(200).entity(cs.findClaimById(id)).build();
	 }
	 
	 @GET
		@Produces(MediaType.APPLICATION_JSON)
		@Path("treatedClaims")
	    public List<Claim> getClaimsTreated()
	    {
	        return cs.getClaimTreated();
	    }
	 @GET
		@Produces(MediaType.APPLICATION_JSON)
		@Path("untreatedClaims")
	    public List<Claim> getClaimsUntreated()
	    {
	        return cs.getClaimUntreated();
	    }
	 @GET
		@Produces(MediaType.APPLICATION_JSON)
		@Path("inProgressClaims")
	    public List<Claim> getClaimsInProgress()
	    {
	        return cs.getClaimInProgress();
	    }
		
		 
	
	

}
