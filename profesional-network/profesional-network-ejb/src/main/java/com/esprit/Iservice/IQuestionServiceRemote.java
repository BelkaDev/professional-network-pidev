package com.esprit.Iservice;

import javax.ejb.Remote;

import com.esprit.beans.Answer;

@Remote
public interface IQuestionServiceRemote {
	public void addQuestion(String question);
	public void deleteQuestion(int question_id);
	public void updateQuestion(int question_id);
	public void assignResponseToQuestion(int question_id,Answer a);
	public void DisplayQuestion(int question_id);
	
}
