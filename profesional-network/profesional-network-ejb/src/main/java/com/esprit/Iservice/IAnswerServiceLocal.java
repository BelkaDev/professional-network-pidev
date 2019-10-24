package com.esprit.Iservice;

import javax.ejb.Local;

import com.esprit.beans.Answer;

@Local
public interface IAnswerServiceLocal {
	public void addAnswer(String answer) ;
	public void deleteAnswer(int answer_id);
	public void SetCorrectAnswer(int answer_id);
	public void updateAnswer(int answer_id,String answer);
	public Answer displayAnswer(int answer_id);

}
