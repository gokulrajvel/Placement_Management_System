package com.gokulrajvel.placementmonitoring.data.repository;

import com.gokulrajvel.placementmonitoring.data.dto.StudentSignUp;
import com.gokulrajvel.placementmonitoring.data.dto.TeacherSignUp;

import java.util.LinkedHashMap;
import java.util.Map;

public class PlacementMonitoringDB {
    private static final Map<String, StudentSignUp> STUDENTS = new LinkedHashMap<>();
    private static final Map<String, TeacherSignUp> TEACHERS = new LinkedHashMap<>();

    private PlacementMonitoringDB() {
    }

    public static boolean addStudent(StudentSignUp studentSignUp) {
        String email = normalize(studentSignUp.getEmail());
        if (STUDENTS.containsKey(email)) {
            return false;
        }
        studentSignUp.setEmail(email);
        STUDENTS.put(email, studentSignUp);
        return true;
    }

    public static boolean addTeacher(TeacherSignUp teacherSignUp) {
        String email = normalize(teacherSignUp.getEmail());
        if (TEACHERS.containsKey(email)) {
            return false;
        }
        teacherSignUp.setEmail(email);
        TEACHERS.put(email, teacherSignUp);
        return true;
    }

    public static StudentSignUp validateStudentLogin(String email, String password) {
        StudentSignUp student = STUDENTS.get(normalize(email));
        if (student == null) {
            return null;
        }
        return student.getPassword().equals(password) ? student : null;
    }

    public static TeacherSignUp validateTeacherLogin(String email, String password) {
        TeacherSignUp teacher = TEACHERS.get(normalize(email));
        if (teacher == null) {
            return null;
        }
        return teacher.getPassword().equals(password) ? teacher : null;
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase();
    }
}
