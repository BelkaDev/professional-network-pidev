package com.esprit.service.candidate;

import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.Iservice.candidate.IJobServiceLocal;
import com.esprit.Iservice.candidate.IJobServiceRemote;
import com.esprit.beans.JobOffer;
import com.esprit.beans.candidate.Candidate;
import com.esprit.beans.candidate.JobApplication;

@Stateless
@LocalBean
public class JobService implements IJobServiceLocal, IJobServiceRemote {

	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;

	@Override
	public List<JobOffer> searchJob(String criteria) {
		return em.createQuery("select j from JobOffer j where JO_TITLE like '%"+criteria+"%' or JO_AREA like '%"+criteria+"%' or JO_DESCRIPTION like '%"+criteria+"%'").getResultList();
	}

	@Override
	public void applyForAJob(int candidateId, int jobId) {
		Candidate c = em.find(Candidate.class, candidateId);
		JobApplication ja = new JobApplication();
		ja.setCandidate(c);
		ja.setJobId(jobId);
		ja.setStatus("pending");
		c.getJobApplications().add(ja);
	}

	@Override
	public Set<JobApplication> getJobApplications(int candidateId) {
		return em.find(Candidate.class, candidateId).getJobApplications();
	}
	
	
	
}
