package com.esprit.service.candidate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.Iservice.candidate.IContactServiceLocal;

import com.esprit.Iservice.candidate.IContactServiceRemote;
import com.esprit.beans.candidate.Activity;
import com.esprit.beans.candidate.Candidate;
import com.esprit.beans.candidate.Certification;
import com.esprit.beans.candidate.Contact;
import com.esprit.beans.candidate.Experience;
import com.esprit.beans.candidate.Skill;

@Stateless
@LocalBean
public class ContactService implements IContactServiceLocal, IContactServiceRemote {

	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;
	@Override
	public void requestConnection(int senderId, int receiverId) {
		Candidate c = em.find(Candidate.class, receiverId);
		Contact con = new Contact();
		con.setContactId(senderId);
		con.setStatus("pending");
		c.getContacts().add(con);
	}
	@Override
	public void cancelRequest(int requestId) {
		em.remove(em.find(Contact.class, requestId));
	}
	@Override
	public Set<Contact> getRequests(int receiverId) {
		
		return em.find(Candidate.class, receiverId).getContacts();
	}
	@Override
	public void acceptRequest(int requestId) {
		em.find(Contact.class, requestId).setStatus("accepted");
	}
	@Override
	public Set<Candidate> getFriendsList(int candidateId) {
		Set<Candidate> toReturn = new HashSet<>();
		List<Contact> lc = em.createQuery("select c from Contact c where Candidate_ID="+candidateId+" and status='accepted'", Contact.class).getResultList();
		for(int i=0;i<lc.size();i++)
		{
			toReturn.add(em.find(Candidate.class, lc.get(i).getContactId()));
		}
		return toReturn;
	}
	@Override
	public void blockCandidate(int blocker, int toBlock) {
		List<Contact> lc = em.createQuery("select c from Contact c where Candidate_ID="+blocker+" and contact_id="+toBlock, Contact.class).getResultList();
		lc.get(0).setStatus("blocked");
	}
	@Override
	public List<Candidate> searchForCandidates(String criteria) {
		List<Candidate> toReturn = new ArrayList<>();
		List<Candidate> lc=em.createQuery("select c from Candidate c where firstName like %"+criteria+"% or lastName like %"+criteria+"%").getResultList();
		if(!lc.isEmpty())
		{
			toReturn.addAll(lc);
			
		}
		lc.clear();
		lc=em.createQuery("select c from Candidate c").getResultList();
		for(int i = 0;i<lc.size();i++)
		{
			for(Skill s : lc.get(i).getSkills())
			{
				if(s.getDesignation().equals(criteria))
					toReturn.add(lc.get(i));
			}
			for(Certification s : lc.get(i).getCertifications())
			{
				if(s.getDesignation().equals(criteria))
					toReturn.add(lc.get(i));
			}
			for(Experience s : lc.get(i).getExperiences())
			{
				if(s.getDesignation().equals(criteria))
					toReturn.add(lc.get(i));
			}
			for(Activity s : lc.get(i).getActivities())
			{
				if(s.getDesignation().equals(criteria))
					toReturn.add(lc.get(i));
			}
		}
		
		return toReturn;
	}
	

	
}
