package com.esprit.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.esprit.Iservice.IQuestionServiceLocal;
import com.esprit.Iservice.IQuestionServiceRemote;
import com.esprit.beans.Answer;

@Stateless
@LocalBean
public class QuestionService implements IQuestionServiceLocal,IQuestionServiceRemote {

	@Override
	public void addQuestion(String question) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteQuestion(int question_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateQuestion(int question_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void assignResponseToQuestion(int question_id, Answer a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DisplayQuestion(int question_id) {
		// TODO Auto-generated method stub
		
	}

}
