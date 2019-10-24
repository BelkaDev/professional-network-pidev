package com.esprit.Iservice;

import javax.ejb.Remote;

import com.esprit.beans.Answer;
import com.esprit.beans.Quiz;
import com.esprit.beans.QuizState;

@Remote
public interface IQuizServiceRemote {
	public void addQuiz(int candidate_id, int jobOffer_id);

	public boolean setState(int quiz_id);

	public void setScore(int quiz_id, double score);

	public void deleteQuiz(int quiz_id);

	public boolean setInterview(int quiz_id);

	public boolean assignQuestionToQuiz(int quiz_id, int question_id);

	public Quiz displayQuiz(int quiz_id);

	public boolean correctAnswer(int answer_id);

}
