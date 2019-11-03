package com.esprit.services;

import java.util.List;
import java.util.Random;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.esprit.Iservice.EventParticipationServiceRemote;

import com.esprit.beans.Enterprise;
import com.esprit.beans.EnterpriseEvent;
import com.esprit.beans.EventParticipation;
import com.esprit.beans.JobOffer;
import com.esprit.beans.candidate.Candidate;
import com.esprit.utils.SendingMail;

@Stateless
@LocalBean
public class EventParticipationService implements EventParticipationServiceRemote {

	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;

	@Override
	public int addParticipation(EventParticipation ep,int eventId, int condidateId) {
		EnterpriseEvent enterpriseeventManagedEntity = em.find(EnterpriseEvent.class, eventId);
		ep.setEnterpriseEvent(enterpriseeventManagedEntity);
		Candidate candidateManagedEntity =em.find(Candidate.class, condidateId);
		ep.setCandidate(candidateManagedEntity);

		if ((enterpriseeventManagedEntity.getEEmaxparticipants()-enterpriseeventManagedEntity.getEEtickets())==0) {
			System.out.print("evenement complet");
		}
		else if (enterpriseeventManagedEntity.getEEtickets()== enterpriseeventManagedEntity.getEEmaxparticipants()/2) {
			
		
		
		
		
		   int leftLimit = 97; // letter 'a'
	        int rightLimit = 122; // letter 'z'
	        int targetStringLength = 9;
	        Random random = new Random();
	        StringBuilder buffer = new StringBuilder(targetStringLength);
	        for (int i = 0; i < targetStringLength; i++) {
	        	int randomLimitedInt = leftLimit + (int) 
	        			(random.nextFloat() * (rightLimit - leftLimit + 1));
	        	buffer.append((char) randomLimitedInt);
	        }
	        String generatedString = buffer.toString();
	        
	        ep.setRidactionCode(generatedString);
	            
	        String contenu =("your participation has been registered  , you can now access the event with the following  ridaction code =" + generatedString);
			SendingMail sm = new SendingMail(contenu, "zied.kouki.1@esprit.tn", "ridaction code");
			SendingMail.envoyer();
	    
		}
		else
			enterpriseeventManagedEntity.setEEtickets(enterpriseeventManagedEntity.getEEtickets()+1);
		
		em.merge(enterpriseeventManagedEntity);
		em.persist(ep);
		
		return ep.getEPid();
		
	}

	

	
	@Override
	public List<EventParticipation> getallParticipation() {
		TypedQuery<EventParticipation> q1 = em.createQuery("select p from EventParticipation p",EventParticipation.class);
		
		return q1.getResultList();
	}
	
	
	
	
	
	
	
}
