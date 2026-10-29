package com.mainbreak.repository;

import com.mainbreak.model.Appointment;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AppointmentRepository {

    private final List<Appointment> appointments = new ArrayList<>();

    public void add(Appointment appointment) {
        this.appointments.add(appointment);
    }

    public Optional<Appointment> findById(String id) {
        for (Appointment appointment : this.appointments) {
            if (appointment.getID().equals(id)) {
                return Optional.of(appointment);
            }
        }
        return Optional.empty();
    }

    public List<Appointment> findAll() {
        return this.appointments;
    }
}