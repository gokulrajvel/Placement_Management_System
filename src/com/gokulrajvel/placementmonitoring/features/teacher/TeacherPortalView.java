package com.gokulrajvel.placementmonitoring.features.teacher;

import com.gokulrajvel.placementmonitoring.data.dto.Company;
import com.gokulrajvel.placementmonitoring.data.dto.InterviewSchedule;
import com.gokulrajvel.placementmonitoring.data.dto.StudentSignUp;
import com.gokulrajvel.placementmonitoring.data.dto.TeacherSignUp;
import com.gokulrajvel.placementmonitoring.util.ConsoleInput;
import com.gokulrajvel.placementmonitoring.util.ParseHelper;

import java.util.Date;
import java.util.Scanner;

public class TeacherPortalView {
    private final TeacherSignUp teacher;
    private final TeacherPortalModel teacherPortalModel;
    private final Scanner scanner = ConsoleInput.getScanner();

    public TeacherPortalView(TeacherSignUp teacher) {
        this.teacher = teacher;
        this.teacherPortalModel = new TeacherPortalModel();
    }

    public void init() {
        while (true) {
            System.out.println();
            System.out.println("Teacher Portal - " + teacher.getTeacherId());
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Add Company");
            System.out.println("4. View Companies");
            System.out.println("5. Update Student Placement Status");
            System.out.println("6. Schedule Interview");
            System.out.println("7. View Interview History");
            System.out.println("8. Update Interview History");
            System.out.println("9. Logout");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addStudent();
                    break;
                case "2":
                    teacherPortalModel.showStudentList();
                    break;
                case "3":
                    addCompany();
                    break;
                case "4":
                    teacherPortalModel.showCompanyList();
                    break;
                case "5":
                    updatePlacementStatus();
                    break;
                case "6":
                    scheduleInterview();
                    break;
                case "7":
                    teacherPortalModel.showInterviewHistory();
                    break;
                case "8":
                    updateInterviewHistory();
                    break;
                case "9":
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addStudent() {
        StudentSignUp studentSignUp = new StudentSignUp();
        System.out.println();
        System.out.println("Add Student");
        studentSignUp.setRegisterNo(readRequired("Register No: "));
        studentSignUp.settName(readRequired("Name: "));
        studentSignUp.setEmail(readEmail("Email: "));
        studentSignUp.setPassword(readPassword("Password: "));
        studentSignUp.setContactNo(readMobile("Contact No: "));
        studentSignUp.setBirthDate(readDate("DOB (" + ParseHelper.getDobPattern() + "): "));
        studentSignUp.setBatch(readRequired("Batch: "));
        studentSignUp.setSkill(readSkills());
        teacherPortalModel.addStudent(studentSignUp);
    }

    private void addCompany() {
        Company company = new Company();
        System.out.println();
        System.out.println("Add Company");
        company.setCompany_name(readRequired("Company Name: "));
        company.setCompany_address(readRequired("Company Address: "));
        company.setCompany_email(readEmail("Company Email: "));
        company.setCompany_phone(readMobile("Company Phone: "));
        company.setCompany_website(readRequired("Company Website: "));
        teacherPortalModel.addCompany(company);
    }

    private void updatePlacementStatus() {
        System.out.println();
        System.out.println("Update Placement Status");
        String studentEmail = readEmail("Student Email: ");
        StudentSignUp.placeMentStatus placementStatus = readPlacementStatus();
        teacherPortalModel.updatePlacementStatus(studentEmail, placementStatus);
    }

    private void scheduleInterview() {
        InterviewSchedule interviewSchedule = new InterviewSchedule();
        System.out.println();
        System.out.println("Schedule Interview");
        interviewSchedule.setStudentEmail(readEmail("Student Email: "));
        interviewSchedule.setCompanyId(readPositiveNumber("Company Id: "));
        interviewSchedule.setSchedule_date(readDate("Interview Date (" + ParseHelper.getDobPattern() + "): "));
        interviewSchedule.setSchedule_time(readTime("Interview Time (HH:mm): "));
        interviewSchedule.setLocationOrLink(readRequired("Location / Meeting Link: "));
        interviewSchedule.setTeacherId(teacher.getTeacherId());
        interviewSchedule.setRemarks(readOptional("Remarks (optional): "));
        teacherPortalModel.scheduleInterview(interviewSchedule);
    }

    private void updateInterviewHistory() {
        System.out.println();
        System.out.println("Update Interview History");
        int scheduleId = readPositiveNumber("Interview Schedule Id: ");
        InterviewSchedule.InterviewStatus interviewStatus = readInterviewStatus();
        String remarks = readOptional("Remarks: ");
        teacherPortalModel.updateInterviewHistory(scheduleId, interviewStatus, remarks);
    }

    private StudentSignUp.placeMentStatus readPlacementStatus() {
        while (true) {
            System.out.print("Placement Status (1. PLACED / 2. NOT_PLACED): ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    return StudentSignUp.placeMentStatus.PLACED;
                case "2":
                    return StudentSignUp.placeMentStatus.NOT_PLACED;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private InterviewSchedule.InterviewStatus readInterviewStatus() {
        while (true) {
            System.out.println("Interview Status");
            System.out.println("1. SCHEDULED");
            System.out.println("2. COMPLETED");
            System.out.println("3. SELECTED");
            System.out.println("4. REJECTED");
            System.out.println("5. CANCELLED");
            System.out.print("Choose status: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    return InterviewSchedule.InterviewStatus.SCHEDULED;
                case "2":
                    return InterviewSchedule.InterviewStatus.COMPLETED;
                case "3":
                    return InterviewSchedule.InterviewStatus.SELECTED;
                case "4":
                    return InterviewSchedule.InterviewStatus.REJECTED;
                case "5":
                    return InterviewSchedule.InterviewStatus.CANCELLED;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private String readRequired(String label) {
        while (true) {
            System.out.print(label);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Value cannot be empty.");
        }
    }

    private String readOptional(String label) {
        System.out.print(label);
        return scanner.nextLine().trim();
    }

    private String[] readSkills() {
        String skills = readRequired("Skills (comma separated): ");
        String[] parts = skills.split(",");
        for (int index = 0; index < parts.length; index++) {
            parts[index] = parts[index].trim();
        }
        return parts;
    }

    private String readEmail(String label) {
        while (true) {
            String value = readRequired(label);
            if (ParseHelper.isValidEmail(value)) {
                return value;
            }
            System.out.println("Enter a valid email address.");
        }
    }

    private String readMobile(String label) {
        while (true) {
            String value = readRequired(label);
            if (ParseHelper.isValidMobile(value)) {
                return value;
            }
            System.out.println("Enter a valid 10-digit mobile number starting with 6-9.");
        }
    }

    private String readPassword(String label) {
        while (true) {
            String value = readRequired(label);
            if (ParseHelper.isValidPassword(value)) {
                return value;
            }
            System.out.println("Password must be at least 8 characters and include letters and numbers.");
        }
    }

    private int readPositiveNumber(String label) {
        while (true) {
            System.out.print(label);
            String value = scanner.nextLine().trim();
            try {
                int number = Integer.parseInt(value);
                if (number > 0) {
                    return number;
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Enter a valid positive number.");
        }
    }

    private Date readDate(String label) {
        while (true) {
            String value = readRequired(label);
            Date scheduleDate = ParseHelper.parseDate(value);
            if (scheduleDate != null) {
                return scheduleDate;
            }
            System.out.println("Enter date in " + ParseHelper.getDobPattern() + " format.");
        }
    }

    private String readTime(String label) {
        while (true) {
            String value = readRequired(label);
            if (value.matches("([01]\\d|2[0-3]):[0-5]\\d")) {
                return value;
            }
            System.out.println("Enter time in HH:mm format.");
        }
    }
}
