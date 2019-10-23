package com.esprit.resource;

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

import com.esprit.beans.Quiz;
import com.esprit.services.QuizService;

@Path("quiz")
public class QuizWS {
	@EJB
	QuizService quizService=new QuizService();
	
	@POST
	@Path("addQuiz")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addQuiz() {
		quizService.addQuiz();
		return Response.status(Status.OK).entity("the quiz has been successfully created").build();
	}
	@DELETE
	@Path("deleteQuiz")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeQuiz(@QueryParam("id")int quiz_id) {
		quizService.deleteQuiz(quiz_id);
		return Response.status(Status.OK).entity("the quiz has been deleted").build();
	}
	@GET
	@Path("getQuiz")
	@Produces(MediaType.APPLICATION_JSON)
	public Response displayQuiz(@QueryParam("id")int quiz_id) {
		return Response.status(Status.FOUND).entity(quizService.displayQuiz(quiz_id)).build();
	}
	@PUT
	@Path("assignQuestion")
	@Produces(MediaType.APPLICATION_JSON)
	public Response assignQuestionToQuiz(@QueryParam("idQuiz")int quiz_id,@QueryParam("idQuestion")int question_id) {
		quizService.assignQuestionToQuiz(quiz_id, question_id);
		return Response.status(Status.OK).entity("the question has been assigned to the quiz").build();
	}
	@PUT
	@Path("setQuizState")
	@Produces(MediaType.APPLICATION_JSON)
	public Response setQuizState(@QueryParam("id")int quiz_id) {
		quizService.setState(quiz_id);
		Quiz quiz=quizService.displayQuiz(quiz_id);
		return Response.status(Status.OK).entity(quiz.getState()).build();
	}
	@PUT
	@Path("setInterview")
	@Produces(MediaType.APPLICATION_JSON)
	public Response setInterview(@QueryParam("id")int quiz_id) {
		if(quizService.setInterview(quiz_id)) {
			return Response.status(Status.OK).entity("your interview has been set up please check for the time and date").build();
		}
		return Response.status(Status.OK).entity("we are sorry to inform you that you have failed the quiz").build();
	}
	@PUT
	@Path("setScore")
	@Produces(MediaType.APPLICATION_JSON)
	public Response setScore(@QueryParam("id")int quiz_id,@QueryParam("score")double score) {
		quizService.setScore(quiz_id, score);
		return Response.status(Status.OK).entity("your score is "+score).build();
	}
	

}
