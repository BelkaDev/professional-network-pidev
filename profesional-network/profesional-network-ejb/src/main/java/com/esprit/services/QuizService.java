package com.esprit.services;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

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

	public static InterviewService ins = new InterviewService();

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
		System.out.println("TEST TEST 111111111111111111111111111");
		System.out.println(q.getCandidate().getId());
		System.out.println(q.getJobOffer().getJOid());
		if (q.getInterview() == null) {
			if (q.getState() == QuizState.Validated) {
				Interview in = new Interview();
				String da = generateDate();
				while (!(isThisDateValid(da, "yyyy-mm-dd")) || !(ins.isWeekend(da)) || !(ins.isOlderThanToday(da))

						|| !checkCandidateDate(q.getCandidate().getId(), Date.valueOf(da))
						|| !checkHRDate(q.getJobOffer().getJOid(), Date.valueOf(da))) {
					da = generateDate();
				}
				in.setDate(Date.valueOf(da));
				String ti = generateTime();
				while (!checkCandidateTime(q.getCandidate().getId(), Time.valueOf(ti))
						|| !checkRHTime(q.getJobOffer().getJOid(), Time.valueOf(ti))) {
					ti=generateTime();
				}
				in.setTime(Time.valueOf(ti));
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
	public Set<Quiz> getCandidateForOffer(int offer_id) {
		JobOffer o = em.find(JobOffer.class, offer_id);
		return o.getQuizs();
	}

	@Override
	public void ChooseCnadidate(int offer_id) {
		Set<Quiz> list = getCandidateForOffer(offer_id);
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
		Set<Quiz> list = getCandidateQuiz(candidate_id);
		if (list.isEmpty())
			return true;
		for (Quiz q : list) {
			if (q.getInterview() != null) {
				if (q.getInterview().getDate().equals(d))
					return false;
			}
		}
		return true;
	}

	@Override
	public Set<Quiz> getCandidateQuiz(int candidate_id) {
		System.out.println("heyeheyehyehyehyehyehyehyehyeheyheyheyheyh" + candidate_id);
		Candidate c = em.find(Candidate.class, candidate_id);
		System.out.println("TESTSTSTSTST" + c.getBiography());
		return c.getQuizs();
	}

	@Override
	public int getYears(Date datedebut, Date dateFin) {
		int start = datedebut.toLocalDate().getYear();
		int end = dateFin.toLocalDate().getYear();
		System.out.println(end - start);
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
		int years = getYearsExpericence(candidate_id);
		Query q = em.createQuery("select j from JobOffer j where j.JOexperience <= :years").setParameter("years",
				years);
		System.out.println(years);
		return (List<JobOffer>) q.getResultList();
	}

	public List<JobOffer> sendOffers(int quiz_id) {
		Quiz q = em.find(Quiz.class, quiz_id);
		if (q.getState() == QuizState.Failed)
			return selectOffers(q.getCandidate().getId());
		return null;
	}

	@Override
	public boolean isThisDateValid(String dateToValidate, String dateFromat) {
		if (dateToValidate == null) {
			return false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);

		try {

			// if not valid, it will throw ParseException
			java.util.Date date = sdf.parse(dateToValidate);
			System.out.println(date);

		} catch (ParseException e) {

			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public String generateDate() {
		int year = ThreadLocalRandom.current().nextInt(Year.now().getValue(), Year.now().getValue() + 2);
		int month = ThreadLocalRandom.current().nextInt(1, 13);
		int day = ThreadLocalRandom.current().nextInt(1, 32);
		if (month < 10 && day < 10) {
			String da = String.valueOf(year) + "-0" + String.valueOf(month) + "-0" + String.valueOf(day);
			return da;
		}
		if (month < 10) {
			String da = String.valueOf(year) + "-0" + String.valueOf(month) + "-" + String.valueOf(day);
			return da;
		}
		if (day < 10) {
			String da = String.valueOf(year) + "-0" + String.valueOf(month) + "-0" + String.valueOf(day);
			return da;
		}
		String da = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day);
		return da;
	}

	@Override
	public boolean checkHRDate(int jobOffer_id, Date d) {
		Set<Quiz> list = getCandidateForOffer(jobOffer_id);
		if (list.isEmpty())
			return true;
		for (Quiz q : list) {
			if (q.getInterview() != null) {
				if (q.getInterview().getDate().equals(d))
					return false;
			}
		}
		return true;
	}

	@Override
	public String generateTime() {
		int hours = ThreadLocalRandom.current().nextInt(9, 19);
		int min = ThreadLocalRandom.current().nextInt(1, 60);
		if (hours < 10 && min < 10) {
			String da = "0" + String.valueOf(hours) + ":0" + String.valueOf(min) + ":00";
			return da;
		}
		if (hours < 10) {
			String da = "0" + String.valueOf(hours) + ":" + String.valueOf(min) + ":00";
			return da;
		}
		if (min < 10) {
			String da = String.valueOf(hours) + ":0" + String.valueOf(min) + ":00";
			return da;
		}
		String da = String.valueOf(hours) + ":" + String.valueOf(min) + ":00";
		return da;
	}

	@Override
	public boolean checkCandidateTime(int candidate_id, Time t) {
		Candidate c = em.find(Candidate.class, candidate_id);
		if (c.getQuizs().isEmpty())
			return true;
		for (Quiz q : c.getQuizs()) {
			if (q.getInterview() != null) {
				if (q.getInterview().getTime().equals(t))
					return false;
			}
		}
		return true;
	}

	@Override
	public boolean checkRHTime(int jobOffer_id, Time t) {
		JobOffer o = em.find(JobOffer.class, jobOffer_id);
		if (o.getQuizs().isEmpty())
			return true;
		for (Quiz q : o.getQuizs()) {
			if (q.getInterview() != null) {
				if (q.getInterview().getTime().equals(t))
					return false;
			}
		}
		return true;
	}

}
