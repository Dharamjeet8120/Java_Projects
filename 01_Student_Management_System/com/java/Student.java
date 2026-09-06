package com.java;

public class Student {

	int id;
	String name;
	String course;
	double marks;

	public Student(int id, String name, String course, double marks) {

		this.id = id;
		this.name = name;
		this.course = course;
		this.marks = marks;
	}

	@Override
	public String toString() {

		return id + " | " + name + " | " + course + " | " + marks;
	}
}