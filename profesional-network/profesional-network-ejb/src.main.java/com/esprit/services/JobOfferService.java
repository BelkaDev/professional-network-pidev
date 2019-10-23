package com.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.esprit.Iservice.JobOfferServiceRemote;
import com.esprit.beans.Enterprise;
import com.esprit.beans.HumanResources;
import com.esprit.beans.JobOffer;
import com.esprit.beans.ProjectLeader;

@Stateless
@LocalBean
public class JobOfferService implements JobOfferServiceRemote {

	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;

	
	@Override
	public int AddJobOffer(JobOffer joboffer,int hrId,int plId) {
		HumanResources hrManagedEntity = em.find(HumanResources.class, hrId);
		ProjectLeader plManagedEntity = em.find(ProjectLeader.class, plId);
		joboffer.setHumanresources(hrManagedEntity);
		joboffer.setProjectleader(plManagedEntity);
		
		em.persist(joboffer);
		return joboffer.getJOid();
	}

	@Override
	public void DeleteJobOffer(int id) {
		em.remove(em.find(JobOffer.class, id));

	}
	
	

	@Override
	public int ModifyJobOffer(int id, String title, String area, String descrip) {
		Query query = em.createQuery("update JobOffer j set j.JOtitle=:title, j.JOarea=:area, j.JOdescription=:descrip  where j.JOid=:id");
		query.setParameter("id", id);
		query.setParameter("title", title);
		query.setParameter("area", area);
		query.setParameter("descrip", descrip);
		int modified = query.executeUpdate();
		return modified;
	}
	
	
	public List<JobOffer> getAllJobOffer(){
		TypedQuery<JobOffer> q1 = em.createQuery("select j from JobOffer j ", JobOffer.class);
		 return q1.getResultList();
	}
	
	
	@Override
	public List<JobOffer> getJobofferByExperience(int JOexperience) {
		TypedQuery<JobOffer> q1 = em.createQuery("select j from JobOffer j where JOexperience=:JOexperience", JobOffer.class);
		q1.setParameter("JOexperience", JOexperience);
		 return q1.getResultList();

	}
	
	

}
