package com.esprit.services;

import java.sql.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.esprit.Iservice.EnterpriseEventServiceRemote;
import com.esprit.beans.Enterprise;
import com.esprit.beans.EnterpriseEvent;
import com.esprit.beans.JobOffer;

@Stateless
@LocalBean
public class EnterpriseEventService implements EnterpriseEventServiceRemote{
	
	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;

	@Override
	public int AddEnterpriseEvent(EnterpriseEvent event, int enterpriseId) {
		Enterprise entrepriseManagedEntity = em.find(Enterprise.class, enterpriseId);

		event.setEnterprise(entrepriseManagedEntity);
		event.setEEvues(0);
		event.setEEstate(0);
		event.setEEtickets(0);
		em.persist(event);
		return event.getEEid();
	}

	@Override
	public void DeleteEnterpriseEvent(int id) {
		em.remove(em.find(EnterpriseEvent.class, id));
		
	}

	@Override
	public int ModifyEnterpriseEvent(int id, String title, String place,Date sdate, Date edate, String descrip, int minpart, int maxpart,float eprice  ) {
		Query query = em.createQuery("update EnterpriseEvent e set e.EEtitle=:title, e.EEplace=:place, e.EESdate=:sdate , e.EEEdate=:edate , e.EEdescription=:descrip, e.EEminparticipants=:minpart,e.EEmaxparticipants=:maxpart,e.EEprice=:eprice  where e.EEid=:id");
		query.setParameter("id", id);
		query.setParameter("title", title);
		query.setParameter("place", place);
		query.setParameter("sdate", sdate);
		query.setParameter("edate", edate);
		query.setParameter("descrip", descrip);
		query.setParameter("minpart", minpart);
		query.setParameter("maxpart", maxpart);
		query.setParameter("eprice", eprice);
		
		int modified = query.executeUpdate();
		return modified;
	}
	
	

	@Override
	public List<EnterpriseEvent> getAllEnterpriseEvent() {
		TypedQuery<EnterpriseEvent> q1 = em.createQuery("select e from EnterpriseEvent e ", EnterpriseEvent.class);
		return q1.getResultList();
	
	}

	
	@Override
	public EnterpriseEvent geteventById(int EEid) {
		
		TypedQuery<EnterpriseEvent> q1 = em.createQuery("select e from EnterpriseEvent e where EEid=:EEid", EnterpriseEvent.class);
		q1.setParameter("EEid", EEid);
		EnterpriseEvent e= q1.getSingleResult();
		e.setEEvues(e.getEEvues()+1);
		em.merge(e);
		return e;

	}
	

	
	
	
	
}
