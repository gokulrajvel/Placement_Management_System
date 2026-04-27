package com.gokulrajvel.placementmonitoring.features.student.portal;

import com.gokulrajvel.placementmonitoring.data.dto.StudentSignUp;
import com.gokulrajvel.placementmonitoring.util.ConsoleInput;

import java.util.Scanner;

public class StudentPortalView {
    private final StudentSignUp student;
    private final StudentPortalModel studentPortalModel;
    private final Scanner scanner = ConsoleInput.getScanner();

    public StudentPortalView(StudentSignUp student) {
        this.student = student;
        this.studentPortalModel = new StudentPortalModel();
    }

    public void init() {
        while (true) {
            System.out.println();
            System.out.println("Student Portal - " + student.getName());
            System.out.println("1. View Company List");
            System.out.println("2. View Placement Status");
            System.out.println("3. View Interview History");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    studentPortalModel.showCompanyList();
                    break;
                case "2":
                    studentPortalModel.showPlacementStatus(student);
                    break;
                case "3":
                    studentPortalModel.showInterviewHistory(student);
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
