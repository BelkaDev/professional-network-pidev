package com.esprit.service.candidate;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.Iservice.candidate.ITrainingServiceLocal;
import com.esprit.Iservice.candidate.ITrainingServiceRemote;
import com.esprit.beans.User;
import com.esprit.beans.candidate.Experience;
import com.esprit.beans.candidate.Training;
import com.esprit.services.PaymentService;

@Stateless
@LocalBean
public class TrainingService implements ITrainingServiceLocal,ITrainingServiceRemote {

	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;
	@EJB
	PaymentService cs;
	@Override
	
	public void addTraining(Training t) {
		em.persist(t);
		
	}

	@Override
	public void updateTraining(Training t,int trainingId) {
		Training ti = em.find(Training.class, trainingId);
		ti.setQuestion(t.getQuestion());
		ti.setAnswerURL(t.getAnswerURL());
	}

	@Override
	public void deteleTraining(int trainingId) {
		em.remove(em.find(Training.class, trainingId));
	}

	@Override
	public List<Training> displayTraining(int candidateId) {
		if(cs.isPremium(candidateId))
		return em.createQuery("select t from Training t").getResultList();
		return null;
	}
	
	
}
