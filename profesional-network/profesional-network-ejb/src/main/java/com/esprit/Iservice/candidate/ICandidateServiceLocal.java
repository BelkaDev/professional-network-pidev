package com.esprit.Iservice.candidate;

import java.util.Set;

import javax.ejb.Local;

import com.esprit.beans.candidate.Candidate;
import com.esprit.beans.candidate.Experience;

@Local
public interface ICandidateServiceLocal {
	public void addCandidate(Candidate c);
	public void addExperience(Experience c,int CandidateID);
	public void deleteExperience(int id);
	public Experience displayExperience(int id);
	public Set<Candidate> displayCandidatesByExperience(int id);
	public void updateExperience(int id,String d);
}
