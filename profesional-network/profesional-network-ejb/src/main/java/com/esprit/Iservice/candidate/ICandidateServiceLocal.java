package com.esprit.Iservice.candidate;

import java.util.List;
import java.util.Set;

import javax.ejb.Local;

import com.esprit.beans.candidate.Candidate;
import com.esprit.beans.candidate.Experience;
import com.esprit.beans.candidate.Views;

@Local
public interface ICandidateServiceLocal {
	public void addCandidate(Candidate c);
	public Object addProfileObject(Object c,int CandidateID);
	public void addExistingProfileObject(int candidateID, int objectID,Object o);
	public Object deleteProfileObject(int id,Object o,int candidateID);
	public void updateProfileObject(int id,Object o);
	public Object displayProfileObject(int objectID,Object o);
	public Object displayListOfProfileObject(int candidateId,Object o);
	public List<Candidate> displayCandidates();
	public Set<Candidate> displayCandidatesByProfileObject(int objectId,Object o);
	public void addView(int viewerId,int viewedId);
	public Set<Views> displayViews(int candidateId);
	public void deleteView(int viewId );
}
