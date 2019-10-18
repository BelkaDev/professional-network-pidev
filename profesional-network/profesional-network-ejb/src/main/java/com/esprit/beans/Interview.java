package com.esprit.beans;

import java.sql.Date;
import java.sql.Time;

public class Interview {
	private int id;
	private Date date;
	private Time time;
	private double score;
	private InterviewState state;

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

	public Interview() {
	}

	public Interview(int id, Date date, Time time, double score, InterviewState state) {
		this.id = id;
		this.date = date;
		this.time = time;
		this.score = score;
		this.state = state;
	}

}
