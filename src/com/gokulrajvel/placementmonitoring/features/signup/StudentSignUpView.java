package com.gokulrajvel.placementmonitoring.features.signup;

import com.gokulrajvel.placementmonitoring.data.dto.StudentSignUp;
import com.gokulrajvel.placementmonitoring.util.ConsoleInput;
import com.gokulrajvel.placementmonitoring.util.ParseHelper;

import java.util.Scanner;
import java.util.Date;

public class StudentSignUpView {
    private final SignUpModel signUpModel;
    private final Scanner scanner = ConsoleInput.getScanner();

    public StudentSignUpView(SignUpModel signUpModel) {
        this.signUpModel = signUpModel;
    }

    public void init() {
        StudentSignUp studentSignUp = new StudentSignUp();
        System.out.println();
        System.out.println("Student Sign Up");
        studentSignUp.setRegisterNo(readRequired("Register No: "));
        studentSignUp.settName(readRequired("Name: "));
        studentSignUp.setEmail(readEmail("Email: "));
        studentSignUp.setPassword(readPassword("Password: "));
        studentSignUp.setContactNo(readMobile("Contact No: "));
        studentSignUp.setBirthDate(readDob("DOB (" + ParseHelper.getDobPattern() + "): "));
        studentSignUp.setBatch(readRequired("Batch: "));
        studentSignUp.setSkill(readSkills());
        signUpModel.registerStudent(studentSignUp);
    }

    private String[] readSkills() {
        String skills = readRequired("Skills (comma separated): ");
        String[] parts = skills.split(",");
        for (int index = 0; index < parts.length; index++) {
            parts[index] = parts[index].trim();
        }
        return parts;
    }

    private String readRequired(String label) {
        while (true) {
            System.out.print(label);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Value cannot be empty.");
        }
    }

    private String readEmail(String label) {
        while (true) {
            String value = readRequired(label);
            if (ParseHelper.isValidEmail(value)) {
                return value;
            }
            System.out.println("Enter a valid email address.");
        }
    }

    private String readMobile(String label) {
        while (true) {
            String value = readRequired(label);
            if (ParseHelper.isValidMobile(value)) {
                return value;
            }
            System.out.println("Enter a valid 10-digit mobile number starting with 6-9.");
        }
    }

    private String readPassword(String label) {
        while (true) {
            String value = readRequired(label);
            if (ParseHelper.isValidPassword(value)) {
                return value;
            }
            System.out.println("Password must be at least 8 characters and include letters and numbers.");
        }
    }

    private Date readDob(String label) {
        while (true) {
            String value = readRequired(label);
            Date birthDate = ParseHelper.parseDate(value);
            if (birthDate != null) {
                return birthDate;
            }
            System.out.println("Enter DOB in " + ParseHelper.getDobPattern() + " format.");
        }
    }
}
