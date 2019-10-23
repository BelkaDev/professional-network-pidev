package com.esprit.beans;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity()
public class Interview implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "interview_id")
	private int id;
	@Column(name = "date")
	private Date date;
	@Column(name = "time")
	private Time time;
	@Column(name = "score")
	private double score;
	@Enumerated(EnumType.STRING)
	private InterviewState state;
	@OneToOne(mappedBy = "interview")
	private Quiz quiz;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public InterviewState getState() {
		return state;
	}

	public void setState(InterviewState state) {
		this.state = state;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public Interview() {
		this.state=InterviewState.Undefined;
		this.score=0;
	}

	public Interview(int id, Date date, Time time, double score) {
		this.id = id;
		this.date = date;
		this.time = time;
		this.score = score;
		this.state = InterviewState.Undefined;
	}

}
