package com.esprit.service.candidate;

import java.sql.Date;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;import java.util.stream.Collector;
import java.util.stream.Collectors;

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
	public void addExperience(Experience e,int CandidateID) {
		Candidate c=em.find(Candidate.class, CandidateID);
		c.getExperiences().add(e);
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
	public Set<Candidate> displayCandidatesByExperience(int id) {
		
		Experience ex= em.find(Experience.class, id);
		return ex.getCandidates();
	}
	
	@Override
	public void updateExperience(int id, String d) {
		Experience e=em.find(Experience.class, id);
		e.setDesignation(d);
		
	}
	@Override
	public void addCandidate(Candidate c) {
		
		em.persist(c);
		
		
	}
	
	
	
	
}
