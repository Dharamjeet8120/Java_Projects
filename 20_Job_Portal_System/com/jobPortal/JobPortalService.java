package com.jobPortal;

import java.util.ArrayList;
import java.util.List;

public class JobPortalService {

	private static List<Job> jobs = new ArrayList<>();

	private static List<JobApplication> applications = new ArrayList<>();

	private static int nextJobId = 4;

	private static int nextApplicationId = 1001;

	static {

		jobs.add(new Job(1, "Java Developer", "ABC Tech", "Hyderabad", 600000));

		jobs.add(new Job(2, "Data Engineer", "XYZ Labs", "Bangalore", 800000));

		jobs.add(new Job(3, "QA Engineer", "PQR Soft", "Pune", 500000));
	}

	public static void startApplication() {

		while (true) {

			IO.println("\n========== JOB PORTAL ==========");

			IO.println("1. View All Jobs");
			IO.println("2. Search Jobs");
			IO.println("3. Add Job");
			IO.println("4. Apply For Job");
			IO.println("5. View Applications");
			IO.println("6. Search Application");
			IO.println("7. Update Application Status");
			IO.println("8. Delete Job");
			IO.println("9. Portal Statistics");
			IO.println("10. Exit");

			int choice = Integer.parseInt(IO.readln("Enter Choice: "));

			switch (choice) {

			case 1 -> viewAllJobs();

			case 2 -> searchJobs();

			case 3 -> addJob();

			case 4 -> applyForJob();

			case 5 -> viewApplications();

			case 6 -> searchApplication();

			case 7 -> updateApplicationStatus();

			case 8 -> deleteJob();

			case 9 -> portalStatistics();

			case 10 -> {
				IO.println("Thank You!");
				return;
			}

			default -> IO.println("Invalid Choice!");
			}
		}
	}

	private static void viewAllJobs() {

		if (jobs.isEmpty()) {
			IO.println("No Jobs Available!");
			return;
		}

		IO.println("\n========== AVAILABLE JOBS ==========");

		for (Job job : jobs) {
			IO.println(job);
		}
	}

	private static void searchJobs() {

		String keyword = IO.readln("Enter Role/Company Keyword: ");

		boolean found = false;

		IO.println("\n========== SEARCH RESULT ==========");

		for (Job job : jobs) {

			if (job.getRole().toLowerCase().contains(keyword.toLowerCase())

					||

					job.getCompany().toLowerCase().contains(keyword.toLowerCase())

					||

					job.getLocation().toLowerCase().contains(keyword.toLowerCase())) {

				IO.println(job);

				found = true;
			}
		}

		if (!found) {
			IO.println("No Matching Jobs Found!");
		}
	}

	private static void addJob() {

		IO.println("\n========== ADD JOB ==========");

		String role = IO.readln("Job Role: ");

		String company = IO.readln("Company Name: ");

		String location = IO.readln("Location: ");

		double salary = Double.parseDouble(IO.readln("Annual Salary: "));

		if (salary <= 0) {
			IO.println("Invalid Salary!");
			return;
		}

		Job job = new Job(nextJobId++, role, company, location, salary);

		jobs.add(job);

		IO.println("Job Added Successfully!");
		IO.println("Job ID: " + job.getId());
	}

	private static void applyForJob() {

		if (jobs.isEmpty()) {
			IO.println("No Jobs Available!");
			return;
		}

		String applicantName = IO.readln("Applicant Name: ");

		int jobId = Integer.parseInt(IO.readln("Enter Job ID: "));

		Job job = findJob(jobId);

		if (job == null) {
			IO.println("Job Not Found!");
			return;
		}

		// Prevent duplicate application
		for (JobApplication application : applications) {

			if (application.getApplicantName().equalsIgnoreCase(applicantName)

					&&

					application.getJob().getId() == jobId) {

				IO.println("You have already applied for this job!");

				return;
			}
		}

		JobApplication application = new JobApplication(nextApplicationId++, applicantName, job);

		applications.add(application);

		IO.println("\nApplication Submitted Successfully!");

		IO.println("Application ID: " + application.getApplicationId());

		IO.println("Applicant: " + application.getApplicantName());

		IO.println("Job: " + job.getRole());

		IO.println("Company: " + job.getCompany());
	}

	private static void viewApplications() {

		if (applications.isEmpty()) {
			IO.println("No Applications Found!");
			return;
		}

		IO.println("\n========== JOB APPLICATIONS ==========");

		for (JobApplication application : applications) {

			IO.println(application);
		}
	}

	private static void searchApplication() {

		int applicationId = Integer.parseInt(IO.readln("Enter Application ID: "));

		for (JobApplication application : applications) {

			if (application.getApplicationId() == applicationId) {

				IO.println("\nApplication Found!");
				IO.println(application);

				return;
			}
		}

		IO.println("Application Not Found!");
	}

	private static void updateApplicationStatus() {

		int applicationId = Integer.parseInt(IO.readln("Application ID: "));

		for (JobApplication application : applications) {

			if (application.getApplicationId() == applicationId) {

				IO.println("\n1. Applied");
				IO.println("2. Shortlisted");
				IO.println("3. Interview");
				IO.println("4. Selected");
				IO.println("5. Rejected");

				int choice = Integer.parseInt(IO.readln("Select Status: "));

				String status;

				switch (choice) {

				case 1 -> status = "Applied";

				case 2 -> status = "Shortlisted";

				case 3 -> status = "Interview";

				case 4 -> status = "Selected";

				case 5 -> status = "Rejected";

				default -> {
					IO.println("Invalid Status!");
					return;
				}
				}

				application.setStatus(status);

				IO.println("Application Status Updated!");

				return;
			}
		}

		IO.println("Application Not Found!");
	}

	private static void deleteJob() {

		int jobId = Integer.parseInt(IO.readln("Enter Job ID: "));

		boolean removed = jobs.removeIf(job -> job.getId() == jobId);

		if (removed) {

			IO.println("Job Deleted Successfully!");

		} else {

			IO.println("Job Not Found!");
		}
	}

	private static void portalStatistics() {

		int applied = 0;
		int shortlisted = 0;
		int interview = 0;
		int selected = 0;
		int rejected = 0;

		for (JobApplication application : applications) {

			switch (application.getStatus()) {

			case "Applied" -> applied++;

			case "Shortlisted" -> shortlisted++;

			case "Interview" -> interview++;

			case "Selected" -> selected++;

			case "Rejected" -> rejected++;
			}
		}

		IO.println("\n========== PORTAL STATISTICS ==========");

		IO.println("Total Jobs         : " + jobs.size());

		IO.println("Total Applications : " + applications.size());

		IO.println("Applied            : " + applied);

		IO.println("Shortlisted        : " + shortlisted);

		IO.println("Interview          : " + interview);

		IO.println("Selected           : " + selected);

		IO.println("Rejected           : " + rejected);
	}

	private static Job findJob(int jobId) {

		for (Job job : jobs) {

			if (job.getId() == jobId) {
				return job;
			}
		}

		return null;
	}
}