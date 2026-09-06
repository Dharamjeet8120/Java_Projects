package com.java;

import java.util.ArrayList;
import java.util.List;

public class QuizService {

	static List<Question> questions = new ArrayList<>();

	static {

		questions.add(new Question("Java is?", "A. Language", "B. Database", "C. OS", "A"));
		questions.add(new Question("JVM means?", "A. Java Virtual Machine", "B. Java Variable Method", "C. None", "A"));
		questions.add(new Question("Which is Collection?", "A. ArrayList", "B. int", "C. char", "A"));
		questions.add(new Question("Which keyword creates object?", "A. new", "B. class", "C. this", "A"));
		questions.add(new Question("Entry point of Java?", "A. main()", "B. start()", "C. run()", "A"));
		questions.add(new Question("Parent class of all classes?", "A. Object", "B. String", "C. Class", "A"));
		questions.add(new Question("Which is not primitive?", "A. String", "B. int", "C. char", "A"));
		questions.add(new Question("Size of int?", "A. 4", "B. 2", "C. 8", "A"));
		questions.add(new Question("Which loop executes at least once?", "A. do-while", "B. while", "C. for", "A"));
		questions.add(new Question("Java supports?", "A. OOP", "B. POP", "C. None", "A"));

		questions.add(
				new Question("Which package contains Scanner?", "A. java.util", "B. java.io", "C. java.lang", "A"));
		questions.add(new Question("Keyword for inheritance?", "A. extends", "B. implements", "C. inherit", "A"));
		questions.add(new Question("Method overloading occurs in?", "A. Same Class", "B. Different Class", "C. Package",
				"A"));
		questions.add(new Question("Method overriding requires?", "A. Inheritance", "B. Object", "C. Interface", "A"));
		questions.add(new Question("Array index starts from?", "A. 0", "B. 1", "C. -1", "A"));
		questions.add(new Question("String is?", "A. Class", "B. Method", "C. Package", "A"));
		questions.add(new Question("Which exception is unchecked?", "A. ArithmeticException", "B. IOException",
				"C. SQLException", "A"));
		questions.add(new Question("Keyword to handle exception?", "A. try", "B. throw", "C. throws", "A"));
		questions.add(new Question("Which block always executes?", "A. finally", "B. catch", "C. try", "A"));
		questions.add(new Question("Collection used for key-value?", "A. Map", "B. List", "C. Set", "A"));

		questions.add(new Question("Which class implements List?", "A. ArrayList", "B. HashMap", "C. HashSet", "A"));
		questions.add(new Question("Duplicate values not allowed in?", "A. Set", "B. List", "C. Queue", "A"));
		questions.add(new Question("FIFO follows?", "A. Queue", "B. Stack", "C. Set", "A"));
		questions.add(new Question("LIFO follows?", "A. Stack", "B. Queue", "C. List", "A"));
		questions.add(new Question("Java 8 introduced?", "A. Lambda", "B. Servlet", "C. JDBC", "A"));
		questions.add(new Question("Functional Interface contains?", "A. One Abstract Method", "B. Two Methods",
				"C. No Method", "A"));
		questions.add(new Question("Stream API introduced in?", "A. Java 8", "B. Java 6", "C. Java 7", "A"));
		questions.add(new Question("Filter method belongs to?", "A. Stream", "B. List", "C. Array", "A"));
		questions.add(new Question("Default package imported automatically?", "A. java.lang", "B. java.util",
				"C. java.io", "A"));
		questions.add(new Question("Class used for thread creation?", "A. Thread", "B. Runnable", "C. Process", "A"));

		questions.add(new Question("Method to start thread?", "A. start()", "B. run()", "C. begin()", "A"));
		questions.add(new Question("JDBC stands for?", "A. Java Database Connectivity", "B. Java Data Class",
				"C. Java Driver Class", "A"));
		questions.add(new Question("DriverManager belongs to?", "A. JDBC", "B. Servlet", "C. JSP", "A"));
		questions
				.add(new Question("Servlet lifecycle first method?", "A. init()", "B. service()", "C. destroy()", "A"));
		questions.add(new Question("JSP stands for?", "A. Java Server Pages", "B. Java Service Program",
				"C. Java Source Page", "A"));
		questions.add(new Question("Hibernate is?", "A. ORM Framework", "B. Database", "C. Server", "A"));
		questions.add(new Question("JPA stands for?", "A. Java Persistence API", "B. Java Program API",
				"C. Java Package API", "A"));
		questions.add(
				new Question("Spring Boot feature?", "A. Auto Configuration", "B. Manual Config", "C. No Config", "A"));
		questions.add(new Question("REST stands for?", "A. Representational State Transfer", "B. Remote State Transfer",
				"C. Runtime State Transfer", "A"));
		questions.add(new Question("HTTP GET used for?", "A. Read Data", "B. Insert Data", "C. Delete Data", "A"));

		questions.add(new Question("HTTP POST used for?", "A. Create Data", "B. Read Data", "C. Search Data", "A"));
		questions.add(new Question("Spring Bean annotation?", "A. @Component", "B. @BeanFactory", "C. @Object", "A"));
		questions.add(new Question("Dependency Injection is?", "A. IOC", "B. JDBC", "C. JVM", "A"));
		questions.add(new Question("Microservices communicate using?", "A. REST API", "B. JSP", "C. Servlet", "A"));
		questions.add(new Question("SQL command to fetch data?", "A. SELECT", "B. INSERT", "C. UPDATE", "A"));
		questions.add(new Question("Primary Key is?", "A. Unique", "B. Duplicate", "C. Null", "A"));
		questions.add(new Question("Maven file name?", "A. pom.xml", "B. web.xml", "C. app.xml", "A"));
		questions.add(new Question("Tomcat is?", "A. Web Server", "B. Database", "C. IDE", "A"));
		questions.add(
				new Question("Git command to clone repository?", "A. git clone", "B. git pull", "C. git push", "A"));
		questions.add(new Question("Java latest feature category?", "A. Records", "B. Tables", "C. Views", "A"));
	}

	public static void startQuiz() {

		int score = 0;

		for (Question q : questions) {

			IO.println("\n" + q.getQuestion());
			IO.println(q.getOptionA());
			IO.println(q.getOptionB());
			IO.println(q.getOptionC());

			String answer = IO.readln("Enter Answer: ").toUpperCase();

			if (answer.equals(q.getAnswer())) {
				score++;
			}
		}

		IO.println("\n==============================");
		IO.println("Final Score : " + score + "/" + questions.size());

		double percentage = (score * 100.0) / questions.size();

		IO.println("Percentage : " + percentage + "%");

		if (percentage >= 80) {
			IO.println("Grade : A");
		} else if (percentage >= 60) {
			IO.println("Grade : B");
		} else if (percentage >= 40) {
			IO.println("Grade : C");
		} else {
			IO.println("Grade : Fail");
		}
	}
}