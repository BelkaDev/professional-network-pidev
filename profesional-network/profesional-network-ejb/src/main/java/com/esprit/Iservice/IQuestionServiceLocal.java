package com.esprit.Iservice;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.esprit.beans.Answer;
import com.esprit.beans.Question;

@Local
public interface IQuestionServiceLocal {
	public void addQuestion(String question);
	public void deleteQuestion(int question_id);
	public void updateQuestion(int question_id,String question);
	public void assignResponseToQuestion(int question_id,int answer_id);
	public Question DisplayQuestion(int question_id);
	public List<Question> getQuestions();

}
