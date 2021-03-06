package com.esprit.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.esprit.Iservice.JobOfferServiceRemote;
import com.esprit.beans.Enterprise;
import com.esprit.beans.JobOffer;
import com.esprit.beans.User;
import com.esprit.enums.NOTIFICATION_TYPE;
import com.esprit.enums.Role;
import com.esprit.utils.UserSession;

@Stateless
@LocalBean
public class JobOfferService implements JobOfferServiceRemote {


	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;
	
	
	@EJB
	UserService userservice = new UserService();
	@EJB
	NotificationService notificationservice = new NotificationService();
	@EJB
	InterestsService intersetsservice;
	
	
	
	@Override
	public int AddJobOffer(JobOffer joboffer,int entid) {
		User user= em.find(User.class, UserSession.getInstance().getId());
		//if (UserSession.getInstance().getRole() == Role.Project_Leader) {

			Enterprise enterpriseManagedEntity = em.find(Enterprise.class,entid);
			joboffer.setEnterprise(enterpriseManagedEntity);
			joboffer.setIsValid(0);
			joboffer.setVuesNumber(0);
			Calendar currenttime = Calendar.getInstance();
			Date JOdate = new Date((currenttime.getTime()).getTime());
			joboffer.setJOdate(JOdate);
			
			em.persist(joboffer);
			
			
			return joboffer.getJOid();
		}

		/*else if (UserSession.getInstance().getRole() == Role.Human_Resouces ) {
			
			Enterprise enterpriseManagedEntity = em.find(Enterprise.class, user.getEnterprise().getEid());
			joboffer.setEnterprise(enterpriseManagedEntity);
			joboffer.setIsValid(1);
			joboffer.setVuesNumber(0);
			Calendar currenttime = Calendar.getInstance();
			Date JOdate = new Date((currenttime.getTime()).getTime());
			joboffer.setJOdate(JOdate);
			em.persist(joboffer);

			return joboffer.getJOid();
		}

		return 0;
	}*/

	@Override
	public void DeleteJobOffer(int JOid) {
		em.remove(em.find(JobOffer.class, JOid));

	}

	@Override
	public int ModifyJobOffer(int id, String title, String area, String descrip, int exp, String interests) {
		Query query = em.createQuery(
				"update JobOffer j set j.JOtitle=:title, j.JOarea=:area, j.JOdescription=:descrip, j.JOexperience=:exp, j.interests=:interests   where j.JOid=:id");
		query.setParameter("id", id);
		query.setParameter("title", title);
		query.setParameter("area", area);
		query.setParameter("descrip", descrip);
		query.setParameter("exp", exp);
		query.setParameter("interests", interests);
		int modified = query.executeUpdate();
		return modified;
	}

	public List<JobOffer> getAllJobOffer() {
		TypedQuery<JobOffer> q1 = em.createQuery("select j from JobOffer j ORDER BY JOid DESC ", JobOffer.class);
		return q1.getResultList();
	}
	
	public List<JobOffer> getJobofferByEnt(int entid){
		Enterprise enterpriseManagedEntity = em.find(Enterprise.class, entid);
		TypedQuery<JobOffer> q1 = em.createQuery("select j from JobOffer j where j.enterprise=:entid ", JobOffer.class);
		q1.setParameter("entid", enterpriseManagedEntity);
		return q1.getResultList();
	}

	@Override
	public List<JobOffer> getJobofferByExperience(int JOexperience) {
		TypedQuery<JobOffer> q1 = em.createQuery("select j from JobOffer j where JOexperience=:JOexperience",
				JobOffer.class);
		q1.setParameter("JOexperience", JOexperience);
		return q1.getResultList();

	}

	@Override
	public JobOffer getJobofferById(int JOid) {

		TypedQuery<JobOffer> q1 = em.createQuery("select j from JobOffer j where JOid=:JOid", JobOffer.class);
		q1.setParameter("JOid", JOid);
		JobOffer j = q1.getSingleResult();
		j.setVuesNumber(j.getVuesNumber() + 1);
		em.merge(j);
		return j;

	}

	@Override
	public List<JobOffer> getJobofferOrderByVues() {
		TypedQuery<JobOffer> q1 = em.createQuery("select j from JobOffer j ORDER BY VuesNumber DESC", JobOffer.class);
		return q1.getResultList();

	}

	@Override
	public int ValidateJoboffer(int id) {
		User user= em.find(User.class, UserSession.getInstance().getId());
	//	if(UserSession.getInstance().getRole() == Role.Enterprise_Admin) {
		
		Query query = em.createQuery("update JobOffer j set j.isValid=1 where j.JOid=:id");
		query.setMaxResults(1);
		query.setParameter("id", id);
		int validate = query.executeUpdate();
		List<String> jobtags = fetchOfferTags(id);
		
		List <User> allUsers = userservice.getUserByRole();
		for (User usr : allUsers) {
		List<String> userinterests = userservice.fetchUserInterests(usr.getId());
		userinterests.retainAll(jobtags); // get common tags
		if (userinterests.size()!=0) // if there are common tags send notif
		{
		String tags = String.join(", ", userinterests);
		NOTIFICATION_TYPE type = NOTIFICATION_TYPE.Offer;
		String notif_message = "A new job offer with these tags might interest you : "+tags;
		notificationservice.CreateNotification(usr.getId(),notif_message,type ,id);
		}
		}
		
		return validate;
		//}
	
	
	}
	
	
	@Override
	public List<String> fetchOfferTags(int idOffer) {
		JobOffer offer= em.find(JobOffer.class,idOffer);
		String interests = offer.getInterests();
		List<String> interestsList = new ArrayList<String>
		(Arrays.asList(interests.split(",")));
		return interestsList;		
	}
	
	
	@Override
	public void Notifcandidat() {
		
	}

	@Override
	public List<JobOffer> SearchJoboffer(String search) {
		TypedQuery<JobOffer> queryc = em.createQuery("SELECT j FROM JobOffer j WHERE j.JOtitle LIKE :input or j.JOarea LIKE :input or j.JOdescription LIKE :input or j.JOexperience LIKE :input ORDER BY j.JOdate DESC ",JobOffer.class);
		queryc.setParameter("input", "%" + search + "%");
		return queryc.getResultList();
	}


	
	
	
	
	
	
	
	
}
