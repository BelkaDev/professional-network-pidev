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

import com.esprit.beans.Interview;
import com.esprit.beans.Quiz;
import com.esprit.services.InterviewService;

@Path("interviews")
public class InterviewWS {
	@EJB
	InterviewService interviewService=new InterviewService();
	
	public static Interview i=new Interview();
	
	@PUT
	@Path("setDate")
	@Produces(MediaType.APPLICATION_JSON)
	public Response setDate(@QueryParam("id")int interview_id,@QueryParam("date")String date,@QueryParam("idC")int candidate_id,@QueryParam("idJO")int jobOffer_id) {
		System.out.println(date);
		if(interviewService.setDate(interview_id, date,candidate_id,jobOffer_id))
			return Response.status(Status.OK).entity(i).build();
		return Response.status(Status.BAD_REQUEST).entity(i).build();
	}
	@DELETE
	@Path("deleteInterview")
	@Produces(MediaType.APPLICATION_JSON)
	public Response cancelInterview(@QueryParam("id")int interview_id) {
		interviewService.CancelInterview(interview_id);
		return Response.status(Status.FOUND).entity(i).build();
	}
	
	@PUT
	@Path("setTime")
	@Produces(MediaType.APPLICATION_JSON)
	public Response setTime(@QueryParam("id")int interview_id,@QueryParam("time")String time) {
		if(interviewService.setTime(interview_id, time))
			return Response.status(Status.OK).entity(i).build();
		return Response.status(Status.BAD_REQUEST).entity(i).build();
	}
	@PUT
	@Path("setScore")
	@Produces(MediaType.APPLICATION_JSON)
	public Response setInterviewScore(@QueryParam("idI")int interview_id,@QueryParam("score")double score) {
		interviewService.setScore(interview_id, score);
		return Response.status(Status.OK).entity(i).build();
	}
	@PUT
	@Path("chooseWinner")
	@Produces(MediaType.APPLICATION_JSON)
	public Response selectCandidate(@QueryParam("idJo")int joboffer_id) {
		Quiz in=interviewService.acceptCandidate(joboffer_id);
		
		return Response.status(Status.OK).entity(in).build();
	}

}
