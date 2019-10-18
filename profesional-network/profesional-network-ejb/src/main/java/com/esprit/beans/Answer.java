package com.esprit.beans;

public class Answer {
	private int id;
	private String answer;
	private boolean isCorrect;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public Answer(int id, String answer, boolean isCorrect) {
		this.id = id;
		this.answer = answer;
		this.isCorrect = isCorrect;
	}

	public Answer() {
	}

}
