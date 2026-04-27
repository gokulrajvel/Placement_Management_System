package com.gokulrajvel.placementmonitoring.data.repository;

import com.gokulrajvel.placementmonitoring.data.dto.Company;
import com.gokulrajvel.placementmonitoring.data.dto.InterviewSchedule;
import com.gokulrajvel.placementmonitoring.data.dto.StudentSignUp;
import com.gokulrajvel.placementmonitoring.data.dto.TeacherSignUp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PlacementMonitoringDB {
    private static final Map<String, StudentSignUp> STUDENTS = new LinkedHashMap<>();
    private static final Map<String, TeacherSignUp> TEACHERS = new LinkedHashMap<>();
    private static final Map<Integer, Company> COMPANIES = new LinkedHashMap<>();
    private static final Map<Integer, InterviewSchedule> INTERVIEW_SCHEDULES = new LinkedHashMap<>();
    private static int nextCompanyId = 1;
    private static int nextInterviewScheduleId = 1;

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

    public static boolean addCompany(Company company) {
        String email = normalize(company.getCompany_email());
        for (Company existingCompany : COMPANIES.values()) {
            if (normalize(existingCompany.getCompany_email()).equals(email)) {
                return false;
            }
        }
        company.setCompany_id(nextCompanyId++);
        company.setCompany_email(email);
        COMPANIES.put(company.getCompany_id(), company);
        return true;
    }

    public static List<Company> getCompanies() {
        return Collections.unmodifiableList(new ArrayList<>(COMPANIES.values())); //we use unmodifiable lists primarily to protect data from accidental or unauthorized changes.
    }

    public static Company findCompanyById(int companyId) {
        return COMPANIES.get(companyId);
    }

    public static List<StudentSignUp> getStudents() {
        return Collections.unmodifiableList(new ArrayList<>(STUDENTS.values()));
    }

    public static StudentSignUp findStudentByEmail(String email) {
        return STUDENTS.get(normalize(email));
    }

    public static boolean updateStudentPlacementStatus(String email, StudentSignUp.placeMentStatus placementStatus) {
        StudentSignUp student = findStudentByEmail(email);
        if (student == null) {
            return false;
        }
        student.setPlacementStatus(placementStatus);
        return true;
    }

    public static boolean addInterviewSchedule(InterviewSchedule interviewSchedule) {
        StudentSignUp student = findStudentByEmail(interviewSchedule.getStudentEmail());
        Company company = findCompanyById(interviewSchedule.getCompanyId());
        if (student == null || company == null) {
            return false;
        }
        interviewSchedule.setStudentEmail(normalize(interviewSchedule.getStudentEmail()));
        interviewSchedule.setSchedule_id(nextInterviewScheduleId++);
        INTERVIEW_SCHEDULES.put(interviewSchedule.getSchedule_id(), interviewSchedule);
        return true;
    }

    public static InterviewSchedule findInterviewScheduleById(int scheduleId) {
        return INTERVIEW_SCHEDULES.get(scheduleId);
    }

    public static List<InterviewSchedule> getInterviewSchedules() {
        return Collections.unmodifiableList(new ArrayList<>(INTERVIEW_SCHEDULES.values()));
    }

    public static List<InterviewSchedule> getInterviewSchedulesByStudent(String email) {
        String normalizedEmail = normalize(email);
        List<InterviewSchedule> schedules = new ArrayList<>();
        for (InterviewSchedule schedule : INTERVIEW_SCHEDULES.values()) {
            if (normalizedEmail.equals(schedule.getStudentEmail())) {
                schedules.add(schedule);
            }
        }
        return Collections.unmodifiableList(schedules);
    }

    public static boolean updateInterviewSchedule(int scheduleId,
                                                  InterviewSchedule.InterviewStatus interviewStatus,
                                                  String remarks) {
        InterviewSchedule interviewSchedule = findInterviewScheduleById(scheduleId);
        if (interviewSchedule == null) {
            return false;
        }
        interviewSchedule.setInterviewStatus(interviewStatus);
        interviewSchedule.setRemarks(remarks == null ? "" : remarks.trim());
        return true;
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase();
    }
}
