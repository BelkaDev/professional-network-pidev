package com.esprit.Iservice.candidate;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import com.esprit.beans.JobOffer;
import com.esprit.beans.candidate.Candidate;
import com.esprit.beans.candidate.JobApplication;

@Remote
public interface IJobServiceRemote {

	public List<JobOffer> searchJob(String criteria);
	public Candidate applyForAJob(int candidateId,int jobId);
	public Candidate cancelApplication(int candidateId,int jobApplicationId);
	public Set<JobApplication> getJobApplications(int candidateId);
}
