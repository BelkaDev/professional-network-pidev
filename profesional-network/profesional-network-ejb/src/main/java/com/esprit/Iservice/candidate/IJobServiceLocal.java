package com.esprit.Iservice.candidate;

import java.util.List;
import java.util.Set;

import javax.ejb.Local;
import com.esprit.beans.JobOffer;
import com.esprit.beans.candidate.JobApplication;

@Local
public interface IJobServiceLocal {
	public List<JobOffer> searchJob(String criteria);
	public void applyForAJob(int candidateId,int jobId);
	public Set<JobApplication> getJobApplications(int candidateId);
}
