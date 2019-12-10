package com.esprit.resource;


import java.security.Key;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;


import javax.crypto.spec.SecretKeySpec;
import javax.ejb.EJB;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.esprit.services.UserService;
import com.esprit.utils.UserSession;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import com.esprit.beans.Address;
import com.esprit.beans.User;
import com.esprit.enums.Gender;
import com.esprit.enums.Role;


@Path("user")
public class UserWS {


	@EJB
	UserService userservice = new UserService() ;

	@Context
	UriInfo uriInfo;
	@POST
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)

    public Response addUser(

    		@QueryParam("username")String username,
    		@QueryParam("email")String email,
    		@QueryParam("password")String password,
    		@QueryParam("firstName")String firstName,
    		@QueryParam("lastName")String lastName,
    		@QueryParam("role")Role role,
    		@QueryParam("birthDate")Date birthDate,
    		@QueryParam("gender") Gender gender,
    		@QueryParam("streetAddress")String streetAddress,
    		@QueryParam("city")String city,
    		@QueryParam("postalCode")int postalCode,
    		@QueryParam("interests")String interests


    		)
 	{
		Address d=new Address();
		d.setCity(city);
		d.setPostalCode(postalCode);
		d.setStreetAddress(streetAddress);
		
		User user = new User(email, firstName, lastName, password, gender, birthDate, d, username, role,interests);
		
		if(!userservice.UsernameMailUnique(user.getUsername(), user.getEmail())) {
		


	 	return Response.status(Status.CREATED).entity("ADDED").build();
	 	}
		return Response.status(Status.NOT_ACCEPTABLE).entity("username or email exist").build();
		 

	 	}

	@POST
    @Path("addentuser")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEnterpriseUser(
            @QueryParam("username")String username,
            @QueryParam("email")String email,
            @QueryParam("password")String password,
            @QueryParam("firstName")String firstName,
            @QueryParam("lastName")String lastName,
            @QueryParam("role")Role role,
            @QueryParam("birthDate")Date birthDate,
            @QueryParam("gender") Gender gender,
            @QueryParam("streetAddress")String streetAddress,
            @QueryParam("city")String city,
            @QueryParam("postalCode")int postalCode,
            @QueryParam("enterpriseId")int enterpriseId

            )
    {
        Address d=new Address();
        d.setCity(city);
        d.setPostalCode(postalCode);
        d.setStreetAddress(streetAddress);

        User user = new User(email, firstName, lastName, password, gender, birthDate, d, username, role);
        userservice.addEnterpriseUser(user, enterpriseId);


        return Response.status(Status.CREATED).entity("ADDED").build();

    }

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("authenticate")
	public Response authenticateUser(@QueryParam("username") String username, @QueryParam("password") String password) {

			Boolean test = userservice.authenticate(username, password);
				if (test)
				{
					String token = issueToken(username);
					System.out.print("------------------------ "+ token);
					userservice.updateToken(username,token);
					System.out.println("****************** " + token);
					 return Response.status(Status.OK).entity(UserSession.getInstance()).build();

				}
				else {
					return Response.status(Status.NOT_FOUND).entity("NOT FOUND").build();

				}



	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("profile")
	public User Profile() {

			return userservice.getUserById();



	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("resetPass")
	public Response ResetPass(@QueryParam("username") String username) {

		userservice.ResetingPassword(username);
			 return Response.status(Status.ACCEPTED).entity("Reseting Password").build();

	}
	@PUT
	@Path("confirm")
	@Produces(MediaType.APPLICATION_JSON)

	public Response Confirm(@QueryParam("code") String code, @QueryParam("idUser") int idUser

	) {
		userservice.confirmCode(code, idUser);

		return Response.status(Status.ACCEPTED).entity("ACCEPTED").build();
	}
	@PUT
	@Path("updatePass")
	@Produces(MediaType.APPLICATION_JSON)

	public Response UpdatePassword(@QueryParam("username") String username, @QueryParam("newpass") String newPass

	) {
		userservice.UpdatePassword(username, newPass);

		return Response.status(Status.ACCEPTED).entity("ACCEPTED").build();
	}
	@PUT
	@Path("logout")
	@Produces(MediaType.APPLICATION_JSON)

	public Response Logout(

	) {
		userservice.logout();
		return Response.status(Status.ACCEPTED).entity("ACCEPTED").build();
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

		@PUT
		@Path("enableMails")
		@Produces(MediaType.APPLICATION_JSON)

		public Response EnableMails(

		) {
			userservice.enableMailNotifications();
			return Response.status(Status.ACCEPTED).entity("ACCEPTED").build();
		}
		@PUT
		@Path("disableMails")
		@Produces(MediaType.APPLICATION_JSON)

		public Response DisableMails(

		) {
			
			userservice.enableMailNotifications();
			return Response.status(Status.ACCEPTED).entity("ACCEPTED").build();
		}

}
