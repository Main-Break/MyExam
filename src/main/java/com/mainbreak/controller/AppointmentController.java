package com.mainbreak.controller;

import com.mainbreak.exception.ValidationException;
import com.mainbreak.model.Appointment;
import com.mainbreak.model.Doctor;
import com.mainbreak.model.Patient;
import com.mainbreak.repository.AppointmentRepository;
import com.mainbreak.repository.DoctorRepository;
import com.mainbreak.repository.PatientRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class AppointmentController {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public AppointmentController(AppointmentRepository appointmentRepository,
                                 DoctorRepository doctorRepository,
                                 PatientRepository patientRepository) {
        this.appointmentRepository = Objects.requireNonNull(appointmentRepository);
        this.doctorRepository = Objects.requireNonNull(doctorRepository);
        this.patientRepository = Objects.requireNonNull(patientRepository);
    }

    public Appointment schedule(String doctorId, String patientId, LocalDateTime dataTime, String notes) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ValidationException("Médico não encontrado para o ID informado."));
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ValidationException("Paciente não encontrado para o ID informado."));

        Appointment appointment = new Appointment(doctor, patient, dataTime, notes);
        appointmentRepository.add(appointment);
        return appointment;
    }

    public List<Appointment> list() {
        return appointmentRepository.findAll();
    }
}
