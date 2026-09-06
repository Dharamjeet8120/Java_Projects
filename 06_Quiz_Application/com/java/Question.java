package com.java;

public class Question {

	private String question;
	private String optionA;
	private String optionB;
	private String optionC;
	private String answer;

	public Question(String question, String optionA, String optionB, String optionC, String answer) {

		this.question = question;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.answer = answer;
	}

	public String getQuestion() {
		return question;
	}

	public String getOptionA() {
		return optionA;
	}

	public String getOptionB() {
		return optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public String getAnswer() {
		return answer;
	}
}