package com.onlineExam;

import java.util.List;

public class OnlineExamMain {

	public static void main(String[] args) {

		ExamService service = new ExamService();

		while (true) {

			System.out.println();
			System.out.println("======================================");
			System.out.println("        ONLINE EXAM MANAGEMENT");
			System.out.println("======================================");

			System.out.println("1. Start Exam");
			System.out.println("2. View All Questions");
			System.out.println("3. Add Question");
			System.out.println("4. Search Question");
			System.out.println("5. Update Question");
			System.out.println("6. Delete Question");
			System.out.println("7. Exam Result");
			System.out.println("8. Exit");

			System.out.println("======================================");

			String choice = IO.readln("Enter your choice: ");

			switch (choice) {

			case "1":

				List<Question> questions = service.getExamQuestions();

				if (questions.isEmpty()) {
					IO.println("No questions available.");
					break;
				}

				int score = 0;
				int count = 0;

				System.out.println();
				System.out.println("========== START EXAM ==========");

				for (Question q : questions) {

					count++;

					System.out.println();
					System.out.println(q.getId() + ". " + q.getQuestion());

					System.out.println("A. " + q.getOptionA());

					System.out.println("B. " + q.getOptionB());

					System.out.println("C. " + q.getOptionC());

					String answer = IO.readln("Answer (A/B/C): ");

					if (!answer.isEmpty() && service.checkAnswer(q.getId(), answer.charAt(0))) {

						score++;
					}
				}

				System.out.println();
				System.out.println("========== EXAM RESULT ==========");

				System.out.println("Total Questions : " + count);

				System.out.println("Correct Answers : " + score);

				System.out.println("Wrong Answers   : " + (count - score));

				System.out.println("Score           : " + score + "/" + count);

				double percentage = (score * 100.0) / count;

				System.out.printf("Percentage      : %.2f%%%n", percentage);

				System.out.println("=================================");

				break;

			case "2":

				List<Question> allQuestions = service.getAllQuestions();

				if (allQuestions.isEmpty()) {

					IO.println("No questions found.");

				} else {

					System.out.println();
					System.out.println("========== ALL QUESTIONS ==========");

					for (Question q : allQuestions) {
						System.out.println(q);
					}
				}

				break;

			case "3":

				int id = Integer.parseInt(IO.readln("Enter Question ID: "));

				String question = IO.readln("Enter Question: ");

				String optionA = IO.readln("Enter Option A: ");

				String optionB = IO.readln("Enter Option B: ");

				String optionC = IO.readln("Enter Option C: ");

				char answer = IO.readln("Enter Correct Answer (A/B/C): ").toUpperCase().charAt(0);

				Question newQuestion = new Question(id, question, optionA, optionB, optionC, answer);

				if (service.addQuestion(newQuestion)) {

					IO.println("Question added successfully.");

				} else {

					IO.println("Failed to add question.");
				}

				break;

			case "4":

				int searchId = Integer.parseInt(IO.readln("Enter Question ID: "));

				Question foundQuestion = service.searchQuestion(searchId);

				if (foundQuestion != null) {

					System.out.println();
					System.out.println("========== QUESTION FOUND ==========");

					System.out.println(foundQuestion);

				} else {

					IO.println("Question not found.");
				}

				break;

			case "5":

				int updateId = Integer.parseInt(IO.readln("Enter Question ID: "));

				Question existingQuestion = service.searchQuestion(updateId);

				if (existingQuestion == null) {

					IO.println("Question not found.");

				} else {

					String newQuestion1 = IO.readln("Enter New Question: ");

					String newOptionA = IO.readln("Enter New Option A: ");

					String newOptionB = IO.readln("Enter New Option B: ");

					String newOptionC = IO.readln("Enter New Option C: ");

					char newAnswer = IO.readln("Enter New Correct Answer (A/B/C): ").toUpperCase().charAt(0);

					Question updatedQuestion = new Question(updateId, newQuestion1, newOptionA, newOptionB, newOptionC,
							newAnswer);

					if (service.updateQuestion(updatedQuestion)) {

						IO.println("Question updated successfully.");

					} else {

						IO.println("Failed to update question.");
					}
				}

				break;

			case "6":

				int deleteId = Integer.parseInt(IO.readln("Enter Question ID: "));

				if (service.deleteQuestion(deleteId)) {

					IO.println("Question deleted successfully.");

				} else {

					IO.println("Question not found.");
				}

				break;

			case "7":

				int totalQuestions = service.getTotalQuestions();

				System.out.println();
				System.out.println("========== EXAM INFORMATION ==========");

				System.out.println("Total Questions : " + totalQuestions);

				System.out.println("======================================");

				break;

			case "8":

				IO.println("Thank you for using Online Exam Management System.");

				return;

			default:

				IO.println("Invalid choice. Please enter 1 to 8.");
			}
		}
	}
}