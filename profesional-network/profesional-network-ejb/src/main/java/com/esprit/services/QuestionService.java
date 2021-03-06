package com.esprit.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.esprit.Iservice.IQuestionServiceLocal;
import com.esprit.Iservice.IQuestionServiceRemote;
import com.esprit.beans.Answer;
import com.esprit.beans.Question;
@Stateless
@LocalBean
public class QuestionService implements IQuestionServiceLocal, IQuestionServiceRemote {
	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;

	@Override
	public void addQuestion(String question) {
		Question q = new Question();
		q.setQuestion(question);
		em.persist(q);

	}

	@Override
	public void deleteQuestion(int question_id) {
		em.remove(em.find(Question.class, question_id));
	}

	@Override
	public void updateQuestion(int question_id,String question) {
		Question q=em.find(Question.class, question_id);
		q.setQuestion(question);

	}

	@Override
	public void assignResponseToQuestion(int question_id, int answer_id) {
		Question q=em.find(Question.class, question_id);
		Answer a=em.find(Answer.class, answer_id);
		Set<Answer> list=q.getAnswers();
		list.add(a);
		q.setAnswers(list);
		

	}

	@Override
	public Question DisplayQuestion(int question_id) {
	Question q=	em.find(Question.class, question_id);
	return q;

	}
	@Override
	public List<Question> getQuestions() {
		Query q = em.createQuery("select q from Question q");
	return q.getResultList();

	}

}
