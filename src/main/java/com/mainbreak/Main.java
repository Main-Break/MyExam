package com.mainbreak;

import com.mainbreak.controller.AppointmentController;
import com.mainbreak.controller.CadastroFacade;
import com.mainbreak.controller.DoctorController;
import com.mainbreak.controller.PatientController;
import com.mainbreak.model.DoctorFactory;
import com.mainbreak.model.PatientFactory;
import com.mainbreak.repository.AppointmentRepository;
import com.mainbreak.repository.DoctorRepository;
import com.mainbreak.repository.PatientRepository;
import com.mainbreak.security.PasswordHasher;
import com.mainbreak.security.Pbkdf2PasswordHasher;
import com.mainbreak.service.RegistrationService;
import com.mainbreak.service.validation.CommonRegistrationValidationHandler;
import com.mainbreak.service.validation.ProfileRegistrationValidationHandler;
import com.mainbreak.service.validation.RegistrationValidationHandler;
import com.mainbreak.service.validation.UniqueRegistrationValidationHandler;
import com.mainbreak.view.CLI;
import com.mainbreak.view.action.CreateAppointmentAction;
import com.mainbreak.view.action.CreateDoctorAction;
import com.mainbreak.view.action.CreatePatientAction;
import com.mainbreak.view.action.ListAppointmentsAction;
import com.mainbreak.view.action.ListDoctorsAction;
import com.mainbreak.view.action.ListPatientsAction;
import com.mainbreak.view.action.LoggingActionDecorator;

import java.security.SecureRandom;

/**
 * Ponto de entrada: monta as dependências e o menu interativo.
 *
 * @note Papel no Padrão: Client (Factory Method e Command)
 */
public class Main {

    public static void main(String[] args) {

        DoctorRepository doctorRepository = new DoctorRepository();
        PatientRepository patientRepository = new PatientRepository();
        AppointmentRepository appointmentRepository = new AppointmentRepository();

        PasswordHasher passwordHasher = new Pbkdf2PasswordHasher(new SecureRandom());

        RegistrationValidationHandler validationChain = createRegistrationValidationChain(doctorRepository, patientRepository);
        RegistrationService registrationService = new RegistrationService(doctorRepository, patientRepository, new DoctorFactory(), new PatientFactory(), passwordHasher, validationChain);

        DoctorController controllerDoctor = new DoctorController(doctorRepository, registrationService);
        PatientController controllerPatient = new PatientController(patientRepository, registrationService);
        AppointmentController controllerAppointment =
                new AppointmentController(appointmentRepository, doctorRepository, patientRepository);

        CadastroFacade cadastroFacade = new CadastroFacade(controllerDoctor, controllerPatient);

        CLI menuCLI = new CLI();

        menuCLI.addOption(1, "Cadastrar Medico", new LoggingActionDecorator(new CreateDoctorAction(cadastroFacade)));
        menuCLI.addOption(2, "Cadastrar Paciente", new LoggingActionDecorator(new CreatePatientAction(cadastroFacade)));
        menuCLI.addOption(3, "Listar Medicos", new LoggingActionDecorator(new ListDoctorsAction(controllerDoctor)));
        menuCLI.addOption(4, "Listar Pacientes", new LoggingActionDecorator(new ListPatientsAction(controllerPatient)));
        menuCLI.addOption(5, "Cadastrar Consulta", new LoggingActionDecorator(new CreateAppointmentAction(controllerAppointment)));
        menuCLI.addOption(6, "Listar Consultas", new LoggingActionDecorator(new ListAppointmentsAction(controllerAppointment)));

        menuCLI.startCLI();
    }

    private static RegistrationValidationHandler createRegistrationValidationChain(DoctorRepository doctorRepository, PatientRepository patientRepository) {

        RegistrationValidationHandler common = new CommonRegistrationValidationHandler();
        RegistrationValidationHandler profile = new ProfileRegistrationValidationHandler();
        RegistrationValidationHandler unique = new UniqueRegistrationValidationHandler(doctorRepository, patientRepository);

        common.setNext(profile);
        profile.setNext(unique);

        return common;
    }
}
