package com.esprit.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.esprit.beans.Answer;
import com.esprit.beans.Question;
import com.esprit.services.QuestionService;

@Path("questions")
public class QuestionWs {
	@EJB
	QuestionService questionService = new QuestionService();

	@POST
	@Path("addQuestion")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addQuestion(@QueryParam("question") String question) {
		questionService.addQuestion(question);
		Question q=new Question();
		return Response.status(Status.OK).entity(q).build();
	}

	@PUT
	@Path("updateQuestion")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateQuestion(@QueryParam("id") int question_id, @QueryParam("question") String question) {
		questionService.updateQuestion(question_id, question);
		Question q=new Question();
		return Response.status(Status.OK).entity(q).build();
	}
	@DELETE
	@Path("deleteQuestion")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteQuestion(@QueryParam("id")int question_id) {
		questionService.deleteQuestion(question_id);
		Question q=new Question();
		return Response.status(Status.OK).entity(q).build();
	}
	@GET
	@Path("displayQuesiton")
	@Produces(MediaType.APPLICATION_JSON)
	public Response displayQusetion(@QueryParam("id")int question_id) {
		Question q =questionService.DisplayQuestion(question_id);
		return Response.status(Status.OK).entity(q).build();
	}
	@PUT
	@Path("assignAnswer")
	@Produces(MediaType.APPLICATION_JSON)
	public Response assignAnswerToQuestion(@QueryParam("idQ")int question_id,@QueryParam("idA")int answer_id) {
		questionService.assignResponseToQuestion(question_id, answer_id);
		Question q=new Question();
		return Response.status(Status.OK).entity(q).build();
	}
	@GET
	@Path("getQuestions")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllQuestions() {
		return Response.status(Status.OK).entity(questionService.getQuestions()).build();
	}
}
