package com.esprit.resource.candidate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
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
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.esprit.beans.candidate.Activity;
import com.esprit.beans.candidate.Candidate;
import com.esprit.beans.candidate.Certification;
import com.esprit.beans.candidate.Experience;
import com.esprit.beans.candidate.Skill;
import com.esprit.service.candidate.CandidateService;

@Path("candidate")
public class CandidateWs {
	private static final String UPLOAD_FOLDER = System.getProperty("user.dir")+"/files/";
	@EJB
	CandidateService cs;
	private void CreateFolderIfNotExist(String directory) {
	    File dir = new File(directory);
	    if (!dir.exists()) dir.mkdirs();
	}
	
	private String uploadFile( Map<String, List<InputPart>> uploadForm)
	{
    String fileName = "";
	List<InputPart> inputParts = uploadForm.get("file");
	for (InputPart inputPart : inputParts) {
	 try {
			MultivaluedMap<String, String> header = inputPart.getHeaders();
			fileName = getFileName(header);
			InputStream inputStream = inputPart.getBody(InputStream.class,null);
			byte [] bytes = IOUtils.toByteArray(inputStream);
			fileName = UPLOAD_FOLDER + fileName;
			writeFile(bytes,fileName);
			System.out.println("Done");

		  } catch (IOException e) {
			e.printStackTrace();
		  }
	}
			return fileName;
	}
	
	private String getFileName(MultivaluedMap<String, String> header) {

		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
		
		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {
				String[] name = filename.split("=");
				String finalFileName = name[1].trim().replaceAll("\"", "");
				return finalFileName;
			}
		}
		return "unknown";
	}

	private void writeFile(byte[] content, String filename) throws IOException {
		File file = new File(filename);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream fop = new FileOutputStream(file);
		fop.write(content);
		fop.flush();
		fop.close();
	}
	
	@POST
	@Path("addCandidate")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCandidate(@QueryParam("firstName") String firstName,@QueryParam("lastName") String lastName,@QueryParam("biography") String biography,
			@QueryParam("rating") double rating,MultipartFormDataInput input) {
		CreateFolderIfNotExist(UPLOAD_FOLDER);
		String fileName = uploadFile(input.getFormDataMap());
		Candidate c = new Candidate();
		c.setFirstName(firstName);
		c.setLastName(lastName);
		c.setBiography(biography);
		c.setRating(rating);
		c.setCv(fileName);
		cs.addCandidate(c);
		return Response.status(Status.CREATED).entity("Candidate Added").build();
	}
	
	@GET
	@Path("getCandidates")
	@Produces(MediaType.APPLICATION_JSON)
	public Response displayCandidates() {
		return Response.status(Status.FOUND).entity(cs.displayCandidates()).build();
	}

	@POST
	@Path("addExperience")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addExperience(@QueryParam("designation") String designation,
			@QueryParam("type") String type,@QueryParam("startDate") Date startDate,@QueryParam("endDate") Date endDate,@QueryParam("candidateID") int candidateID) {
		Experience e = new Experience();
		e.setDesignation(designation);
		e.setType(type);
		e.setStartDate(startDate);
		e.setEndDate(endDate);
		return Response.status(Status.OK).entity(cs.addProfileObject(e, candidateID)).build();
	}
	
	@POST
	@Path("addSkill")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addSkill(@QueryParam("designation") String designation,@QueryParam("rating") double rating, @QueryParam("candidateID") int candidateID) {
		Skill s = new Skill();
		s.setDesignation(designation);
		s.setRating(rating);
		cs.addProfileObject(s, candidateID);
		return Response.status(Status.CREATED).entity("Skill Added").build();
	}
	
	@POST
	@Path("addActivity")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addActivity(@QueryParam("designation") String designation,@QueryParam("Date") Date date, @QueryParam("candidateID") int candidateID) {
		Activity a = new Activity();
		a.setDesignation(designation);
		a.setDate(date);
		cs.addProfileObject(a, candidateID);
		return Response.status(Status.CREATED).entity("Activity Added").build();
	}
	@POST
	@Path("addCertification")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCertification(@QueryParam("designation") String designation,@QueryParam("companyName")String companyName,@QueryParam("issueDate")Date issueDate,@QueryParam("expiryDate") Date expiryDate,@QueryParam("credentialId")String credentialId, @QueryParam("candidateID") int candidateID) {
		Certification cert = new Certification();
		cert.setDesignation(designation);
		cert.setCompanyName(companyName);
		cert.setIssueDate(issueDate);
		cert.setExpiryDate(expiryDate);
		cert.setCredentialId(credentialId);
		cs.addProfileObject(cert, candidateID);
		return Response.status(Status.CREATED).entity("Certification Added").build();
	}
	
	
	
	@POST
	@Path("addExistingExperience")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addExistingExperience(@QueryParam("experienceID") int experienceID,@QueryParam("candidateID") int candidateID) {
		cs.addExistingProfileObject(candidateID, experienceID, new Experience());
		return Response.status(Status.CREATED).entity("Experience Added").build();
	}
	@POST
	@Path("addExistingSkill")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addExistingSkill(@QueryParam("skillID") int skillID,@QueryParam("candidateID") int candidateID) {
		cs.addExistingProfileObject(candidateID, skillID, new Skill());
		return Response.status(Status.CREATED).entity("Skill Added").build();
	}
	@POST
	@Path("addExistingCertification")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addExistingCertification(@QueryParam("certificationID") int certificationID,@QueryParam("candidateID") int candidateID) {
		cs.addExistingProfileObject(candidateID, certificationID, new Certification());
		return Response.status(Status.CREATED).entity("Skill Added").build();
	}
	
	@POST
	@Path("addExistingActivity")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addExistingActivity(@QueryParam("activityID") int activityID,@QueryParam("candidateID") int candidateID) {
		cs.addExistingProfileObject(candidateID, activityID, new Activity());
		return Response.status(Status.CREATED).entity("Activity Added").build();
	}
	
	@DELETE
	@Path("deleteExperience")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteExperience(@QueryParam("experienceId")int experienceID,@QueryParam("candidateId")int candidateID) {
		return Response.status(Status.OK).entity(cs.deleteProfileObject(experienceID, new Experience(), candidateID)).build();
	}
	@DELETE
	@Path("deleteSkill")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteSkill(@QueryParam("skillID")int skillID,@QueryParam("candidateID")int candidateID) {
		cs.deleteProfileObject(skillID, new Skill(), candidateID);
		return Response.status(Status.OK).entity("the Skill has been deleted").build();
	}
	
	@DELETE
	@Path("deleteCertification")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCertification(@QueryParam("certificationID")int certificationID,@QueryParam("candidateID")int candidateID) {
		cs.deleteProfileObject(certificationID, new Certification(), candidateID);
		return Response.status(Status.OK).entity("the Certification has been deleted").build();
	}
	
	@DELETE
	@Path("deleteActivity")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteActivity(@QueryParam("activityID")int activityID,@QueryParam("candidateID")int candidateID) {
		cs.deleteProfileObject(activityID, new Activity(), candidateID);
		return Response.status(Status.OK).entity("the Activity has been deleted").build();
	}
	
	@GET
	@Path("getExperience")
	@Produces(MediaType.APPLICATION_JSON)
	public Response displayExperience(@QueryParam("id")int id) {
		return Response.status(Status.OK).entity(cs.displayProfileObject(id, new Experience())).build();
	}
	
	@GET
	@Path("getSkill")
	@Produces(MediaType.APPLICATION_JSON)
	public Response displaySkill(@QueryParam("id")int id) {
		return Response.status(Status.OK).entity(cs.displayProfileObject(id, new Skill())).build();
	}
	@GET
	@Path("getActivity")
	@Produces(MediaType.APPLICATION_JSON)
	public Response displayActivity(@QueryParam("id")int id) {
		return Response.status(Status.OK).entity(cs.displayProfileObject(id, new Activity())).build();
	}
	
	@GET
	@Path("getCertification")
	@Produces(MediaType.APPLICATION_JSON)
	public Response displayCertification(@QueryParam("id")int id) {
		return Response.status(Status.OK).entity(cs.displayProfileObject(id, new Certification())).build();
	}
	
	@GET
	@Path("getAllExperience")
	@Produces(MediaType.APPLICATION_JSON)
	public Response displayAllExperience(@QueryParam("id")int id) {
		return Response.status(Status.OK).entity(cs.displayListOfProfileObject(id, new Experience())).build();
	}
	
	@GET
	@Path("getCandidateByExperience")
	@Produces(MediaType.APPLICATION_JSON)
	public Response displayCandidateByExperience(@QueryParam("id")int id) {
		return Response.status(Status.OK).entity(cs.displayCandidatesByProfileObject(id, new Experience())).build();
	}
	
	@GET
	@Path("getCandidateByActivity")
	@Produces(MediaType.APPLICATION_JSON)
	public Response displayCandidateByActivity(@QueryParam("id")int id) {
		return Response.status(Status.OK).entity(cs.displayCandidatesByProfileObject(id, new Activity())).build();
	}
	@GET
	@Path("getCandidateBySkill")
	@Produces(MediaType.APPLICATION_JSON)
	public Response displayCandidateBySkill(@QueryParam("id")int id) {
		return Response.status(Status.OK).entity(cs.displayCandidatesByProfileObject(id, new Skill())).build();
	}
	@GET
	@Path("getCandidateByCertification")
	@Produces(MediaType.APPLICATION_JSON)
	public Response displayCandidateByCertification(@QueryParam("id")int id) {
		return Response.status(Status.OK).entity(cs.displayCandidatesByProfileObject(id, new Certification())).build();
	}
	
	@PUT
	@Path("updateExperience")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateExperience(@QueryParam("id") int id, @QueryParam("designation") String designation,@QueryParam("type")String type,@QueryParam("startDate")Date startDate,@QueryParam("endDate")Date endDate) {
		Experience e = new Experience();
		e.setDesignation(designation);
		e.setStartDate(startDate);
		e.setEndDate(endDate);
		e.setType(type);
		cs.updateProfileObject(id, e);
		return Response.status(Status.OK).entity("Experience updated").build();
	}
	
	@PUT
	@Path("updateActivity")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateActivity(@QueryParam("id") int id,@QueryParam("designation")String designation,@QueryParam("Date")Date date ) {
		Activity a = new Activity();
		a.setDesignation(designation);
		a.setDate(date);
		cs.updateProfileObject(id, a);
		return Response.status(Status.OK).entity("Activity updated").build();
	}
	@PUT
	@Path("updateSkill")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateSkill(@QueryParam("id") int id,@QueryParam("designation")String designation,@QueryParam("rating")double rating ) {
		Skill s = new Skill();
		s.setDesignation(designation);
		s.setRating(rating);
		cs.updateProfileObject(id, s);
		return Response.status(Status.OK).entity("Skill updated").build();
	}
	
	@PUT
	@Path("updateCertification")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCertification(@QueryParam("id") int id,@QueryParam("designation") String designation,
			@QueryParam("companyName") String companyName,
			@QueryParam("issueDate") Date issueDate,
			@QueryParam("expiryDate") Date expiryDate,
			@QueryParam("credentialID") String credentialId) {
		Certification cert = new Certification();
		cert.setDesignation(designation);
		cert.setCompanyName(companyName);
		cert.setIssueDate(issueDate);
		cert.setExpiryDate(expiryDate);
		cert.setCredentialId(credentialId);
		cs.updateProfileObject(id, cert);
		return Response.status(Status.OK).entity("Certification updated").build();
	}
	
	
	@POST
	@Path("addView")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addView(@QueryParam("viewerID") int viewerId,
			@QueryParam("viewedID") int viewedId) {
			cs.addView(viewerId, viewedId);
		return Response.status(Status.CREATED).entity("Visited Profile").build();
	}
	
	@GET
	@Path("getViews")
	@Produces(MediaType.APPLICATION_JSON)
	public Response displayViews(@QueryParam("candidateId")int candidateId) {
		return Response.status(Status.FOUND).entity(cs.displayViews(candidateId)).build();
	}
	
	@DELETE
	@Path("deleteView")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteView(@QueryParam("viewID") int viewId) {
		cs.deleteView(viewId);
		return Response.status(Status.OK).entity("the View has been deleted").build();
	}
	
	
}
