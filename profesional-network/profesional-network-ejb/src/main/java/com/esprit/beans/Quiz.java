package com.esprit.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.esprit.beans.candidate.Candidate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity()
public class Quiz implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "quiz_id")
	private int id;
	@Column(name = "score")
	private double score;
	@Enumerated(EnumType.STRING)
	private QuizState state;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Question> questions;
	@OneToOne
	private Interview interview;
	@ManyToOne
	@JoinColumn(name = "idCnadidate", referencedColumnName = "ID", updatable = false)
	private Candidate candidate;
	@ManyToOne
	@JoinColumn(name = "idOffer", referencedColumnName = "JO_ID", updatable = false)
	private JobOffer jobOffer;

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
		this.state = QuizState.Pending;
	}

	public Quiz() {
		this.score = 0;
		this.state = QuizState.Pending;
	}

	@Override
	public String toString() {
		return "Quiz [id=" + id + ", score=" + score + ", state=" + state + ", questions=" + questions + ", interview="
				+ interview + "]";
	}

	public Interview getInterview() {
		return interview;
	}

	public void setInterview(Interview interview) {
		this.interview = interview;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public JobOffer getJobOffer() {
		return jobOffer;
	}

	public void setJobOffer(JobOffer jobOffer) {
		this.jobOffer = jobOffer;
	}

}
