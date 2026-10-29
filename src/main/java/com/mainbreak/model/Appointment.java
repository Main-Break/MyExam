package com.mainbreak.model;

import com.mainbreak.model.enums.AppointmentStatus;

import java.time.LocalDateTime;

public class Appointment {
    private String ID;
    private Patient patient;
    private Doctor doctor;
    private LocalDateTime dataTime;
    private AppointmentStatus status;
    private String notes;

    public Object getID() {
        return this.ID;
    }

    public AppointmentStatus setStatus(AppointmentStatus status) {
        this.status = status;
        return  this.status;
    }



}
