package com.gokulrajvel.placementmonitoring.features.signup;

public class SignUpModel {
    SignUpView signUpView;
    SignUpModel(SignUpView obj) {
        this.signUpView=obj;
    }
    public void createUser(String username){

        signUpView.created(username);
    }
}
