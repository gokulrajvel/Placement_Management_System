package com.gokulrajvel.placementmonitoring.features.signup;

import com.gokulrajvel.placementmonitoring.util.ConsoleInput;

import java.util.Scanner;

public class SignUpView {
    private final SignUpModel signUpModel;
    private final Scanner scanner = ConsoleInput.getScanner();

    public SignUpView() {
        signUpModel = new SignUpModel(this);
    }

    public boolean init() {
        while (true) {
            System.out.println();
            System.out.println("Sign Up As");
            System.out.println("1. Student");
            System.out.println("2. Teacher");
            System.out.println("3. Back");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    new StudentSignUpView(signUpModel).init();
                    return true;
                case "2":
                    new TeacherSignUpView(signUpModel).init();
                    return true;
                case "3":
                    return false;
                default:
                    showMessage("Invalid option. Please try again.");
            }
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
