package com.esprit.Iservice.candidate;

import javax.ejb.Local;

import com.esprit.beans.candidate.Experience;

@Local
public interface ICandidateServiceLocal {
	public void addExperience(Experience c);
	public void deleteExperience(int id);
	public Experience displayExperience(int id);
	public void updateExperience(int id,String d);
}
