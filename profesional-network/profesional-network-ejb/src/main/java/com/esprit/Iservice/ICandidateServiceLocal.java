package com.esprit.Iservice;

import javax.ejb.Local;

import com.esprit.beans.Experience;

@Local
public interface ICandidateServiceLocal {
	public void addExperience(Experience c);
	public void deleteExperience(int id);
	public Experience displayExperience(int id);
	public void updateExperience(int id,String d);
}
