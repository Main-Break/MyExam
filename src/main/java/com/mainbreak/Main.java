package com.mainbreak;

import com.mainbreak.controller.AppointmentController;
import com.mainbreak.controller.DoctorController;
import com.mainbreak.controller.PatientController;
import com.mainbreak.repository.DoctorRepository;
import com.mainbreak.repository.PatientRepository;
import com.mainbreak.repository.AppointmentRepository;
import com.mainbreak.view.CLI;
import com.mainbreak.view.action.CreateDoctorAction;
import com.mainbreak.view.action.CreatePatientAction;

public class Main {

    public static void main(String[] args) {

        DoctorRepository doctorRepository = new DoctorRepository();
        PatientRepository patientRepository = new PatientRepository();
        AppointmentRepository appointmentRepository = new AppointmentRepository();

        DoctorController controllerDoctor = new DoctorController(doctorRepository);
        PatientController controllerPatient = new PatientController(patientRepository);
        // AppointmentController controllerAppointment = new AppointmentController(appointmentRepository, doctorRepository, patientRepository);

        CLI menuCLI = new CLI();

        menuCLI.addOption(1, "Cadastrar Medico", new CreateDoctorAction(controllerDoctor));
        menuCLI.addOption(2, "Cadastrar Paciente", new CreatePatientAction(controllerPatient));
        // menuCLI.addOption(3, "Cadastrar Consulta", new CreateAppointmentAction(controllerAppointment));

        menuCLI.startCLI();
    }
}