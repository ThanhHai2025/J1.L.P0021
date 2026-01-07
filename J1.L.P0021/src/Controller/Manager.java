package Controller;

import Model.Report;
import Model.Student;
import Utility.Validation;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Admin
 */
public class Manager {

    Validation validation = new Validation();
    ArrayList<Student> listOfStudent;

    public Manager() {
        listOfStudent = new ArrayList<>();
    }

    public ArrayList<Student> getList() {
        return listOfStudent;
    }

    public void createStudent(ArrayList<Student> listOfStudent) {
        if (listOfStudent.size() > 10) {
            if (!validation.checkInputYN("Number student > 10. Do you want to coninue (Y/N): ")) {
                return;
            }
        }
        while (true) {
            String id = validation.checkInputString("Enter ID: ");
            String name = validation.checkInputString("Enter name student: ");
            if (!validation.checkIdExist(listOfStudent, id, name)) {
                System.err.println("Id has exist. Please input again: ");
                continue;
            }
            String semester = validation.checkInputString("Enter semester: ");
            String course = validation.checkInputCourse("Enter name course: ");
            if (!validation.checkStudentExist(listOfStudent, id, name, semester, course)) {
                System.err.println("Duplicate.");
                continue;
            }
            listOfStudent.add(new Student(id, name, semester, course));
            System.out.println("Add success.");
            return;
        }
    }

    public void findAndSort(ArrayList<Student> listOfStudent) {
        if (listOfStudent.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
        ArrayList<Student> listStudentFindByName = listStudentFindByName(listOfStudent);
        if (listStudentFindByName.isEmpty()) {
            System.err.println("Not exist.");
        } else {
            Collections.sort(listStudentFindByName);
            System.out.printf("%-20s%-15s%-10s\n", "Student name", "Semester", "Course name");
            for (Student student : listStudentFindByName) {
                student.print();
            }
        }
    }

    private ArrayList<Student> listStudentFindByName(ArrayList<Student> listOfStudent) {
        ArrayList<Student> listStudentFindByName = new ArrayList<>();
        String name = validation.checkInputString("Enter name to search: ");
        for (Student student : listOfStudent) {
            if (student.getStudentName().contains(name)) {
                listStudentFindByName.add(student);
            }
        }
        return listStudentFindByName;
    }

    public void updateOrDelete(ArrayList<Student> listOfStudent) {
        if (listOfStudent.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
        String id = validation.checkInputString("Enter ID: ");
        ArrayList<Student> listStudentFindByName = getListStudentById(listOfStudent, id);
        if (listStudentFindByName.isEmpty()) {
            System.err.println("Not found student.");
            return;
        } else {
            Student student = getStudentByListFound(listStudentFindByName);
            if (validation.checkInputUD("Do you want to Update(U) or Delete(D) student: ")) {
                String idStudent = validation.checkInputString("Enter id: ");
                String name = validation.checkInputString("Enter name student: ");
                String semester = validation.checkInputString("Enter semester: ");
                String course = validation.checkInputString("Enter name course: ");
                if (!validation.checkChangeInformation(student, idStudent, name, semester, course)) {
                    System.err.println("Nothing change.");
                    return;
                }
                if (!validation.checkStudentExist(listOfStudent, idStudent, name, semester, course)) {
                    System.err.println("Duplicate student. Update again.");
                    return;

                }
                student.setId(idStudent);
                student.setStudentName(name);
                student.setSemester(semester);
                student.setCourseName(course);
                System.out.println("Update success.");

            } else {
                listOfStudent.remove(student);
                System.out.println("Delete success.");
                return;
            }
        }
    }

    private ArrayList<Student> getListStudentById(ArrayList<Student> listOfStudent, String id) {
        ArrayList<Student> getListStudentById = new ArrayList<>();
        for (Student student : listOfStudent) {
            if (id.equalsIgnoreCase(student.getId())) {
                getListStudentById.add(student);
            }
        }
        return getListStudentById;
    }

    private Student getStudentByListFound(ArrayList<Student> listStudentFindByName) {
        System.out.println("List student found: ");
        int count = 1;
        System.out.printf("%-10s%-15s%-15s%-15s\n", "Number", "Student name", "Semester", "Course name");
        for (Student student : listStudentFindByName) {
            System.out.printf("%-10s%-15s%-15s%-15s\n", count, student.getStudentName(), student.getSemester(), student.getCourseName());
            count++;
        }
        int choice = validation.checkinputLimit("Enter student: ", 1, listStudentFindByName.size());
        return listStudentFindByName.get(choice - 1);
    }

    public void report(ArrayList<Student> listOfStudent) {
        if (listOfStudent.isEmpty()) {
            System.out.println("List empty.");
            return;
        }
        ArrayList<Report> listOfReport = new ArrayList<>();
        for (Student student : listOfStudent) {
            String id = student.getId();
            String courseName = student.getCourseName();
            String studentName = student.getStudentName();
            int total = 0;
            for (Student studentCountTotal : listOfStudent) {
                if (id.equalsIgnoreCase(studentCountTotal.getId())
                        && courseName.equalsIgnoreCase(studentCountTotal.getCourseName())) {
                    total++;
                }
            }
            if (validation.checkReportExist(listOfReport, studentName, courseName, total)) {
                listOfReport.add(new Report(studentName, courseName, total));
            }
        }
        System.out.printf("%-15s | %-10s | %-5s\n", "Student name", "Course", "Total");
        for (Report report : listOfReport) {
            System.out.printf("%-15s | %-10s | %-5s\n", report.getStudentName(), report.getCourseName(), report.getTotalCourse());
        }
    }
}
