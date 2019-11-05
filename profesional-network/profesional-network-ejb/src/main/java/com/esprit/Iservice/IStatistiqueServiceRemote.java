package com.esprit.Iservice;

import java.sql.Date;
import java.util.List;

import javax.ejb.Remote;

import com.esprit.beans.Enterprise;
import com.esprit.beans.Statistics;
import com.esprit.beans.User;
import com.esprit.beans.candidate.Candidate;

@Remote
public interface IStatistiqueServiceRemote {
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
	public List<Candidate> statistiqueRecrutementTot( Date start, Date end);
	public int recrutedCandidatPerEnterprise(int e,Date start, Date end);
	
	
}
