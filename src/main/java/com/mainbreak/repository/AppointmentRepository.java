package com.mainbreak.repository;

import com.mainbreak.model.Appointment;
import com.mainbreak.model.Doctor;
import java.util.ArrayList;
import java.util.List;

public class AppointmentRepository {

    private final List<Appointment> appointments = new ArrayList<>();

    public void add(Appointment appointment) {
        this.appointments.add(appointment);
    }

    public Appointment findById(String id) {
        for (Appointment appointment : this.appointments) {
            if (appointment.getID().equals(id)) {
                return appointment;
            }
        }
        return null;
    }

    public List<Appointment> findAll() {
        return this.appointments;
    }
}