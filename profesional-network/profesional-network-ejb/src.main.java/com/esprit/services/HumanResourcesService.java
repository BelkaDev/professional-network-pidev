package com.esprit.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.Iservice.HumanResourcesServiceRemote;
import com.esprit.beans.HumanResources;

@Stateless
@LocalBean
public class HumanResourcesService implements HumanResourcesServiceRemote {

	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;
	
	@Override
	public int AddHumanResources(HumanResources Hresources) {
		em.persist(Hresources);
		return Hresources.getHRid();
	}

}
