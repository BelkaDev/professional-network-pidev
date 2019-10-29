package com.esprit.Iservice.candidate;

import java.util.Set;

import javax.ejb.Remote;

import com.esprit.beans.candidate.Candidate;
import com.esprit.beans.candidate.Experience;

@Remote
public interface ICandidateServiceRemote {
	public void addCandidate();
	public void addExperience(Experience c);
	public void deleteExperience(int id);
	public Experience displayExperience(int id);
	public void updateExperience(int id,String d);
	public Set<Experience> displayCandidatesByExperience(int id);
}
