package com.gokulrajvel.placementmonitoring.data.dto;

import java.util.Date;

public class InterviewSchedule {
    private int schedule_id;
    private Date schedule_date;
    private String schedule_time;
    private String locationOrLink;
    public void  setSchedule_id(int schedule_id) {
        this.schedule_id = schedule_id;
    }
    public int getSchedule_id() {
        return schedule_id;
    }
    public void setSchedule_date(Date schedule_date) {
        this.schedule_date = schedule_date;
    }
    public Date getSchedule_date() {
        return schedule_date;
    }
    public void setSchedule_time(String schedule_time) {
        this.schedule_time = schedule_time;
    }
    public String getSchedule_time() {
        return schedule_time;
    }
    public void setLocationOrLink(String locationOrLink) {
        this.locationOrLink = locationOrLink;
    }
    public String getLocationOrLink() {
        return locationOrLink;
    }

}
