package com.hospital;

import java.util.ArrayList;
import java.util.List;

public class HospitalService {

	private static List<Patient> patients = new ArrayList<>();

	private static List<Doctor> doctors = new ArrayList<>();

	private static List<Appointment> appointments = new ArrayList<>();

	private static List<Bill> bills = new ArrayList<>();

	private static double totalRevenue = 0;

	public static void startApplication() {

		while (true) {

			IO.println("\n===== HOSPITAL MANAGEMENT SYSTEM =====");

			IO.println("1. Register Patient");
			IO.println("2. View Patients");
			IO.println("3. Search Patient");
			IO.println("4. Update Patient");
			IO.println("5. Discharge Patient");

			IO.println("6. Add Doctor");
			IO.println("7. View Doctors");

			IO.println("8. Book Appointment");
			IO.println("9. View Appointments");

			IO.println("10. Generate Bill");
			IO.println("11. View Bills");

			IO.println("12. Total Patients");
			IO.println("13. Revenue Report");

			IO.println("14. Exit");

			int choice = Integer.parseInt(IO.readln("Enter Choice: "));

			switch (choice) {

			case 1:
				registerPatient();
				break;

			case 2:
				viewPatients();
				break;

			case 3:
				searchPatient();
				break;

			case 4:
				updatePatient();
				break;

			case 5:
				dischargePatient();
				break;

			case 6:
				addDoctor();
				break;

			case 7:
				viewDoctors();
				break;

			case 8:
				bookAppointment();
				break;

			case 9:
				viewAppointments();
				break;

			case 10:
				generateBill();
				break;

			case 11:
				viewBills();
				break;

			case 12:
				IO.println("Total Patients : " + patients.size());
				break;

			case 13:
				revenueReport();
				break;

			case 14:
				IO.println("Thank You!");
				return;

			default:
				IO.println("Invalid Choice!");
			}
		}
	}

	private static void registerPatient() {

		int id = Integer.parseInt(IO.readln("Patient ID: "));

		String name = IO.readln("Patient Name: ");

		int age = Integer.parseInt(IO.readln("Patient Age: "));

		String disease = IO.readln("Disease: ");

		patients.add(new Patient(id, name, age, disease));

		IO.println("Patient Registered Successfully!");
	}

	private static void viewPatients() {

		if (patients.isEmpty()) {

			IO.println("No Patients Found!");
			return;
		}

		for (Patient patient : patients) {

			IO.println(patient);
		}
	}

	private static void searchPatient() {

		int id = Integer.parseInt(IO.readln("Patient ID: "));

		for (Patient patient : patients) {

			if (patient.getId() == id) {

				IO.println(patient);
				return;
			}
		}

		IO.println("Patient Not Found!");
	}

	private static void updatePatient() {

		int id = Integer.parseInt(IO.readln("Patient ID: "));

		for (Patient patient : patients) {

			if (patient.getId() == id) {

				patient.setName(IO.readln("New Name: "));

				patient.setDisease(IO.readln("New Disease: "));

				IO.println("Patient Updated Successfully!");

				return;
			}
		}

		IO.println("Patient Not Found!");
	}

	private static void dischargePatient() {

		int id = Integer.parseInt(IO.readln("Patient ID: "));

		boolean removed = patients.removeIf(p -> p.getId() == id);

		if (removed) {

			IO.println("Patient Discharged Successfully!");

		} else {

			IO.println("Patient Not Found!");
		}
	}

	private static void addDoctor() {

		int doctorId = Integer.parseInt(IO.readln("Doctor ID: "));

		String doctorName = IO.readln("Doctor Name: ");

		String specialization = IO.readln("Specialization: ");

		doctors.add(new Doctor(doctorId, doctorName, specialization));

		IO.println("Doctor Added Successfully!");
	}

	private static void viewDoctors() {

		if (doctors.isEmpty()) {

			IO.println("No Doctors Found!");
			return;
		}

		for (Doctor doctor : doctors) {

			IO.println(doctor);
		}
	}

	private static void bookAppointment() {

		int patientId = Integer.parseInt(IO.readln("Patient ID: "));

		String patientName = IO.readln("Patient Name: ");

		String doctorName = IO.readln("Doctor Name: ");

		String date = IO.readln("Appointment Date: ");

		appointments.add(new Appointment(patientId, patientName, doctorName, date));

		IO.println("Appointment Booked Successfully!");
	}

	private static void viewAppointments() {

		if (appointments.isEmpty()) {

			IO.println("No Appointments Found!");
			return;
		}

		for (Appointment appointment : appointments) {

			IO.println(appointment);
		}
	}

	private static void generateBill() {

		int patientId = Integer.parseInt(IO.readln("Patient ID: "));

		String patientName = IO.readln("Patient Name: ");

		double amount = Double.parseDouble(IO.readln("Bill Amount: "));

		Bill bill = new Bill(patientId, patientName, amount);

		bills.add(bill);

		totalRevenue += amount;

		IO.println("Bill Generated Successfully!");
	}

	private static void viewBills() {

		if (bills.isEmpty()) {

			IO.println("No Bills Found!");
			return;
		}

		for (Bill bill : bills) {

			IO.println(bill);
		}
	}

	private static void revenueReport() {

		IO.println("\n===== REVENUE REPORT =====");

		IO.println("Total Revenue : Rs." + totalRevenue);

		IO.println("Total Bills : " + bills.size());
	}
}