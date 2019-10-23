package com.esprit.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
	@Column(name="score")
	private double score;
	@Enumerated(EnumType.STRING)
	private QuizState state;
	@OneToMany(mappedBy = "quiz",fetch=FetchType.EAGER)
	private Set<Question> questions;
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

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	public Quiz(int id, double score) {
		score = 0;
		this.id = id;
		this.score = score;
		this.state =QuizState.Pending;
	}

	public Quiz() {
		this.score=0;
		this.state =QuizState.Pending;
	}

	@Override
	public String toString() {
		return "Quiz [id=" + id + ", score=" + score + ", state=" + state + ", questions=" + questions + ", interview="
				+ interview + "]";
	}

}
