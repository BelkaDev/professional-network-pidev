package com.esprit.Iservice;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.Local;

import com.esprit.beans.candidate.Candidate;
import com.esprit.beans.candidate.Experience;
import com.esprit.beans.JobOffer;
import com.esprit.beans.Quiz;
import com.esprit.beans.QuizState;

@Local
public interface IQuizServiceLocal {
	public void addQuiz(int candidate_id, int jobOffer_id);

	public boolean setState(int quiz_id);

	public void setScore(int quiz_id, double score);

	public void deleteQuiz(int quiz_id);

	public boolean setInterview(int quiz_id);

	public boolean assignQuestionToQuiz(int quiz_id, int question_id);

	public Quiz displayQuiz(int quiz_id);

	public boolean correctAnswer(int answer_id);

	public List<Quiz> getCandidateForOffer(int offer_id);

	public void ChooseCnadidate(int offer_id);

	public boolean CorrectQuiz(int quiz_id, List<Integer> answersList);

	public boolean checkCandidateDate(int candidate_id, Date d);

	public List<Quiz> getCandidateQuiz(int candidate_id);

	public int getYears(Date datedebut, Date dateFin);

	public Set<Experience> getCandidateExperience(int candidate_id);

	public int getYearsExpericence(int candidate_id);

	public List<JobOffer> selectOffers(int years);

	public List<JobOffer> sendOffers(int quiz_id);

}
