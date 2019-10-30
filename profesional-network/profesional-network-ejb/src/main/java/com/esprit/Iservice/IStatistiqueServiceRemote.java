package com.esprit.Iservice;

import java.sql.Date;

import javax.ejb.Remote;

import com.esprit.beans.Enterprise;
import com.esprit.beans.Statistique;

@Remote
public interface IStatistiqueServiceRemote {
	public void ajouterStatistique(Statistique s);
	public void statistiqueCompetenece();

	
	//Enterprise
	public void nbreEnterpriseAdded(Date debut,Date fin);
	public void statistiqueRecrutement(Enterprise e);
	public void nbreProjetAdded(Enterprise e,Date debut,Date fin);
	public void nbreCondidateRecruted(Enterprise e,Date debut,Date fin);
	

	//Condidate 
	public void nbreCondidateAdded(Date debut,Date fin);
	public void condidateMostActif(Date debut,Date fin);
	
	
}
