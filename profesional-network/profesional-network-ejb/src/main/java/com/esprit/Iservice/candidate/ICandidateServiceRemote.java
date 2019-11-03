package com.esprit.Iservice.candidate;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import com.esprit.beans.candidate.Candidate;
import com.esprit.beans.candidate.Experience;
import com.esprit.beans.candidate.Views;

@Remote
public interface ICandidateServiceRemote {
	public void addCandidate(Candidate c);
	public void addProfileObject(Object c,int CandidateID);
	public void addExistingProfileObject(int candidateID, int objectID,Object o);
	public void deleteProfileObject(int id,Object o,int candidateID);
	public void updateProfileObject(int id,Object o);
	public Object displayProfileObject(int objectID,Object o);
	public List<Candidate> displayCandidates();
	public Set<Candidate> displayCandidatesByProfileObject(int objectId,Object o);
	public void addView(int viewerId,int viewedId);
	public Set<Views> displayViews(int CandidateId);
	public void deleteView(int viewId);
	
}
