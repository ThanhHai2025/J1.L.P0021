package View;

import Controller.Manager;
import Model.Student;
import Utility.Validation;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class View {

    Validation validation = new Validation();
    Manager manager = new Manager();


    public void Menu() {
        System.out.println("====== Student Management ======");
        System.out.println("1. Create");
        System.out.println("2. Find and Sort");
        System.out.println("3. Update/Delete");
        System.out.println("4. Report");
        System.out.println("5. Exit");
    }

    public void display() {
        ArrayList<Student> listOfStudent = manager.getList();
        while (true) {
            Menu();
            int choice = validation.checkinputLimit("Enter your choice: ", 1, 5);
            switch (choice) {
                case 1:
                    manager.createStudent(listOfStudent);
                    break;
                case 2:
                    manager.findAndSort(listOfStudent);
                    break;
                case 3:
                    manager.updateOrDelete(listOfStudent);
                    break;
                case 4:
                    manager.report(listOfStudent);
                    break;
                case 5:
                    System.out.println("Exit program.");
                    return;
            }
        }

    }
}
