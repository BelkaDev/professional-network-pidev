package com.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.esprit.Iservice.IAnswerServiceLocal;
import com.esprit.Iservice.IAnswerServiceRemote;
import com.esprit.beans.Answer;
@Stateless
@LocalBean
public class AnswerService implements IAnswerServiceLocal,IAnswerServiceRemote {
	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;
	@Override
	public void addAnswer(String answer) {
		Answer a=new Answer();
		a.setAnswer(answer);
		em.persist(a);
		
	}

	@Override
	public void deleteAnswer(int answer_id) {
		em.remove(em.find(Answer.class, answer_id));
		System.out.println("deleted successfully");
		
	}

	@Override
	public void SetCorrectAnswer(int answer_id) {
		Answer a=em.find(Answer.class, answer_id);
		a.setCorrect(true);
		
	}

	@Override
	public void updateAnswer(int answer_id,String answer) {
		Answer a=em.find(Answer.class, answer_id);
		a.setAnswer(answer);
		
	}
	public Answer displayAnswer(int answer_id) {
		Answer a=em.find(Answer.class, answer_id);
		return a;
	}

	@Override
	public List<Answer> getAnswers() {
		Query q = em.createQuery("select a from Answer a");
		return q.getResultList();
	}

}
