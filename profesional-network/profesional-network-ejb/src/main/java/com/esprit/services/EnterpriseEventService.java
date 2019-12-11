package com.esprit.services;

import java.sql.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.esprit.Iservice.EnterpriseEventServiceRemote;
import com.esprit.beans.Enterprise;
import com.esprit.beans.EnterpriseEvent;
import com.esprit.beans.FileUpload;
import com.esprit.beans.JobOffer;
import com.esprit.beans.User;
import com.esprit.enums.Role;
import com.esprit.utils.UserSession;

@Stateless
@LocalBean
public class EnterpriseEventService implements EnterpriseEventServiceRemote{
	@EJB
	FileService fileService;
	
	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;

	@Override
	public int AddEnterpriseEvent(EnterpriseEvent event,int user,String filename) {
		User us = em.find(User.class, user);
		
		//if(UserSession.getInstance().getRole()==Role.Enterprise_Admin) {
		FileUpload newfile = new FileUpload();
		newfile.setPath(filename);
		newfile.setType(null);
		fileService.addFile(newfile);
		event.setFile(newfile);
		
		
		Enterprise entrepriseManagedEntity = em.find(Enterprise.class, us.getEnterprise().getEid());

		event.setEnterprise(entrepriseManagedEntity);
		event.setEEvues(0);
		event.setEEstate(1);
		event.setEEtickets(0);
		em.persist(event);
		return event.getEEid();
		//}
		//return 0;
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
		TypedQuery<EnterpriseEvent> q1 = em.createQuery("select e from EnterpriseEvent e ORDER BY EEid DESC ", EnterpriseEvent.class);
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
	
	
	public List<EnterpriseEvent> getEventByEnt(int entid){
		Enterprise enterpriseManagedEntity = em.find(Enterprise.class, entid);
		TypedQuery<EnterpriseEvent> q1 = em.createQuery("select e from EnterpriseEvent e where e.enterprise=:entid ", EnterpriseEvent.class);
		q1.setParameter("entid", enterpriseManagedEntity);
		return q1.getResultList();
	}
	

	
	
	
	
}
