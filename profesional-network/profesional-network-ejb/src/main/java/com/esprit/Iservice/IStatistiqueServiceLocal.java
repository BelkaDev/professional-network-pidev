package com.esprit.Iservice;

import java.sql.Date;

import javax.ejb.Local;

import com.esprit.beans.Entreprise;
import com.esprit.beans.Statistique;

@Local
public interface IStatistiqueServiceLocal {
	public void ajouterStatistique(Statistique s);
	public void statistiqueCompetenece();
	//Entreprise
	public void nbreEntrepriseAdded(Date debut,Date fin);
	public void statistiqueRecrutement(Entreprise e);
	public void nbreProjetAdded(Entreprise e,Date debut,Date fin);
	public void nbreCondidateRecruted(Entreprise e,Date debut,Date fin);
	//Condidate 
	public void nbreCondidateAdded(Date debut,Date fin);
	public void condidateMostActif(Date debut,Date fin);
}
