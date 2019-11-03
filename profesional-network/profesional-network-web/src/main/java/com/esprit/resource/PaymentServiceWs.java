package com.esprit.resource;

import java.sql.Date;
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

import com.esprit.beans.Payement;

import com.esprit.services.PaymentService;
@Path("payment")
public class PaymentServiceWs {
	@EJB
	PaymentService ps;
	
	
	 @PUT
	    @Path("add")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response addClaim(         
	            @QueryParam("numCard")String numCard,
	            @QueryParam("cvv")int cvv,
	            @QueryParam("expirationDate")Date expirationDate,
	            @QueryParam("idPack")int idPack
	    ){
		 
	 if(ps.payPack(idPack, numCard, cvv, expirationDate))
		 {
		 System.out.println("*****************"+numCard);

		 ps.payPack(idPack, numCard, cvv, expirationDate);
		 return Response.status(Status.CREATED).entity("Add succesful").build();
		 
		 }
		 return Response.status(Status.NOT_ACCEPTABLE).entity("VERIFY YOUR PARAMS").build();
		
	    }
	 
	 
	 
	 @PUT
		@Produces(MediaType.APPLICATION_JSON)
		@Path("cancelPayment/{idPayment}")
	    public Response CancelPayment(
	    		@PathParam(value="idPyment")int idPayment
	    		)
	    {
		 ps.cancelPayment(idPayment);
		 
		 return Response.status(Status.OK).entity("REQUEST SENDED TO ADMIN").build();
		 
	    }
	 @GET
	 @Path("getUPayments")
	 @Produces(MediaType.APPLICATION_JSON)
	 public List<Payement> getUPayment() {
		 
		return ps.consultYourPaymentDetails();
	     
	 }
	 @GET
	 @Path("getPaymentsAdmin")
	 @Produces(MediaType.APPLICATION_JSON)
	 public List<Payement> getPaymentAdmin() {
		 
		return ps.ConsultPayments();
	     
	 }
	 
	 
	 @PUT
     @Path("cancelPaymentAdmin{id}")
     @Produces(MediaType.APPLICATION_JSON)
	 public Response cancelPaymentAdmin(
			 @PathParam(value="id")int id 
			 
		
			 )
	 {
		ps.ValidateCanceledPayment(id);
		 return Response.status(200).entity("your payment was canceled succesuly").build();
	 }
	 @PUT
     @Path("validatePaymentAdmin{id}")
     @Produces(MediaType.APPLICATION_JSON)
	 public Response validatePaymentAdmin(
			 @PathParam(value="id")int id 
			 
		
			 )
	 {
		ps.validatePayment(id);
		 return Response.status(200).entity("your payment was validated succesuly").build();
	 }
	 @DELETE
	 @Path("deletePayment/{id}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response DeletePayment(
			 @QueryParam("id")int id
			 ) {
		 
		 if(ps.removePayment(id)) {
			 return Response.status(200).entity("Deleted payment with "+id).build();
		 }
	     return Response.status(200).entity("payment not found ").build();
	 }
}
