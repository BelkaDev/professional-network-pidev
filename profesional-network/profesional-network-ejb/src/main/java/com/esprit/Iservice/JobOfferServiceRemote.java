package com.esprit.Iservice;

import java.util.List;

import javax.ejb.Remote;

import com.esprit.beans.JobOffer;

@Remote
public interface JobOfferServiceRemote {

	public int AddJobOffer(JobOffer joboffer,int entid);
	public void DeleteJobOffer(int JOid);
	public int ModifyJobOffer(int id, String title, String area, String descrip, int exp, String interests);
	public List<JobOffer> getAllJobOffer();
	public List<JobOffer> getJobofferByExperience(int JOexperience);
	public List<JobOffer> getJobofferOrderByVues();
	public JobOffer getJobofferById(int JOid);
	public int ValidateJoboffer(int id);
	public void Notifcandidat();
	List<String> fetchOfferTags(int idOffer);
	public List<JobOffer> SearchJoboffer(String search);
	public List<JobOffer> getJobofferByEnt(int entid);
}

