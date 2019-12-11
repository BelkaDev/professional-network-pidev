package com.esprit.resource;

import java.util.List;

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

import com.esprit.beans.JobOffer;
import com.esprit.beans.Quiz;
import com.esprit.services.QuizService;

@Path("quiz")
public class QuizWS {
	@EJB
	QuizService quizService = new QuizService();
	
	public static Quiz q=new Quiz();

	@POST
	@Path("addQuiz")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addQuiz(@QueryParam("idC") int candidate_id, @QueryParam("idO") int offer_id) {
		quizService.addQuiz(candidate_id, offer_id);
		return Response.status(Status.OK).entity(q).build();
	}

	@DELETE
	@Path("deleteQuiz")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeQuiz(@QueryParam("id") int quiz_id) {
		quizService.deleteQuiz(quiz_id);
		String msg="the quiz has been deleted";
		return Response.status(Status.OK).entity(q).build();
	}

	@GET
	@Path("getQuiz")
	@Produces(MediaType.APPLICATION_JSON)
	public Response displayQuiz(@QueryParam("id") int quiz_id) {
		return Response.status(Status.OK).entity(quizService.displayQuiz(quiz_id)).build();
	}

	@PUT
	@Path("assignQuestion")
	@Produces(MediaType.APPLICATION_JSON)
	public Response assignQuestionToQuiz(@QueryParam("idQuiz") int quiz_id, @QueryParam("idQuestion") int question_id) {
		if (quizService.assignQuestionToQuiz(quiz_id, question_id))
			return Response.status(Status.OK).entity(q).build();
		return Response.status(Status.OK).entity(q).build();
	}

	@PUT
	@Path("setQuizState")
	@Produces(MediaType.APPLICATION_JSON)
	public Response setQuizState(@QueryParam("id") int quiz_id) {
		quizService.setState(quiz_id);
		Quiz quiz = quizService.displayQuiz(quiz_id);
		return Response.status(Status.OK).entity(quiz.getState()).build();
	}

	@PUT
	@Path("setInterview")
	@Produces(MediaType.APPLICATION_JSON)
	public Response setInterview(@QueryParam("id") int quiz_id) {
		String msg="we are sorry to inform you that you have failed the quiz";
		if (quizService.setInterview(quiz_id)) {
			msg="your interview has been set up please check for the time and date";
			return Response.status(Status.OK)
					.entity(msg).build();
		}
		return Response.status(Status.OK).entity(q).build();
	}

	@PUT
	@Path("setScore")
	@Produces(MediaType.APPLICATION_JSON)
	public Response setScore(@QueryParam("id") int quiz_id, @QueryParam("score") double score) {
		quizService.setScore(quiz_id, score);
		return Response.status(Status.OK).entity(q).build();
	}

	@GET
	@Path("OfferQuiz")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showOfferQuiz(@QueryParam("idOffer") int offer_id) {
		return Response.status(Status.OK).entity(quizService.getCandidateForOffer(offer_id)).build();
	}
	
	@GET
	@Path("candidateQuiz")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showCandidateQuiz(@QueryParam("idCandidate") int offer_id) {
		return Response.status(Status.OK).entity(quizService.getCandidateQuiz(offer_id)).build();
	}

	@PUT
	@Path("passQuiz")
	@Produces(MediaType.APPLICATION_JSON)
	public Response takeQuiz(@QueryParam("idQuiz") int quiz_id, @QueryParam("list") List<Integer> answersList) {
		if (quizService.CorrectQuiz(quiz_id, answersList))
			return Response.status(Status.OK).entity(q)
					.build();
		return Response.status(Status.OK).entity(q).build();
	}

	@PUT
	@Path("validateQuiz")
	@Produces(MediaType.APPLICATION_JSON)
	public Response validate(@QueryParam("idOffer") int offer_id) {
		quizService.ChooseCnadidate(offer_id);
		String msg="the selection process for interviews has been completed";
		return Response.status(Status.OK).entity(q).build();
	}
	@GET
	@Path("moreoffers")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOffers(@QueryParam("idQ")int quiz_id) {
		List<JobOffer> list=quizService.selectOffers(quiz_id);
		if(list.isEmpty())
			return Response.status(Status.OK).entity(q).build();
		return Response.status(Status.OK).entity(list).build();
	}

}
