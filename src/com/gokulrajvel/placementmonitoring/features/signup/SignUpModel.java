package com.gokulrajvel.placementmonitoring.features.signup;

import com.gokulrajvel.placementmonitoring.data.dto.StudentSignUp;
import com.gokulrajvel.placementmonitoring.data.dto.TeacherSignUp;
import com.gokulrajvel.placementmonitoring.data.repository.PlacementMonitoringDB;

public class SignUpModel {
    private final SignUpView signUpView;

    SignUpModel(SignUpView obj) {
        this.signUpView = obj;
    }

    public void registerStudent(StudentSignUp studentSignUp) {
        boolean isCreated = PlacementMonitoringDB.addStudent(studentSignUp);
        if (isCreated) {
            signUpView.showMessage("Student account created for " + studentSignUp.getName() + ".");
            return;
        }
        signUpView.showMessage("Student email already exists.");
    }

    public void registerTeacher(TeacherSignUp teacherSignUp) {
        boolean isCreated = PlacementMonitoringDB.addTeacher(teacherSignUp);
        if (isCreated) {
            signUpView.showMessage("Teacher account created for " + teacherSignUp.getTeacherId() + ".");
            return;
        }
        signUpView.showMessage("Teacher email already exists.");
    }
}
