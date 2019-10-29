package com.esprit.service.candidate;

import java.sql.Date;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.Iservice.candidate.ICandidateServiceLocal;
import com.esprit.Iservice.candidate.ICandidateServiceRemote;
import com.esprit.beans.candidate.Candidate;
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
	public Set<Experience> displayCandidatesByExperience(int id) {
		Candidate e=em.find(Candidate.class, id);
		return e.getExperiences();
	}
	
	@Override
	public void updateExperience(int id, String d) {
		Experience e=em.find(Experience.class, id);
		e.setDesignation(d);
		
	}
	@Override
	public void addCandidate() {
		Candidate c = new Candidate();
		c.setBiography("cascade");
		c.setRating(1);
		Experience e = new Experience();
		e.setDesignation("experience cas");
		e.setType("cassss");
		e.setStartDate(new Date(2010,02,20));
		e.setEndDate(new Date(2011,02,20));
		c.getExperiences().add(e);
		em.persist(c);
		
		
	}
	
	
	
	
}
