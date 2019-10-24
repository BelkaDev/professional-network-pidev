package com.esprit.Iservice;

import javax.ejb.Remote;

import com.esprit.beans.Answer;

@Remote
public interface IAnswerServiceRemote {
	public void addAnswer(String answer) ;
	public void deleteAnswer(int answer_id);
	public void SetCorrectAnswer(int answer_id);
	public void updateAnswer(int answer_id,String answer);
	public Answer displayAnswer(int answer_id);

}
