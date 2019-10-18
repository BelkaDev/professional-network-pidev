package com.esprit.beans;

public class Quiz {
	private int id;
	private double score;
	private QuizState state;

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

	public Quiz(int id, double score, QuizState state) {
		this.id = id;
		this.score = score;
		this.state = state;
	}

	public Quiz() {
	}

}
