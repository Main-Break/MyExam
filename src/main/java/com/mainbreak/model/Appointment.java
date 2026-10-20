package com.mainbreak.model;

import java.time.LocalDateTime;

public class Appointment {
    private String ID;
    private Patient patient;
    private Doctor doctor;
    private LocalDateTime dataTime;
    private String status;
    private String notes;
}
