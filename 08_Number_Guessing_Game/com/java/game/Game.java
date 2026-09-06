package com.java.game;

public class Game {

	private int secretNumber;
	private int attempts;
	private int maxAttempts;

	public Game(int secretNumber, int maxAttempts) {

		this.secretNumber = secretNumber;
		this.maxAttempts = maxAttempts;
		this.attempts = 0;
	}

	public int getSecretNumber() {
		return secretNumber;
	}

	public int getAttempts() {
		return attempts;
	}

	public int getMaxAttempts() {
		return maxAttempts;
	}

	public void increaseAttempts() {
		attempts++;
	}
}