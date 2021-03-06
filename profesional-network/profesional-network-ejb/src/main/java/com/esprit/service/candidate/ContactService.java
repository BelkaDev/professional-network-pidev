package com.esprit.service.candidate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.Iservice.candidate.IContactServiceLocal;

import com.esprit.Iservice.candidate.IContactServiceRemote;
import com.esprit.beans.Enterprise;
import com.esprit.beans.JobOffer;
import com.esprit.beans.Notification;
import com.esprit.beans.candidate.Activity;
import com.esprit.beans.candidate.Candidate;
import com.esprit.beans.candidate.Certification;
import com.esprit.beans.candidate.Experience;
import com.esprit.beans.candidate.Skill;
//import com.esprit.beans.candidate.Subscription;
import com.esprit.enums.NOTIFICATION_TARGET;
import com.esprit.enums.NOTIFICATION_TYPE;
import com.esprit.services.NotificationService;

@Stateless
@LocalBean
public class ContactService implements IContactServiceLocal, IContactServiceRemote {
	
	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;
	
	@EJB
	NotificationService notificationservice = new NotificationService();
	
	@Override
	public void requestConnection(int senderId, int receiverId) {
		/*Candidate c = em.find(Candidate.class, receiverId);
		Contact con = new Contact();
		con.setContactId(senderId);
		con.setStatus("pending");
		c.getContacts().add(con);*/
		
		
		
		// notify reciever for the request
		// Notify won't work here cause it works on users not candidates
		/*String notif_message = c.getFirstName()+" "+c.getLastName()+" wants to get in contact with you.";
		NOTIFICATION_TYPE type = NOTIFICATION_TYPE.Contact;
		notificationservice.CreateNotification(receiverId,notif_message,type,senderId);*/
	}
	@Override
	public void cancelRequest(int requestId) {
		/*Contact c = em.find(Contact.class, requestId);
		em.remove(c);*/
	
	}
	/*@Override
	public Set<Contact> getRequests(int receiverId) {
		
		return em.find(Candidate.class, receiverId).getContacts();
	}*/
	@Override
	public void acceptRequest(int requestId) {
		/*Contact c = em.find(Contact.class, requestId);
		c.setStatus("accepted");
		Contact c1 = new Contact();
		Candidate ca = em.find(Candidate.class, c.getContactId());
		c1.setContactId(c.getCandidate().getId());
		c1.setStatus("accepted");
		ca.getContacts().add(c1);*/
		
		//notify sender for being accepted
		//String notif_message = "candidate.getFirstName()+  +candidate.getLastName() accepted your contact request.";
		//notificationservice.CreateNotification(c.getSender(),type,notif_message,c.getReciever());
	}
	@Override
	public Set<Candidate> getFriendsList(int candidateId) {
		return em.find(Candidate.class, candidateId).getContacts();
	}
	@Override
	public void blockCandidate(int blocker, int toBlock) {
		/*List<Contact> lc = em.createQuery("select c from Contact c where Candidate_ID="+blocker+" and contact_id="+toBlock, Contact.class).getResultList();
		lc.get(0).setStatus("blocked");*/
	}
	@Override
	public List<Candidate> searchForCandidates(String criteria) {
		List<Candidate> toReturn = new ArrayList<>();
		List<Candidate> lc=em.createQuery("select c from Candidate c where firstName like '%"+criteria+"%' or lastName like '%"+criteria+"%'").getResultList();
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
	@Override
	public List<Enterprise> searchForEnterprise(String criteria) {
		return em.createQuery("select e from ENTERPRISE e where ENT_NAME like '%"+criteria+"%'").getResultList();
	}
	@Override
	public List<JobOffer> getOffersByEnterprise(int enterpriseId) {
		return em.createQuery("select j from JobOffer j where enterprise_ENT_ID = "+enterpriseId).getResultList();
	}
	@Override
	public List<Candidate> getContactsInEnterprise(int candidateId, int enterpriseId) {
		List<Candidate> candidateIdList = em.createQuery("select s.Candidate from Subscription s where enterpriseId="+enterpriseId).getResultList();
		System.out.println(candidateIdList);
		Set <Candidate> allFriends = this.getFriendsList(candidateId);
		List<Candidate> toReturn = new ArrayList<>();
		for(Candidate c : allFriends)
		{
			for(Candidate i : candidateIdList)
			{
				if(c.getId()==i.getId())
				{
					toReturn.add(c);
				}
			}
		}
		return toReturn;
	}

	
	@Override
	public Candidate followCandidate(int follower, int followed) {
		Candidate c = em.find(Candidate.class, follower);
		Candidate c1=em.find(Candidate.class, followed);
		c.getContacts().add(c1);
		return c;
		
	}
	@Override
	public Candidate unfollowCandidate(int follower, int followed) {
		Candidate c = em.find(Candidate.class, follower);
		Candidate c1=em.find(Candidate.class, followed);
		c.getContacts().remove(c1);
		return c;
	}
	@Override
	public Candidate subscribeToEnterprise(int candidateId, int enterpriseId) {
		Candidate c = em.find(Candidate.class, candidateId);
		Enterprise e = em.find(Enterprise.class, enterpriseId);
		c.getSubscriptions().add(e);
		return c;
	}
	@Override
	public Candidate unsubscribeFromEnterprise(int candidateId, int enterpriseId) {
		Candidate c = em.find(Candidate.class, candidateId);
		Enterprise e = em.find(Enterprise.class, enterpriseId);
		c.getSubscriptions().remove(e);
		return c;
	}
	@Override
	public List<JobOffer> getOffers() {
		return em.createQuery("select j from JobOffer j",JobOffer.class).getResultList();
	}
}


