package com.esprit.services;


import java.sql.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.esprit.Iservice.EnterpriseSeviceRemote;
import com.esprit.beans.Enterprise;
import com.esprit.beans.EnterpriseEvent;
import com.esprit.beans.JobOffer;

@Stateless
@LocalBean
public class EnterpriseService  implements EnterpriseSeviceRemote{

	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;
	
	@Override
	public int AddEnterprise(Enterprise enterprise) {
		em.persist(enterprise);
		return enterprise.getEid();
	    }
	
	@Override
	public void DeleteEnterprise(int id) {
		em.remove(em.find(Enterprise.class, id));
		}
	
	
	
	@Override
	public int ModifyEnterprise(int id, String name, String domain, String location, int empnumber, String descrip) {
		Query query = em.createQuery("update Enterprise e set e.Ename=:name, e.Edomain=:domain, e.Elocation=:location , e.Employeesnumber=:empnumber  where e.Eid=:id");
		query.setParameter("id", id);
		query.setParameter("name", name);
		query.setParameter("domain", domain);
		query.setParameter("location", location);
		query.setParameter("empnumber", empnumber);
		query.setParameter("descrip", descrip);
		int modified = query.executeUpdate();
		return modified;
	}


}
