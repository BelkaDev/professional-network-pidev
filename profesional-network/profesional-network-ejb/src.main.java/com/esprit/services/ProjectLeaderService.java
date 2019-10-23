package com.esprit.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.Iservice.ProjectLeaderServiceRemote;
import com.esprit.beans.ProjectLeader;

@Stateless
@LocalBean
public class ProjectLeaderService implements ProjectLeaderServiceRemote {
	

	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;

	@Override
	public int AddProjectLeader(ProjectLeader Pleader) {
		em.persist(Pleader);
		return Pleader.getPLid();
		
		
	}
	
}
 