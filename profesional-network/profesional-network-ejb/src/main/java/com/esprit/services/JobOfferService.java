package com.esprit.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.esprit.Iservice.JobOfferServiceRemote;
import com.esprit.beans.Enterprise;
import com.esprit.beans.Interests;
import com.esprit.beans.JobOffer;
import com.esprit.beans.User;
import com.esprit.enums.NOTIFICATION_TYPE;
import com.esprit.enums.Role;
import com.esprit.enums.Tags;
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
	
	@Override
	public int AddJobOffer(JobOffer joboffer) {
		User user= em.find(User.class, UserSession.getInstance().getId());

		if (UserSession.getInstance().getRole() == Role.Project_Leader) {

			
			Enterprise enterpriseManagedEntity = em.find(Enterprise.class, user.getEnterprise().getEid());
			joboffer.setEnterprise(enterpriseManagedEntity);
			joboffer.setIsValid(0);
			joboffer.setVuesNumber(0);
			joboffer.setInterests("Jee,dotnet");
			Calendar currenttime = Calendar.getInstance();
			Date JOdate = new Date((currenttime.getTime()).getTime());
			joboffer.setJOdate(JOdate);
			em.persist(joboffer);

			 // condition if same tags => notif
			return joboffer.getJOid();
		}

		else if (UserSession.getInstance().getRole() == Role.Human_Resouces | UserSession.getInstance().getRole() == Role.Enterprise_Admin) {
			
			Enterprise enterpriseManagedEntity = em.find(Enterprise.class, user.getEnterprise().getEid());
			joboffer.setEnterprise(enterpriseManagedEntity);
			joboffer.setIsValid(0);
			joboffer.setVuesNumber(0);
			Calendar currenttime = Calendar.getInstance();
			Date JOdate = new Date((currenttime.getTime()).getTime());
			joboffer.setJOdate(JOdate);
			em.persist(joboffer);

			return joboffer.getJOid();
		}

		return 0;
	}

	@Override
	public void DeleteJobOffer(int id) {
		em.remove(em.find(JobOffer.class, id));

	}

	@Override
	public int ModifyJobOffer(int id, String title, String area, String descrip) {
		Query query = em.createQuery(
				"update JobOffer j set j.JOtitle=:title, j.JOarea=:area, j.JOdescription=:descrip  where j.JOid=:id");
		query.setParameter("id", id);
		query.setParameter("title", title);
		query.setParameter("area", area);
		query.setParameter("descrip", descrip);
		int modified = query.executeUpdate();
		return modified;
	}

	public List<JobOffer> getAllJobOffer() {
		TypedQuery<JobOffer> q1 = em.createQuery("select j from JobOffer j ", JobOffer.class);
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
		Query query = em.createQuery("update JobOffer j set j.isValid=1 where j.JOid=:id");
		query.setMaxResults(1);
		query.setParameter("id", id);
		int validate = query.executeUpdate();
		List<String> jobtags = fetchOfferTags(id);
		
		List <User> allUsers = userservice.allUsers();
		for (User usr : allUsers) {
		List<String> userinterests = userservice.fetchUserInterests(usr.getId());
		userinterests.retainAll(jobtags); 
		if (userinterests.size()!=0)
		{
		String tags = String.join(", ", userinterests);
		NOTIFICATION_TYPE type = NOTIFICATION_TYPE.Offer;
		String notif_message = "A new offer with these tags might interest you : "+tags;
		notificationservice.CreateNotification(usr.getId(),notif_message,type ,id);
		}
		}
		return validate;
	
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

}
