package com.esprit.webservice;

import java.security.Key;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.crypto.spec.SecretKeySpec;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import com.esprit.beans.EnterpriseUser;
import com.esprit.enums.Role;
import com.esprit.services.EnterpriseUserService;

//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;

@Path("enterpriseuser")
public class EnterpriseUserWS {
	@EJB
	EnterpriseUserService enterpriseuserws;
	private final String status = "{\"status\":\"ok\"}" ;
	
/*	
	
	@Context
	UriInfo uriInfo;
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("addenterpriseuser")
	public Response addenterpriseadmin(@QueryParam("username") String username,
			@QueryParam("password") String password,
			@QueryParam("email") String email,
			@QueryParam("role") Role role,
			@QueryParam("enterpriseId") int enterpriseId

	) {
		EnterpriseUser eu =new EnterpriseUser(username, password, email, role);
		enterpriseuserws.AddEnterpriseUser(eu,enterpriseId);

		return Response.status(200).entity(status).build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("logout")
	public Response logoutUser(@QueryParam("EuserId") int EuserId){
		enterpriseuserws.Logout(EuserId);
		return Response.status(200).entity(status).build();
	}
	
	
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("authenticate")
	public Response authenticateUser(@QueryParam("username") String username, @QueryParam("password") String password) {
		

			Boolean test = enterpriseuserws.authenticate(username, password);
				if (test)
				{
					String token = issueToken(username);
					enterpriseuserws.updateToken(username,token);
					System.out.println("****************** " + token);
					 return Response.status(Status.CREATED).entity("CONNECTED").build();
					
				}
				else {
					return Response.status(Status.NOT_FOUND).entity("NOT FOUND").build();

				}
			

		
	}
	private String issueToken(String username) {
		// Issue a token (can be a random String persisted to a database or a JWT token)
		// The issued token must be associated to a user
		// Return the issued token

		String keyString = "simplekey";
		Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
		System.out.println("the key is : " + key.hashCode());

		System.out.println("uriInfo.getAbsolutePath().toString() : " + uriInfo.getAbsolutePath().toString());
		//System.out.println("Expiration date: " + toDate(LocalDateTime.now().plusMinutes(15L)));

		String jwtToken = Jwts.builder().setSubject(username).setIssuer(uriInfo.getAbsolutePath().toString())
				//.setIssuedAt(new Date()).setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
				.signWith(SignatureAlgorithm.HS512, key).compact();

		System.out.println("the returned token is : " + jwtToken);
		return jwtToken;
	}
	private Date toDate(LocalDateTime localDateTime) {
		return (Date) Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	
	
	
*/	
	
	
}
