package com.esprit.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.Iservice.IQuizServiceLocal;
import com.esprit.Iservice.IQuizServiceRemote;
import com.esprit.beans.Answer;
import com.esprit.beans.Interview;
import com.esprit.beans.JobOffer;
import com.esprit.beans.Question;
import com.esprit.beans.Quiz;
import com.esprit.beans.QuizState;
import com.esprit.beans.candidate.Candidate;

@Stateless
@LocalBean
public class QuizService implements IQuizServiceLocal, IQuizServiceRemote {
	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;

	@Override
	public void addQuiz(int candidate_id,int jobOffer_id) {
		Candidate c=em.find(Candidate.class, candidate_id);
		JobOffer o=em.find(JobOffer.class, jobOffer_id);
		Quiz qz = new Quiz();
		qz.setCandidate(c);
		qz.setJobOffer(o);
		em.persist(qz);

	}

	@Override
	public boolean setState(int quiz_id) {
		Quiz q = em.find(Quiz.class, quiz_id);
		if (q.getScore() < 5) {
			q.setState(QuizState.Failed);
			return false;
		} else {
			q.setState(QuizState.Validated);
			return true;
		}

	}

	@Override
	public void setScore(int quiz_id, double score) {
		Quiz q = em.find(Quiz.class, quiz_id);
		q.setScore(score);
	}

	@Override
	public void deleteQuiz(int quiz_id) {
		em.remove(em.find(Quiz.class, quiz_id));

	}

	@Override
	public boolean setInterview(int quiz_id) {
		Quiz q = em.find(Quiz.class, quiz_id);
		if (q.getState().equals(QuizState.Validated)) {
			Interview in = new Interview();
			in.setScore(q.getScore());
			in.setQuiz(q);
			em.persist(in);
			return true;
		}
		return false;

	}

	@Override
	public boolean assignQuestionToQuiz(int quiz_id, int question_id) {
		Quiz qz = em.find(Quiz.class, quiz_id);
		Set<Question> list = qz.getQuestions();
		if (list.size() < 10) {
			Question qs = em.find(Question.class, question_id);
			list.add(qs);
			qz.setQuestions(list);
			return true;
		}
		return false;

	}

	@Override
	public Quiz displayQuiz(int quiz_id) {
		Quiz q = em.find(Quiz.class, quiz_id);
		System.out.println(q);
		return q;
	}

	@Override
	public boolean correctAnswer(int answer_id) {
		Answer a=em.find(Answer.class,answer_id);
		if(a.isCorrect())
			return true;
		return false;
	}
	
}
