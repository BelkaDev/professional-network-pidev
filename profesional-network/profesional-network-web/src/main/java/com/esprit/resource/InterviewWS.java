package com.esprit.resource;

import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.esprit.services.InterviewService;

@Path("interviews")
public class InterviewWS {
	@EJB
	InterviewService interviewService=new InterviewService();
	
	@PUT
	@Path("setDate")
	@Produces(MediaType.APPLICATION_JSON)
	public Response setDate(@QueryParam("id")int interview_id,@QueryParam("date")String date) {
		System.out.println(date);
		if(interviewService.setDate(interview_id, date))
			return Response.status(Status.OK).entity("the date has been set").build();
		return Response.status(Status.OK).entity("invalid date").build();
	}
	@DELETE
	@Path("deleteInterview")
	@Produces(MediaType.APPLICATION_JSON)
	public Response cancelInterview(@QueryParam("id")int interview_id) {
		interviewService.CancelInterview(interview_id);
		return Response.status(Status.FOUND).entity("the interview has beeen canceled").build();
	}
	
	@PUT
	@Path("setTime")
	@Produces(MediaType.APPLICATION_JSON)
	public Response setTime(@QueryParam("id")int interview_id,@QueryParam("time")String time) {
		if(interviewService.setTime(interview_id, time))
			return Response.status(Status.OK).entity("the time has been set").build();
		return Response.status(Status.BAD_REQUEST).entity("invalid time has to be between 9 and 18").build();
	}
	
	

}
