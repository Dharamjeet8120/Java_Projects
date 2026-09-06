package com.onlineExam;

public class Question {

	private int id;
	private String question;
	private String optionA;
	private String optionB;
	private String optionC;
	private char answer;

	public Question() {
	}

	public Question(int id, String question, String optionA, String optionB, String optionC, char answer) {

		this.id = id;
		this.question = question;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.answer = answer;
	}

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

	public String getOptionA() {
		return optionA;
	}

	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}

	public String getOptionB() {
		return optionB;
	}

	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	public char getAnswer() {
		return answer;
	}

	public void setAnswer(char answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {

		return "ID : " + id + " | Question : " + question + " | A : " + optionA + " | B : " + optionB + " | C : "
				+ optionC + " | Answer : " + answer;
	}
}