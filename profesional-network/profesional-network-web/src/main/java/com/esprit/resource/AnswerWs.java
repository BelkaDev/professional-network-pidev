package com.esprit.resource;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
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

import com.esprit.beans.Answer;
import com.esprit.services.AnswerService;

@Path("answers")
public class AnswerWs {
	@EJB
	AnswerService answerService = new AnswerService();

	@POST
	@Path("addAnswer")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAnswer(@QueryParam("answer") String answer) {
		answerService.addAnswer(answer);
		Answer a = new Answer();
		return Response.status(Status.OK).entity(a).build();
	}

	@PUT
	@Path("setCorrect")
	@Produces(MediaType.APPLICATION_JSON)
	public Response setCorrect(@QueryParam("id") int answer_id) {
		answerService.SetCorrectAnswer(answer_id);
		Answer a = new Answer();
		return Response.status(Status.OK).entity(a).build();
	}

	@DELETE
	@Path("deleteAnswer")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeAnswer(@QueryParam("id") int answer_id) {
		answerService.deleteAnswer(answer_id);
		Answer a = new Answer();
		return Response.status(Status.OK).entity(a).build();
	}

	@PUT
	@Path("updateAnswer")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAnswer(@QueryParam("id") int answer_id, @QueryParam("answer") String answer) {
		answerService.updateAnswer(answer_id, answer);
		Answer a = new Answer();
		return Response.status(Status.OK).entity(a).build();
	}
	@GET
	@Path("getAnswer")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAnswer(@QueryParam("id")int answer_id) {
		Answer a=answerService.displayAnswer(answer_id);
		return Response.status(Status.OK).entity(a).build();
	}
	@GET
	@Path("getAnswers")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllAnswers() {
		return Response.status(Status.OK).entity(answerService.getAnswers()).build();
	}

}
