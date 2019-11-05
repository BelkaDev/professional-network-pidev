package com.esprit.beans.candidate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name="Training")
public class Training {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	@Column(name="question")
	private String question;
	@Column(name="answerURL")
	private String answerURL;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswerURL() {
		return answerURL;
	}
	public void setAnswerURL(String answerURL) {
		this.answerURL = answerURL;
	}
	
}
