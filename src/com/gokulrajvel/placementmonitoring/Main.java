package com.gokulrajvel.placementmonitoring;

import com.gokulrajvel.placementmonitoring.features.signup.SignUpModel;
import com.gokulrajvel.placementmonitoring.features.signup.SignUpView;

public class Main {
    public static final int VERSION_NO = 1;
    public static final String VERSION_NAME = "0.0.1";

    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");
        SignUpView signUp = new SignUpView();
        signUp.init();
        System.out.println(signUp);
    }
}