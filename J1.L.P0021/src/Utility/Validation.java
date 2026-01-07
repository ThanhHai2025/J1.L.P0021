package Utility;

import Model.Report;
import Model.Student;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Validation {

    Scanner check = new Scanner(System.in);

    public int checkinputLimit(String mess, int min, int max) {
        System.out.println(mess);
        while (true) {
            try {
                int result = Integer.parseInt(check.nextLine().trim());
                if (result < min || result > max) {
                    System.err.println("Please input number in range[ " + min + " ," + max + " ].");
                    continue;
                }
                return result;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please input again: ");
            }
        }
    }

    public String checkInputString(String mess) {
        System.out.println(mess);
        while (true) {
            String result = check.nextLine().trim();
            if (result.isEmpty()) {
                System.out.println("Not empty. Enter again: ");
            } else {
                return result;
            }
        }
    }

    public boolean checkInputYN(String mess) {
        System.out.println(mess);
        while (true) {
            String result = check.nextLine().trim();
            if (result.equalsIgnoreCase("Y")) {
                return true;
            }
            if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.out.println("Only onput Y or N. Enter again: ");
        }
    }

    public boolean checkInputUD(String mess) {
        System.out.println(mess);
        while (true) {
            String result = check.nextLine().trim();
            if (result.equalsIgnoreCase("U")) {
                return true;
            }
            if (result.equalsIgnoreCase("D")) {
                return false;
            }
            System.out.println("Only onput U or D. Enter again: ");
        }
    }

    public String checkInputCourse(String mess) {
        System.out.println(mess + "Java/ .Net/ C/C++");
        while (true) {
            String result = check.nextLine().trim();
            if (result.equalsIgnoreCase("java")
                    || result.equalsIgnoreCase(".net")
                    || result.equalsIgnoreCase("c/c++")) {
                if(result.equalsIgnoreCase("java")) return "java";
                if(result.equalsIgnoreCase(".net")) return ".net";
                return "c/c++";
            }
            System.out.println("Only Three Course: Java, .Net, C/C++. Enter again: ");
        }
    }

    public boolean checkStudentExist(ArrayList<Student> listOfStudent, String id, String studentName, String semester, String courseName) {
        int size = listOfStudent.size();
        for (Student student : listOfStudent) {
            if (id.equalsIgnoreCase(student.getId())
                    && studentName.equalsIgnoreCase(student.getStudentName())
                    && semester.equalsIgnoreCase(student.getSemester())
                    && courseName.equalsIgnoreCase(student.getCourseName())) {
                return false;
            }
        }
        return true;
    }

    public boolean checkReportExist(ArrayList<Report> listOfReport, String name, String course, int total) {
        for (Report report : listOfReport) {
            if (name.equalsIgnoreCase(report.getStudentName())
                    && course.equalsIgnoreCase(report.getCourseName())
                    && total == report.getTotalCourse()) {
                return false;
            }
        }
        return true;
    }

    public boolean checkIdExist(ArrayList<Student> listOfStudent, String id, String name) {
        for (Student student : listOfStudent) {
            if (id.equalsIgnoreCase(student.getId())
                    && !name.equalsIgnoreCase(student.getStudentName())) {
                return false;
            }
        }
        return true;
    }

    public boolean checkChangeInformation(Student student, String id, String name, String semester, String course) {
        if (id.equalsIgnoreCase(student.getId())
                && name.equalsIgnoreCase(student.getStudentName())
                && semester.equalsIgnoreCase(student.getSemester())
                && course.equalsIgnoreCase(student.getCourseName())) {
            return false;
        }
        return true;
    }
}
