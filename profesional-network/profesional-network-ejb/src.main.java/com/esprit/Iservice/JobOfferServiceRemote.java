package com.esprit.Iservice;

import java.util.List;

import javax.ejb.Remote;

import com.esprit.beans.JobOffer;

@Remote
public interface JobOfferServiceRemote {

	public int AddJobOffer(JobOffer joboffer,int hrId,int plId);
	public void DeleteJobOffer(int id );
	public int ModifyJobOffer(int id, String title, String area, String descrip);
	public List<JobOffer> getAllJobOffer();
	public List<JobOffer> getJobofferByExperience(int JOexperience);
}

