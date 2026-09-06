package com.jobPortal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JobApplication {

	private int applicationId;
	private String applicantName;
	private Job job;
	private String status;
	private LocalDateTime appliedDate;

	public JobApplication(int applicationId, String applicantName, Job job) {

		this.applicationId = applicationId;
		this.applicantName = applicantName;
		this.job = job;
		this.status = "Applied";
		this.appliedDate = LocalDateTime.now();
	}

	public int getApplicationId() {
		return applicationId;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public Job getJob() {
		return job;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

		return applicationId + " | " + applicantName + " | " + job.getRole() + " | " + job.getCompany() + " | " + status
				+ " | " + appliedDate.format(formatter);
	}
}