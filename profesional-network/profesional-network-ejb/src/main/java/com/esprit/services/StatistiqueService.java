package com.esprit.services;

import java.sql.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.Iservice.IStatistiqueServiceLocal;
import com.esprit.Iservice.IStatistiqueServiceRemote;
import com.esprit.beans.Entreprise;
import com.esprit.beans.Statistics;

@Stateless
public class StatistiqueService implements IStatistiqueServiceLocal,IStatistiqueServiceRemote {

	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;
	
	@Override
	public void ajouterStatistique(Statistics s) {
		em.persist(s);
		System.out.println("Out of addStat" + s.getId());
		
		
	}

	@Override
	public void statistiqueCompetenece() {
			
	}

	@Override
	public void nbreEntrepriseAdded(Date debut, Date fin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void statistiqueRecrutement(Entreprise e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nbreProjetAdded(Entreprise e, Date debut, Date fin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nbreCondidateRecruted(Entreprise e, Date debut, Date fin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nbreCondidateAdded(Date debut, Date fin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void condidateMostActif(Date debut, Date fin) {
		// TODO Auto-generated method stub
		
	}

}
