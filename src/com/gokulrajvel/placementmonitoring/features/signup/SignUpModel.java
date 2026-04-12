package com.gokulrajvel.placementmonitoring.features.signup;

public class SignUpModel {
    SignUpView obj;
    SignUpModel(SignUpView obj) {
        this.obj=obj;
    }
    public void createUser(String username){

        obj.created(username);
    }
}
