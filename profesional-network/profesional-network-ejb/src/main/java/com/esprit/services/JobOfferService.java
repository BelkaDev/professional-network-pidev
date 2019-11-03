package com.esprit.services;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.security.auth.x500.X500Principal;

import com.esprit.Iservice.JobOfferServiceRemote;
import com.esprit.beans.Enterprise;
import com.esprit.beans.EnterpriseEvent;
import com.esprit.beans.JobOffer;
import com.esprit.beans.User;
import com.esprit.beans.candidate.Candidate;
import com.esprit.beans.candidate.Certification;
import com.esprit.beans.candidate.Experience;
import com.esprit.enums.Role;
import com.esprit.utils.SendingMail;
import com.esprit.utils.UserSession;

@Stateless
@LocalBean
public class JobOfferService implements JobOfferServiceRemote {

	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;

	@Override
	public int AddJobOffer(JobOffer joboffer) {

		User user = em.find(User.class, UserSession.getInstance().getId());

		if (user.getRole() == Role.Project_Leader) {

			Enterprise enterpriseManagedEntity = em.find(Enterprise.class, user.getEnterprise().getEid());
			joboffer.setEnterprise(enterpriseManagedEntity);
			joboffer.setIsValid(0);
			joboffer.setVuesNumber(0);
			Calendar currenttime = Calendar.getInstance();
			Date JOdate = new Date((currenttime.getTime()).getTime());
			joboffer.setJOdate(JOdate);
			em.persist(joboffer);

			TypedQuery<User> query = em.createQuery("select u from User u where u.enterprise=:entid ", User.class)
					.setParameter("entid", enterpriseManagedEntity);

			List<User> listuser = query.getResultList();
			for (User u : listuser) {
				if (u.getRole() == Role.Human_Resouces) {
					String hr = u.getEmail();

					String contenu = ("Prject Leader  " + user.getUsername() + "  added a new job offer  "
							+ joboffer.getJOtitle());
					SendingMail sm = new SendingMail(contenu, hr, "new joboffer");
					SendingMail.envoyer();
				}
			}

			return joboffer.getJOid();
		}

		else if (user.getRole() == Role.Human_Resouces | user.getRole() == Role.Enterprise_Admin) {
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
		
		TypedQuery<String> querydescription = em.createQuery("SELECT j.JOdescription FROM JobOffer j where j.JOid=:id", String.class);
		querydescription.setParameter("id", id);
		String s = querydescription.getSingleResult();
		System.out.println("ccccccccccccccccccccccccccccccccccccc"+s);

		TypedQuery<Candidate> queryc = em.createQuery("SELECT c FROM Candidate c WHERE c.biography LIKE :b", Candidate.class);
		queryc.setParameter("b", "%" + s + "%");
		List<Candidate> listc = queryc.getResultList();
			for (Candidate c : listc) {
				
				System.out.println("ccccccccccccccccccccccccccccccccccccc"+c.getBiography());

					// Notif
						String contenu = ("a joboffer that may interrest you");
						SendingMail sm = new SendingMail(contenu, "koukiziedd711@gmail.com", "new joboffer");
						SendingMail.envoyer();
						
					
			}

		return validate;
	
	}
	
	
	
	
	
	@Override
	public void Notifcandidat() {
		
	}

}
