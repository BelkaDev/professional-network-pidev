package com.esprit.service.candidate;

import java.sql.Date;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.Iservice.candidate.ICandidateServiceLocal;
import com.esprit.Iservice.candidate.ICandidateServiceRemote;
import com.esprit.beans.candidate.Activity;
import com.esprit.beans.candidate.Candidate;
import com.esprit.beans.candidate.Certification;
import com.esprit.beans.candidate.Experience;
import com.esprit.beans.candidate.Skill;
import com.esprit.beans.candidate.Views;

@Stateless
@LocalBean
public class CandidateService implements ICandidateServiceLocal, ICandidateServiceRemote {

	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;

	@Override
	public void addCandidate(Candidate c) {
		em.persist(c);
	}

	@Override
	public Object addProfileObject(Object o, int CandidateID) {
		Candidate c = em.find(Candidate.class, CandidateID);
		if (o.getClass() == Experience.class) {
			Experience e = (Experience) o;
			c.getExperiences().add(e);
			return e;
		} else if (o.getClass() == Skill.class) {
			Skill s = (Skill) o;
			c.getSkills().add(s);
			return s;
		} else if (o.getClass() == Certification.class) {
			Certification cert = (Certification) o;
			c.getCertifications().add(cert);
			return cert;
		} else if (o.getClass() == Activity.class) {
			Activity a = (Activity) o;
			c.getActivities().add(a);
			return a;
		}
		return null;

	}

	@Override
	public void addExistingProfileObject(int candidateID, int objectID, Object o) {

		Candidate c = em.find(Candidate.class, candidateID);
		if (o.getClass() == Experience.class) {
			Experience e = em.find(Experience.class, objectID);
			c.getExperiences().add(e);
		} else if (o.getClass() == Skill.class) {
			Skill s = em.find(Skill.class, objectID);
			c.getSkills().add(s);
		} else if (o.getClass() == Certification.class) {
			Certification cert = em.find(Certification.class, objectID);
			c.getCertifications().add(cert);
		} else if (o.getClass() == Activity.class) {
			Activity a = em.find(Activity.class, objectID);
			c.getActivities().add(a);
		}

	}

	@Override
	public Object deleteProfileObject(int id, Object o, int candidateID) {
		Candidate c = em.find(Candidate.class, candidateID);
		if (o.getClass() == Experience.class) {
			Experience e = em.find(Experience.class, id);
			c.getExperiences().remove(e);
			return e;
		} else if (o.getClass() == Skill.class) {
			Skill s = em.find(Skill.class, id);
			c.getSkills().remove(s);
			return s;
		} else if (o.getClass() == Certification.class) {
			Certification cert = em.find(Certification.class, id);
			c.getCertifications().remove(cert);
			return cert;
		} else if (o.getClass() == Activity.class) {
			Activity a = em.find(Activity.class, id);
			c.getActivities().remove(a);
			return a;
		}
		return null;
	}

	@Override
	public void updateProfileObject(int objectID, Object o) {

		if (o.getClass() == Experience.class) {
			Experience e = em.find(Experience.class, objectID);
			Experience n = (Experience) o;
			e.setDesignation(n.getDesignation());
			e.setStartDate(n.getStartDate());
			e.setEndDate(n.getEndDate());
			e.setType(n.getType());

		} else if (o.getClass() == Skill.class) {
			Skill s = em.find(Skill.class, objectID);
			Skill n = (Skill) o;
			s.setDesignation(n.getDesignation());
			s.setRating(n.getRating());

		} else if (o.getClass() == Certification.class) {
			Certification cert = em.find(Certification.class, objectID);
			Certification n = (Certification) o;
			cert.setDesignation(n.getDesignation());
			cert.setCompanyName(n.getCompanyName());
			cert.setIssueDate(n.getIssueDate());
			cert.setExpiryDate(n.getExpiryDate());
			cert.setCredentialId(n.getCredentialId());

		} else if (o.getClass() == Activity.class) {
			Activity a = em.find(Activity.class, objectID);
			Activity n = (Activity) o;
			a.setDesignation(n.getDesignation());
			a.setDate(n.getDate());
		}
	}

	@Override
	public Object displayProfileObject(int objectID, Object o) {
		if (o.getClass() == Experience.class) {
			Experience e = em.find(Experience.class, objectID);
			return e;
		} else if (o.getClass() == Skill.class) {
			Skill s = em.find(Skill.class, objectID);
			return s;
		} else if (o.getClass() == Certification.class) {
			Certification cert = em.find(Certification.class, objectID);
			return cert;
		}
		Activity a = em.find(Activity.class, objectID);
		return a;
	}

	@Override
	public Set<Candidate> displayCandidatesByProfileObject(int objectID, Object o) {

		if (o.getClass() == Experience.class) {
			Experience e = em.find(Experience.class, objectID);
			return e.getCandidates().stream().collect(Collectors.toSet());
		} else if (o.getClass() == Skill.class) {
			Skill s = em.find(Skill.class, objectID);
			return s.getCandidates().stream().collect(Collectors.toSet());
		} else if (o.getClass() == Certification.class) {
			Certification cert = em.find(Certification.class, objectID);
			return cert.getCandidates().stream().collect(Collectors.toSet());
		}
		Activity a = em.find(Activity.class, objectID);
		return a.getCandidates().stream().collect(Collectors.toSet());
	}

	@Override
	public List<Candidate> displayCandidates(int id) {
		return em.createQuery("select c from Candidate c where not id = "+id,Candidate.class).getResultList();
	}



	@Override
	public Set<Views> displayViews(int candidateId) {
			Candidate c = em.find(Candidate.class, candidateId);
			return c.getViews();
	}

	@Override
	public void deleteView(int viewId) {
		Views v = em.find(Views.class, viewId);
		em.remove(v);
	}

	@Override
	public void addView(int viewerId, int viewedId) {
		Candidate c = em.find(Candidate.class, viewedId);
		Views v = new Views();
		v.setViewerId(viewerId);
		Date d = new Date(System.currentTimeMillis());
		v.setDate(d);
		c.getViews().add(v);
	}

	@Override
	public Object displayListOfProfileObject(int candidateId, Object o) {
		Candidate c = em.find(Candidate.class, candidateId);
		if (o.getClass() == Experience.class) {
			return c.getExperiences();
		} else if (o.getClass() == Skill.class) {
			return c.getSkills();
		} else if (o.getClass() == Certification.class) {
			return c.getCertifications();
		}
		return c.getActivities();
	}

	@Override
	public Candidate updateBasicCandidate(int candidateId, Candidate c) {
		Candidate old = em.find(Candidate.class, candidateId);
		old.setFirstName(c.getFirstName());
		old.setLastName(c.getLastName());
		old.setTitle(c.getTitle());
		old.setBiography(c.getBiography());
		return old;
	}

	@Override
	public Candidate getCandidateById(int candidateId) {
		return em.find(Candidate.class, candidateId);
	}

	

}
