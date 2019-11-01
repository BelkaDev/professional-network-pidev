package com.esprit.Iservice;

import java.util.List;

import javax.ejb.Remote;

import com.esprit.beans.EventParticipation;

@Remote
public interface EventParticipationServiceRemote {

	
	public int addParticipation(EventParticipation ep,int eventId, int condidateId);
	public List<EventParticipation> getallParticipation();
}
