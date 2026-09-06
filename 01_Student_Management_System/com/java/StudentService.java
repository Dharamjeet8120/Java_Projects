package com.java;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    static List<Student> list = new ArrayList<>();

    public static void addStudent() {

        int id = Integer.parseInt(IO.readln("Enter ID: "));
        String name = IO.readln("Enter Name: ");
        String course = IO.readln("Enter Course: ");
        double marks = Double.parseDouble(IO.readln("Enter Marks: "));

        list.add(new Student(id, name, course, marks));

        IO.println("Student Added Successfully!");
    }

    public static void viewStudents() {

        if (list.isEmpty()) {

            IO.println("No Students Found!");
            return;
        }

        IO.println("\nID | Name | Course | Marks");
        IO.println("--------------------------------");

        for (Student s : list) {

            IO.println(s);
        }
    }

    public static void searchStudent() {

        int id = Integer.parseInt(IO.readln("Enter Student ID: "));

        boolean found = false;

        for (Student s : list) {

            if (s.id == id) {

                IO.println("Student Found:");
                IO.println(s);

                found = true;
                break;
            }
        }

        if (!found) {

            IO.println("Student Not Found!");
        }
    }

    public static void updateStudent() {

        int id = Integer.parseInt(IO.readln("Enter Student ID: "));

        boolean found = false;

        for (Student s : list) {

            if (s.id == id) {

                s.name = IO.readln("Enter New Name: ");
                s.course = IO.readln("Enter New Course: ");
                s.marks = Double.parseDouble(IO.readln("Enter New Marks: "));

                IO.println("Student Updated Successfully!");

                found = true;
                break;
            }
        }

        if (!found) {

            IO.println("Student Not Found!");
        }
    }

    public static void deleteStudent() {

        int id = Integer.parseInt(IO.readln("Enter Student ID: "));

        boolean removed = list.removeIf(s -> s.id == id);

        if (removed) {

            IO.println("Student Deleted Successfully!");

        } else {

            IO.println("Student Not Found!");
        }
    }
}