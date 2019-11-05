package com.esprit.Iservice;

import java.sql.Date;

import javax.ejb.Local;

import com.esprit.beans.Enterprise;

import com.esprit.beans.Statistics;
import com.esprit.beans.User;
import com.esprit.beans.candidate.Candidate;

import java.util.List;

@Local
public interface IStatistiqueServiceLocal {
	public void addStatistics(Statistics s) ;
	public int numProjectsadded(Date debut, Date fin);
	public List<User> entreprisesAdded(Date debut, Date fin);
	public int numEntreprisesAdded(Date debut, Date fin);
	public List<User> condidatsAdded(Date start, Date end);
	public int numCandidateAdded(Date start, Date end);
	public List<Candidate> statistiqueRecrutement(int e, Date start, Date end);
	public int recrutedCandidat(Date start,Date end);
	public List<String> statistiqueRecrutement(Date start,Date end);
	public int nbreProjetAdded(int e, Date debut, Date fin) ;
	public int recrutedCandidatPerEnterprise(int e,Date start, Date end);
	
}
