package com.gokulrajvel.placementmonitoring.features.student.portal;

import com.gokulrajvel.placementmonitoring.data.dto.Company;
import com.gokulrajvel.placementmonitoring.data.dto.InterviewSchedule;
import com.gokulrajvel.placementmonitoring.data.dto.StudentSignUp;
import com.gokulrajvel.placementmonitoring.data.repository.PlacementMonitoringDB;

import java.util.List;

public class StudentPortalModel {
    public void showCompanyList() {
        List<Company> companies = PlacementMonitoringDB.getCompanies();
        if (companies.isEmpty()) {
            System.out.println("No companies available.");
            return;
        }
        System.out.println();
        System.out.println("Available Companies");
        for (Company company : companies) {
            System.out.println(company.getCompany_id() + ". " + company.getCompany_name()
                    + " | " + company.getCompany_address()
                    + " | " + company.getCompany_website());
        }
    }

    public void showPlacementStatus(StudentSignUp student) {
        System.out.println("Placement Status: " + student.getPlacementStatus());
    }

    public void showInterviewHistory(StudentSignUp student) {
        List<InterviewSchedule> schedules = PlacementMonitoringDB.getInterviewSchedulesByStudent(student.getEmail());
        if (schedules.isEmpty()) {
            System.out.println("No interview history available.");
            return;
        }
        System.out.println();
        System.out.println("My Interview History");
        for (InterviewSchedule schedule : schedules) {
            Company company = PlacementMonitoringDB.findCompanyById(schedule.getCompanyId());
            String companyName = company == null ? "Unknown Company" : company.getCompany_name();
            System.out.println(schedule.getSchedule_id()
                    + " | " + companyName
                    + " | " + schedule.getFormattedDate()
                    + " " + schedule.getSchedule_time()
                    + " | " + schedule.getLocationOrLink()
                    + " | " + schedule.getInterviewStatus()
                    + " | " + (schedule.getRemarks() == null || schedule.getRemarks().isEmpty() ? "-" : schedule.getRemarks()));
        }
    }
}
