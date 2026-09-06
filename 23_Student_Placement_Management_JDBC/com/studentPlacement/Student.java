package com.studentPlacement;

public class Student {

	private int id;
	private String name;
	private String skill;
	private boolean placed;

	public Student() {
	}

	public Student(int id, String name, String skill, boolean placed) {
		this.id = id;
		this.name = name;
		this.skill = skill;
		this.placed = placed;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public boolean isPlaced() {
		return placed;
	}

	public void setPlaced(boolean placed) {
		this.placed = placed;
	}

	@Override
	public String toString() {

		return "ID : " + id + " | Name : " + name + " | Skill : " + skill + " | Placed : " + (placed ? "Yes" : "No");
	}
}