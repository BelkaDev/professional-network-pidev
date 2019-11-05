package com.esprit.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.esprit.Iservice.IStatistiqueServiceLocal;
import com.esprit.Iservice.IStatistiqueServiceRemote;
import com.esprit.beans.Enterprise;

import com.esprit.beans.InterviewState;
import com.esprit.beans.JobOffer;
import com.esprit.beans.Notification;
import com.esprit.beans.Pack;
import com.esprit.beans.Payement;
import com.esprit.beans.Quiz;
import com.esprit.beans.Statistics;
import com.esprit.beans.User;
import com.esprit.beans.UserPack;
import com.esprit.beans.candidate.Candidate;
import com.esprit.enums.NOTIFICATION_TYPE;
import com.esprit.enums.Role;
import com.esprit.utils.EmailUtil;

@Stateless
@LocalBean
public class StatistiqueService implements IStatistiqueServiceLocal, IStatistiqueServiceRemote {

	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;

	@EJB
	NotificationService ns=new NotificationService();
	@EJB
	PackService ps;

	@Override
	public void addStatistics(Statistics s) {
		em.persist(s);
		System.out.println("Out of addStat" + s.getId());

	}

	// number of **projects** added
	@Override
	public int numProjectsadded(Date debut, Date fin) {
		TypedQuery<JobOffer> q1 = em.createQuery("select j from JobOffer j ", JobOffer.class);
		List<JobOffer> results = q1.getResultList();
		Statistics s = new Statistics();
		s.setEndDate(fin);
		s.setStartDate(debut);
		s.setType("Number of projects added");
		int i = 0;
		for (JobOffer j : results) {
			if (j.getJOdate().after(debut) && j.getJOdate().before(fin))
				i++;
		}

		s.setValue(i);
		addStatistics(s);
		return i;
	}

	// number of **enterprises** added
	@Override
	public List<User> entreprisesAdded(Date debut, Date fin) {
		TypedQuery<User> query = em.createQuery("SELECT e FROM User e", User.class);
		List<User> allUser = query.getResultList();
		List<User> entreprise = new ArrayList<User>();
		for (User e : allUser) {
			if (!(e.getRole().equals(Role.Candidate)) && !(e.getRole().equals(Role.Admin))) {
				if (e.getAccountCreationDate().after(debut) && e.getAccountCreationDate().before(fin)) {
					entreprise.add(e);

				}
			}
		}
		return entreprise;

	}

	@Override
	public int numEntreprisesAdded(Date debut, Date fin) {
		int nbr = entreprisesAdded(debut, fin).size();
		Statistics s = new Statistics();
		s.setEndDate(fin);
		s.setStartDate(debut);
		s.setType("number of enterprises added ");
		s.setValue(nbr);

		addStatistics(s);
		return nbr;
	}

	// number of **candidated** added
	@Override
	public List<User> condidatsAdded(Date start, Date end) {
		TypedQuery<User> query = em.createQuery("SELECT e FROM User e", User.class);
		List<User> allUser = query.getResultList();
		List<User> entreprise = new ArrayList<User>();

		for (User e : allUser) {
			System.out.println("**************************" + e.getRole());
			if (e.getRole().equals(Role.Candidate)) {
				if (e.getAccountCreationDate().after(start) && e.getAccountCreationDate().before(end)) {
					entreprise.add(e);

				}

			}
		}
		return entreprise;

	}

	@Override
	public int numCandidateAdded(Date start, Date end) {
		int nbr = condidatsAdded(start, end).size();
		Statistics s = new Statistics();
		s.setEndDate(end);
		s.setStartDate(start);
		s.setType("number of candidates added");
		s.setValue(nbr);

		addStatistics(s);
		return nbr;
	}

	// recruted candidate per enterprise
	@Override
	public List<Candidate> statistiqueRecrutement(int e, Date start, Date end) {
		List<Candidate> candidat = new ArrayList<Candidate>();
		Enterprise e1 = em.find(Enterprise.class, e);
		TypedQuery<JobOffer> query = em.createQuery("SELECT o FROM JobOffer o WHERE o.enterprise= :id", JobOffer.class);
		query.setParameter("id", e1);
		List<JobOffer> results = query.getResultList();
		for (JobOffer jo : results) {
			if (jo.getJOdate().after(start) && jo.getJOdate().before(end)) {
				Set<Quiz> q = jo.getQuizs();
				for (Quiz q1 : q) {
					if (q1.getInterview() != null) {
						if (q1.getInterview().getState().equals(InterviewState.Accepted)) {
							candidat.add(q1.getCandidate());
						}
					}
				}
			}
		}
		return candidat;

	}

	@Override
	public int recrutedCandidatPerEnterprise(int e, Date start, Date end) {

		Statistics s = new Statistics();
		s.setEndDate(end);
		s.setStartDate(start);
		s.setType("number of candidates recruted per enterprise");
		s.setValue(statistiqueRecrutement(e, start, end).size());

		addStatistics(s);
		return statistiqueRecrutement(e, start, end).size();
	}

	@Override
	public List<Candidate> statistiqueRecrutementTot(Date start, Date end) {
		List<Candidate> candidat = new ArrayList<Candidate>();
		TypedQuery<JobOffer> query = em.createQuery("SELECT o FROM JobOffer o", JobOffer.class);

		List<JobOffer> results = query.getResultList();
		for (JobOffer jo : results) {
			if (jo.getJOdate().after(start) && jo.getJOdate().before(end)) {
				Set<Quiz> q = jo.getQuizs();
				for (Quiz q1 : q) {
					if (q1.getInterview() != null) {
						if (q1.getInterview().getState().equals(InterviewState.Accepted)) {
							candidat.add(q1.getCandidate());
						}
					}
				}
			}
		}
		return candidat;

	}

	// **recruted candidate**
	@Override
	public int recrutedCandidat(Date start, Date end) {
		TypedQuery<Enterprise> query = em.createQuery("SELECT e FROM Enterprise e", Enterprise.class);
		List<Enterprise> results = query.getResultList();

		int numTotal = 0;
		for (Enterprise a : results) {
			int nbr = statistiqueRecrutementTot(start, end).size();
			numTotal += nbr;

		}
		Statistics s = new Statistics();
		s.setEndDate(end);
		s.setStartDate(start);
		s.setType("number of candidates recruted added");
		s.setValue(numTotal);

		addStatistics(s);
		return numTotal;
	}

	@Override
	public List<String> statistiqueRecrutement(Date start, Date end) {
		TypedQuery<Enterprise> query = em.createQuery("SELECT e FROM Enterprise e", Enterprise.class);
		List<Enterprise> results = query.getResultList();
		List<String> enterprises = new ArrayList<String>();
		int numTotal = 0;
		for (Enterprise a : results) {
			int nbr = statistiqueRecrutement(a.getEid(), start, end).size();
			numTotal += nbr;

		}
		for (Enterprise z : results) {
			int nbr1 = statistiqueRecrutement(z.getEid(), start, end).size();
			double percentage = nbr1 / numTotal;
			enterprises.add("Enterprise : " + z.getEname() + " , recruted : " + nbr1 + " , represent : "
					+ percentage * 100 + "% of the recruted candidats ");
		}

		return enterprises;
	}

	@Override
	public int nbreProjetAdded(int e, Date debut, Date fin) {
		Enterprise e1 = em.find(Enterprise.class, e);
		TypedQuery<JobOffer> query = em.createQuery("SELECT e1 From JobOffre e1 WHERE e1.entreprise= :e",
				JobOffer.class);
		query.setParameter("e", e1);
		List<JobOffer> results = query.getResultList();
		int i = 0;
		for (JobOffer j : results) {
			if (j.getJOdate().after(debut) && j.getJOdate().before(fin))
				i++;
		}

		return i;
	}

	public int numProjectPerEnterprise(int id, Date start, Date end) {
		Enterprise e = em.find(Enterprise.class, id);
		TypedQuery<JobOffer> query = em.createQuery("SELECT o FROM JobOffer o WHERE o.enterprise= :id", JobOffer.class);
		query.setParameter("id", e);
		List<JobOffer> results = query.getResultList();
		int i = 0;
		for (JobOffer j : results) {
			if (j.getJOdate().after(start) && j.getJOdate().before(end))
				i++;
		}
		return i;

	}

	@SuppressWarnings("deprecation")
	public Enterprise enterpriseMostActif(Date start, Date end) {
		TypedQuery<Enterprise> query = em.createQuery("SELECT o FROM Enterprise o ", Enterprise.class);

		List<Enterprise> results = query.getResultList();
		int i = 0;
		int j = 0;
		Enterprise l = new Enterprise();
		for (Enterprise e : results) {

			j = numProjectPerEnterprise(e.getEid(), start, end);
			if (j > i) {
				i = j;

			}

		}
		for (Enterprise e1 : results) {
			int k = numProjectPerEnterprise(e1.getEid(), start, end);
			if (k == j) {
				l = e1;
			}
		}
		Statistics s = new Statistics();
		s.setEndDate(end);
		s.setStartDate(start);
		s.setType("Most actif enterprise" + l.getEname());
		s.setValue(j);
		addStatistics(s);
		TypedQuery<User> query1 = em.createQuery("select u from User u where u.enterprise=:id", User.class);
		query1.setParameter("id", l);
		User user = query1.getSingleResult();
		ns.CreateNotification(user.getId(),"you are the most actif company this month you will receve a 3 months standard pack ", NOTIFICATION_TYPE.Payment, 0);
		TypedQuery<Pack> query2 = em.createQuery("select up from Pack up where up.title= :st", Pack.class);
		query2.setParameter("st", "standard account for 3 months");
		Pack p1 = query2.getSingleResult();
		UserPack up = new UserPack();
		up.setStartDate(Date.valueOf(LocalDate.now()));
		up.setEndDate(new Date(up.getStartDate().getYear(), up.getStartDate().getMonth() + 3, up.getStartDate().getDay()));
		up.setPack(p1);
		up.setUser(user);
		up.setValid(true);
		ps.bonusPack(up);
		return l;
	}

}
