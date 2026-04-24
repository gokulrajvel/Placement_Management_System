package com.gokulrajvel.placementmonitoring.features.signin;

import com.gokulrajvel.placementmonitoring.data.dto.LoginRequest;
import com.gokulrajvel.placementmonitoring.data.dto.StudentSignUp;
import com.gokulrajvel.placementmonitoring.data.dto.TeacherSignUp;
import com.gokulrajvel.placementmonitoring.data.repository.PlacementMonitoringDB;

class SignInModel {
    private final SignInView signInView;

    public SignInModel(SignInView signInView) {
        this.signInView = signInView;
    }

    public void loginStudent(LoginRequest loginRequest) {
        StudentSignUp student = PlacementMonitoringDB.validateStudentLogin(loginRequest.getEmail(), loginRequest.getPassword());
        if (student == null) {
            signInView.showMessage("Invalid student email or password.");
            return;
        }
        signInView.showMessage("Student login successful. Welcome " + student.getName() + ".");
    }

    public void loginTeacher(LoginRequest loginRequest) {
        TeacherSignUp teacher = PlacementMonitoringDB.validateTeacherLogin(loginRequest.getEmail(), loginRequest.getPassword());
        if (teacher == null) {
            signInView.showMessage("Invalid teacher email or password.");
            return;
        }
        signInView.showMessage("Teacher login successful. Welcome " + teacher.getTeacherId() + ".");
    }
}
