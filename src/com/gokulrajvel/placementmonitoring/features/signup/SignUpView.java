package com.gokulrajvel.placementmonitoring.features.signup;

import java.util.Scanner;

public class SignUpView {
    private SignUpModel signUpModel;
    public SignUpView(){
        signUpModel=new SignUpModel(this);
    }

    public void init(){
        System.out.println("Enter your name");
        Scanner sc=new Scanner(System.in);
        signUpModel.createUser(sc.nextLine());

    }
    public void created(String username){
        System.out.println("Creating user :"+username+" "+this);

        SignUpView a=this;
        System.out.println(a);
    }
}
