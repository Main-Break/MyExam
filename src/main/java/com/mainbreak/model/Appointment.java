package com.mainbreak.model;

import com.mainbreak.model.enums.AppointmentStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class Appointment {
    private final String ID;
    private final Doctor doctor;
    private final Patient patient;
    private final LocalDateTime dataTime;
    private final String notes;
    private AppointmentStatus status;

    public Appointment(Doctor doctor, Patient patient, LocalDateTime dataTime, String notes) {
        this.ID = UUID.randomUUID().toString();
        this.doctor = doctor;
        this.patient = patient;
        this.dataTime = dataTime;
        this.notes = notes;
        this.status = AppointmentStatus.WAITING;
    }

    public String getID() {
        return this.ID;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public LocalDateTime getDataTime() {
        return dataTime;
    }

    public String getNotes() {
        return notes;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
}
