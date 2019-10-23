package com.esprit.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.Iservice.EnterpriseAdminServiceRemote;
import com.esprit.beans.EnterpriseAdmin;

@Stateless
@LocalBean
public class EnterpriseAdminService implements EnterpriseAdminServiceRemote {

	
	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;

	@Override
	public int AddEnterpriseAdmin(EnterpriseAdmin Eadmin) {
		em.persist(Eadmin);
		return Eadmin.getEAid();
	}
	
}
