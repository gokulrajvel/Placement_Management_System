package com.gokulrajvel.placementmonitoring.features.teacher;

import com.gokulrajvel.placementmonitoring.data.dto.Company;
import com.gokulrajvel.placementmonitoring.data.dto.InterviewSchedule;
import com.gokulrajvel.placementmonitoring.data.dto.StudentSignUp;
import com.gokulrajvel.placementmonitoring.data.repository.PlacementMonitoringDB;

import java.util.List;

public class TeacherPortalModel {
    public void showStudentList() {
        List<StudentSignUp> students = PlacementMonitoringDB.getStudents();
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        System.out.println();
        System.out.println("Student List");
        for (StudentSignUp student : students) {
            System.out.println(student.getName()
                    + " | " + student.getEmail()
                    + " | " + student.getContactNo()
                    + " | " + student.getBatch()
                    + " | " + student.getPlacementStatus());
        }
    }

    public void addCompany(Company company) {
        boolean isAdded = PlacementMonitoringDB.addCompany(company);
        if (isAdded) {
            System.out.println("Company added successfully with id " + company.getCompany_id() + ".");
            return;
        }
        System.out.println("Company email already exists.");
    }

    public void showCompanyList() {
        List<Company> companies = PlacementMonitoringDB.getCompanies();
        if (companies.isEmpty()) {
            System.out.println("No companies available.");
            return;
        }
        System.out.println();
        System.out.println("Company List");
        for (Company company : companies) {
            System.out.println(company.getCompany_id() + ". " + company.getCompany_name()
                    + " | " + company.getCompany_email()
                    + " | " + company.getCompany_phone()
                    + " | " + company.getCompany_website());
        }
    }

    public void updatePlacementStatus(String email, StudentSignUp.placeMentStatus placementStatus) {
        boolean isUpdated = PlacementMonitoringDB.updateStudentPlacementStatus(email, placementStatus);
        if (isUpdated) {
            System.out.println("Student placement status updated to " + placementStatus + ".");
            return;
        }
        System.out.println("Student not found.");
    }

    public void scheduleInterview(InterviewSchedule interviewSchedule) {
        boolean isAdded = PlacementMonitoringDB.addInterviewSchedule(interviewSchedule);
        if (isAdded) {
            System.out.println("Interview scheduled successfully with id " + interviewSchedule.getSchedule_id() + ".");
            return;
        }
        System.out.println("Unable to schedule interview. Check student email and company id.");
    }

    public void showInterviewHistory() {
        List<InterviewSchedule> schedules = PlacementMonitoringDB.getInterviewSchedules();
        if (schedules.isEmpty()) {
            System.out.println("No interview history available.");
            return;
        }
        System.out.println();
        System.out.println("Interview History");
        for (InterviewSchedule schedule : schedules) {
            Company company = PlacementMonitoringDB.findCompanyById(schedule.getCompanyId());
            String companyName = company == null ? "Unknown Company" : company.getCompany_name();
            System.out.println(schedule.getSchedule_id()
                    + " | " + schedule.getStudentEmail()
                    + " | " + companyName
                    + " | " + schedule.getFormattedDate()
                    + " " + schedule.getSchedule_time()
                    + " | " + schedule.getLocationOrLink()
                    + " | " + schedule.getInterviewStatus()
                    + " | " + (schedule.getRemarks() == null || schedule.getRemarks().isEmpty() ? "-" : schedule.getRemarks()));
        }
    }

    public void updateInterviewHistory(int scheduleId,
                                       InterviewSchedule.InterviewStatus interviewStatus,
                                       String remarks) {
        boolean isUpdated = PlacementMonitoringDB.updateInterviewSchedule(scheduleId, interviewStatus, remarks);
        if (isUpdated) {
            System.out.println("Interview history updated successfully.");
            return;
        }
        System.out.println("Interview schedule not found.");
    }
}
