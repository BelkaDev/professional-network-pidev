package com.esprit.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity()
public class Quiz implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "quiz_id")
	private int id;
	private double score;
	private QuizState state;
	@OneToMany(mappedBy = "quiz")
	private List<Question> questions;
	@OneToOne()
	private Interview interview;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public QuizState getState() {
		return state;
	}

	public void setState(QuizState state) {
		this.state = state;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Quiz(int id, double score, QuizState state) {
		score = 0;
		this.id = id;
		this.score = score;
		this.state = state;
	}

	public Quiz() {
	}

}
