package com.gokulrajvel.placementmonitoring.features.signin;

import com.gokulrajvel.placementmonitoring.data.dto.LoginRequest;
import com.gokulrajvel.placementmonitoring.util.ConsoleInput;

import java.util.Scanner;

public class TeacherSignInView {
    private final SignInModel signInModel;
    private final Scanner scanner = ConsoleInput.getScanner();

    public TeacherSignInView(SignInModel signInModel) {
        this.signInModel = signInModel;
    }

    public void init() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setRole("teacher");
        System.out.println();
        System.out.println("Teacher Login");
        System.out.print("Email: ");
        loginRequest.setEmail(scanner.nextLine().trim());
        System.out.print("Password: ");
        loginRequest.setPassword(scanner.nextLine().trim());
        signInModel.loginTeacher(loginRequest);
    }
}
