package com.TicGame;

public class Player {

	private String name;
	private char symbol;
	private int wins;

	public Player(String name, char symbol) {
		this.name = name;
		this.symbol = symbol;
		this.wins = 0;
	}

	public String getName() {
		return name;
	}

	public char getSymbol() {
		return symbol;
	}

	public int getWins() {
		return wins;
	}

	public void increaseWin() {
		wins++;
	}
}