package com.esprit.resource.candidate;

import java.sql.Date;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.esprit.beans.candidate.Experience;
import com.esprit.beans.candidate.Training;
import com.esprit.service.candidate.TrainingService;;

@Path("training")
public class TrainingWs {

	@EJB
	TrainingService ts;
	
	@POST
	@Path("addTraining")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addTraning(@QueryParam("question") String question,
			@QueryParam("answer") String answer) {
		Training t = new Training();
		t.setQuestion(question);
		t.setAnswerURL(answer);
		ts.addTraining(t);
		return Response.status(Status.CREATED).entity("Training Added").build();
	}
	
	@GET
	@Path("getTraining")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTraining(@QueryParam("candidateId") int candidateId) {
		return Response.status(Status.FOUND).entity(ts.displayTraining(candidateId)).build();
	}
}
