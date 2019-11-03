package com.esprit.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.esprit.Iservice.IQuizServiceLocal;
import com.esprit.Iservice.IQuizServiceRemote;
import com.esprit.beans.Answer;
import com.esprit.beans.Interview;
import com.esprit.beans.JobOffer;
import com.esprit.beans.Question;
import com.esprit.beans.Quiz;
import com.esprit.beans.QuizState;
import com.esprit.beans.candidate.Candidate;
import com.esprit.beans.candidate.Experience;

@Stateless
@LocalBean
public class QuizService implements IQuizServiceLocal, IQuizServiceRemote {
	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;

	@Override
	public void addQuiz(int candidate_id, int jobOffer_id) {
		Candidate c = em.find(Candidate.class, candidate_id);
		JobOffer o = em.find(JobOffer.class, jobOffer_id);
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
		if (q.getInterview() == null) {
			if (q.getState() == QuizState.Validated) {
				Interview in = new Interview();
				in.setScore(q.getScore());
				q.setInterview(in);
				em.persist(in);
				return true;
			}
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
		Answer a = em.find(Answer.class, answer_id);
		if (a.isCorrect())
			return true;
		return false;
	}

	@Override
	public List<Quiz> getCandidateForOffer(int offer_id) {
		JobOffer o = em.find(JobOffer.class, offer_id);
		Query q = em.createQuery("SELECT quiz FROM Quiz quiz where quiz.jobOffer = :jobOffer ORDER BY quiz.score DESC");
		q.setParameter("jobOffer", o);
		return (List<Quiz>) q.getResultList();
	}

	@Override
	public void ChooseCnadidate(int offer_id) {
		List<Quiz> list = getCandidateForOffer(offer_id);
		for (Quiz q : list) {
			setState(q.getId());
		}
	}

	@Override
	public boolean CorrectQuiz(int quiz_id, List<Integer> answersList) {
		double s = 0;
		Quiz q = em.find(Quiz.class, quiz_id);
		if (q.getScore() == 0) {
			for (int a : answersList) {
				if (correctAnswer(a))
					s++;
			}
			setScore(quiz_id, s);
			return true;
		}
		return false;
	}

	@Override
	public boolean checkCandidateDate(int candidate_id, Date d) {
		List<Quiz> list = getCandidateQuiz(candidate_id);
		for (Quiz q : list) {
			if (q.getInterview().getDate().equals(d))
				return true;
		}
		return false;
	}

	@Override
	public List<Quiz> getCandidateQuiz(int candidate_id) {
		Candidate c = em.find(Candidate.class, candidate_id);
		Query q = em.createQuery("SELECT quiz FROM Quiz quiz where quiz.candidate = :candidate");
		q.setParameter("candidate", c);
		return (List<Quiz>) q.getResultList();
	}

	@Override
	public int getYears(Date datedebut, Date dateFin) {
		int start = datedebut.toLocalDate().getYear();
		int end = dateFin.toLocalDate().getYear();
		System.out.println(end-start);
		return end - start;
	}

	@Override
	public Set<Experience> getCandidateExperience(int candidate_id) {

		Candidate c = em.find(Candidate.class, candidate_id);
		return c.getExperiences();
	}

	@Override
	public int getYearsExpericence(int candidate_id) {
		int s = 0;
		Set<Experience> list = getCandidateExperience(candidate_id);
		for (Experience e : list) {
			s = getYears(e.getStartDate(), e.getEndDate());
		}
		System.out.println(s);
		return s;
	}

	@Override
	public List<JobOffer> selectOffers(int candidate_id) {
		int years=getYearsExpericence(candidate_id);
		Query q = em.createQuery("select j from JobOffer j where j.JOexperience <= :years").setParameter("years", years);
		System.out.println(years);
		return (List<JobOffer>) q.getResultList();
	}
	public List<JobOffer> sendOffers(int quiz_id) {
		Quiz q=em.find(Quiz.class, quiz_id);
		if(q.getState()==QuizState.Failed)
			return selectOffers(q.getCandidate().getCandidateId());
		return null;
	}

}
