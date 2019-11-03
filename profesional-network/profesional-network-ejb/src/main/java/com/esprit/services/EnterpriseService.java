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
import com.esprit.beans.User;
import com.esprit.enums.Role;
import com.esprit.utils.UserSession;

@Stateless
@LocalBean
public class EnterpriseService  implements EnterpriseSeviceRemote{

	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;
	
	@Override
	public int AddEnterprise(Enterprise enterprise) {
		
User user = em.find(User.class, UserSession.getInstance().getId());
		
		if(user.getRole()==Role.Enterprise_Admin) {
			
			em.persist(enterprise);
			Enterprise ent = em.find(Enterprise.class, enterprise.getEid());
			user.setEnterprise(ent);
			em.merge(user);

			return enterprise.getEid();
		}
		
		return 0;
	    }
	
	
	@Override
	public void DeleteEnterprise(int id) {
		em.remove(em.find(Enterprise.class, id));
		}
	
	
	
	@Override
	public int ModifyEnterprise(int id, String name, String domain, String location, int empnumber, String descrip) {
		Query query = em.createQuery("update Enterprise e set e.Ename=:name, e.Edomain=:domain, e.Elocation=:location , e.Edescription=:descrip , e.Employeesnumber=:empnumber  where e.Eid=:id");
		query.setParameter("id", id);
		query.setParameter("name", name);
		query.setParameter("domain", domain);
		query.setParameter("location", location);
		query.setParameter("empnumber", empnumber);
		query.setParameter("descrip", descrip);
		
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaa"+UserSession.getInstance().getId());
		User user = em.find(User.class, UserSession.getInstance().getId());
		
		if(user.getRole()==Role.Enterprise_Admin && user.getEnterprise().getEid()==id ) {
			int modified = query.executeUpdate();
			
			return modified;
		}
		
		return 0;
		
		
	}

	@Override
	public Enterprise getenterpriseById(int Eid) {
		
		TypedQuery<Enterprise> q1 = em.createQuery("select e from Enterprise e where Eid=:Eid", Enterprise.class);
		q1.setParameter("Eid", Eid);
		Enterprise e= q1.getSingleResult();
		em.merge(e);
		return e;

	}
	
	
	
	

}
