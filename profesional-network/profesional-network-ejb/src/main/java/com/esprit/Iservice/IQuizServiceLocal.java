package com.esprit.Iservice;

import javax.ejb.Local;

import com.esprit.beans.Quiz;
import com.esprit.beans.QuizState;

@Local
public interface IQuizServiceLocal {
	public void addQuiz();
	public boolean setState(int quiz_id);
	public void setScore(int quiz_id,double score);
	public void deleteQuiz(int quiz_id);
	public boolean setInterview(int quiz_id);
	public void assignQuestionToQuiz(int quiz_id,int question_id);
	public Quiz displayQuiz(int quiz_id);
}
