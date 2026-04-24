package com.gokulrajvel.placementmonitoring;

import com.gokulrajvel.placementmonitoring.features.signin.SignInView;
import com.gokulrajvel.placementmonitoring.features.signup.SignUpView;
import com.gokulrajvel.placementmonitoring.util.ConsoleInput;

import java.util.Scanner;

public class PlacementManagement {
    public static final String PROJECT_Name="SIET";
    public static final int VERSION_NO = 1;
    public static final String VERSION_NAME = "0.0.1";

    public static void main(String[] args) {
        System.out.println("----------------- welcome to "+PROJECT_Name+" placement monitoring -----------------");
        System.out.println("-----------------         Version: " + VERSION_NO+VERSION_NAME.substring(1)+"              -----------------");
        showLandingMenu();
    }
    private static void showLandingMenu() {
        Scanner scanner = ConsoleInput.getScanner();
        while (true) {
            System.out.println();
            System.out.println("1. Sign Up");
            System.out.println("2. Sign In");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    new SignUpView().init();
                    break;
                case "2":
                    new SignInView().init();
                    break;
                case "3":
                    System.out.println("Thank you for using Placement Monitoring!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
