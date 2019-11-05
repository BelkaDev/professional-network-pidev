package com.esprit.Iservice;

import java.util.List;

import javax.ejb.Remote;

import com.esprit.beans.EventParticipation;

@Remote
public interface EventParticipationServiceRemote {

	
	public int addParticipation(EventParticipation ep,int eventId);
	public List<EventParticipation> getallParticipation();
	public boolean UniquePart(int eventId);
}
