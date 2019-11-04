package com.esprit.resource.candidate;

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

import com.esprit.service.candidate.ContactService;

@Path("contact")
public class ContactWs {

	@EJB
	ContactService cs;
	
	@POST
	@Path("requestConnection")
	@Produces(MediaType.APPLICATION_JSON)
	public Response requestConnection(@QueryParam("senderId") int senderId,
			@QueryParam("receiverId") int receiverId) {
		cs.requestConnection(senderId, receiverId);
		return Response.status(Status.CREATED).entity("Request Sent").build();
	}
	
	@GET
	@Path("getRequests")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRequests(@QueryParam("receiverId") int receiverId) {
		return Response.status(Status.FOUND).entity(cs.getRequests(receiverId)).build();
	}
	
	@GET
	@Path("getFriendsList")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFriendsList(@QueryParam("candidateId") int candidateId) {
		return Response.status(Status.FOUND).entity(cs.getFriendsList(candidateId)).build();
	}
	
	
	@PUT
	@Path("acceptRequest")
	@Produces(MediaType.APPLICATION_JSON)
	public Response acceptRequest(@QueryParam("requestId") int requestId) {
		cs.acceptRequest(requestId);
		return Response.status(Status.OK).entity("Request Accepted").build();
	}
	
	@DELETE
	@Path("deleteRequest")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteRequest(@QueryParam("requestId") int requestId) {
		cs.cancelRequest(requestId);
		return Response.status(Status.OK).entity("the request has been canceled").build();
	}
	@PUT
	@Path("blockContact")
	@Produces(MediaType.APPLICATION_JSON)
	public Response blockContact(@QueryParam("blocker") int blocker,@QueryParam("toBlock") int toBlock) {
		cs.blockCandidate(blocker, toBlock);
		return Response.status(Status.OK).entity("Blocked Contact").build();
	}
	
	@GET
	@Path("searchCandidate")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchCandidate(@QueryParam("criteria") String criteria) {
		return Response.status(Status.FOUND).entity(cs.searchForCandidates(criteria)).build();
	}
	
	@GET
	@Path("searchEnterprise")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchEnterprise(@QueryParam("criteria") String criteria) {
		return Response.status(Status.FOUND).entity(cs.searchForEnterprise(criteria)).build();
	}
	
	@GET
	@Path("getOffersByEnterprise")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOffers(@QueryParam("enterpriseId") int enterpriseId) {
		return Response.status(Status.FOUND).entity(cs.getOffersByEnterprise(enterpriseId)).build();
	}
	
	@GET
	@Path("getContactsInEnterprise")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getContactsInEnterprise(@QueryParam("candidateId") int candidateId,@QueryParam("enterpriseId") int enterpriseId) {
		return Response.status(Status.FOUND).entity(cs.getContactsInEnterprise(candidateId, enterpriseId)).build();
	}
	
	@POST
	@Path("subscribe")
	@Produces(MediaType.APPLICATION_JSON)
	public Response subscribeToEnterprise(@QueryParam("candidateId") int candidateId,
			@QueryParam("enterpriseId") int enterpriseId) {
		cs.subscribeToEnterprise(candidateId, enterpriseId);
		return Response.status(Status.CREATED).entity("Subscribed").build();
	}
	
	@GET
	@Path("getSubscriptions")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSubscriptions(@QueryParam("candidateId") int candidateId) {
		return Response.status(Status.FOUND).entity(cs.getSubscriptions(candidateId)).build();
	}
	
	@DELETE
	@Path("cancelSubscription")
	@Produces(MediaType.APPLICATION_JSON)
	public Response cancelSubscription(@QueryParam("subscriptionId") int subscriptionId) {
		cs.cancelSubscription(subscriptionId);
		return Response.status(Status.OK).entity("the Subscription has been canceled").build();
	}
	
	
	
	
	
}
