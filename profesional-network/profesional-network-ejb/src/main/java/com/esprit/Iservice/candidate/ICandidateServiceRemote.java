package com.esprit.Iservice.candidate;

import java.util.Set;

import javax.ejb.Remote;

import com.esprit.beans.candidate.Candidate;
import com.esprit.beans.candidate.Experience;

@Remote
public interface ICandidateServiceRemote {
	public void addCandidate(Candidate c);
	public void addProfileObject(Object c,int CandidateID);
	public void addExistingProfileObject(int candidateID, int objectID,Object o);
	public void deleteProfileObject(int id,Object o,int candidateID);
	public void updateProfileObject(int id,Object o);
	public Object displayProfileObject(int objectID,Object o);
	public Set<Candidate> displayCandidatesByProfileObject(int objectId,Object o);
	
}
