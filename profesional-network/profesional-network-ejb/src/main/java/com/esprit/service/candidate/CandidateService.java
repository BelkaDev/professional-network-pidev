package com.esprit.service.candidate;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.Iservice.candidate.ICandidateServiceLocal;
import com.esprit.Iservice.candidate.ICandidateServiceRemote;
import com.esprit.beans.candidate.Experience;

@Stateless
@LocalBean
public class CandidateService implements ICandidateServiceLocal,ICandidateServiceRemote  {
	
	@PersistenceContext(unitName="pidevtwin-ejb")
	EntityManager em;
	@Override
	public void addExperience(Experience c) {
		em.persist(c);
	}
	@Override
	public void deleteExperience(int id) {
		em.remove(em.find(Experience.class, id));
	}
	@Override
	public Experience displayExperience(int id) {
		Experience e=em.find(Experience.class, id);
		return e;
	}
	@Override
	public void updateExperience(int id, String d) {
		Experience e=em.find(Experience.class, id);
		e.setDesignation(d);
		
	}
	
	
	
	
}
