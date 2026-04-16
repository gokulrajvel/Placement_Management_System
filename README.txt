	placement Management

Student Module

- Register and login with credentials

- Update personal profile (name, contact, skills, resume)

- View available job postings

- View interview schedules and results.

Admin Module

- Manage students, companies, and staff accounts

- Generate placement reports and statistics

- Manage academic year and batch data

- Send notifications/announcements.

Placement Officer Module

- Coordinate between students and companies

- Manage drive schedules

- Upload placement results

- Track placement percentage

Class Diagrams
classDiagram
    class User {
        <<abstract>>
        +String username
        +String password
        +login()
        +logout()
    }

    class Student {
        +String name
        +String contact
        +String[] skills
        +String resume
        +String batch
        +register()
        +updateProfile()
        +viewAvailableJobs()
        +viewSchedules()
        +viewResults()
    }

    class Admin {
        +manageAccounts()
        +generateReports()
        +manageAcademicData()
        +sendNotification()
    }

    class PlacementOfficer {
        +coordinateDrives()
        +manageSchedules()
        +uploadResults()
        +trackPlacementPercentage()
    }

    class Company {
        +int companyId
        +String companyName
        +private String company_address;
    	+private String company_email;
    	+private String company_phone;
    	+private String company_website;
    }

    class JobPosting {
        +int jobId
        +String title
        +String description
        +Date deadline
    }

    class InterviewSchedule {
        +int scheduleId
        +Date date
        +String time
        +String locationOrLink
    }

    class PlacementResult {
        +int resultId
        +String status
        +String packageOffered
    }

 

    %% Inheritance Relationships
    User <|-- Student
    User <|-- Admin
    User <|-- PlacementOfficer

    %% Associations
    Admin "1" -- "*" Student : manages
    Admin "1" -- "*" Company : manages
    Admin "1" -- "*" PlacementOfficer : manages
    Admin "1" -- "*" Notification : sends

    Company "1" -- "*" JobPosting : provides
    Student "*" -- "*" JobPosting : views/applies
    
    PlacementOfficer "1" -- "*" InterviewSchedule : manages
    Student "*" -- "*" InterviewSchedule : attends
    
    PlacementOfficer "1" -- "*" PlacementResult : uploads
    Student "1" -- "1..*" PlacementResult : receives






update -- version 1.02
Student Module

- Apply for jobs and track application status

Recruiter Module
- Register and post job openings
- View and filter student applications
- Schedule interviews
- Select or reject candidates
- View shortlisted students.
- Approve/reject company registrations

Non-Functional Requirements

- Secure login with role-based access (Student, Admin, Company, Officer)
- Password encryption (BCrypt)
- Fast search and filter for student profiles
- Responsive UI
- Data backup support


