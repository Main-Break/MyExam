package com.mainbreak;

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
import com.mainbreak.view.action.CreateDoctorAction;
import com.mainbreak.view.action.CreatePatientAction;
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
        RegistrationValidationHandler validationChain = createRegistrationValidationChain(
                doctorRepository, patientRepository);
        RegistrationService registrationService = new RegistrationService(
                doctorRepository,
                patientRepository,
                new DoctorFactory(),
                new PatientFactory(),
                passwordHasher,
                validationChain
        );

        DoctorController controllerDoctor = new DoctorController(doctorRepository, registrationService);
        PatientController controllerPatient = new PatientController(registrationService);
        // AppointmentController controllerAppointment = new AppointmentController(appointmentRepository, doctorRepository, patientRepository);

        CLI menuCLI = new CLI();

        menuCLI.addOption(1, "Cadastrar Medico", new LoggingActionDecorator(new CreateDoctorAction(controllerDoctor)));
        menuCLI.addOption(2, "Cadastrar Paciente", new LoggingActionDecorator(new CreatePatientAction(controllerPatient)));
        // menuCLI.addOption(3, "Cadastrar Consulta", new CreateAppointmentAction(controllerAppointment));

        menuCLI.startCLI();
    }

    // Monta a cadeia de validação e injeta os repositórios usados pelo último elo.
    private static RegistrationValidationHandler createRegistrationValidationChain(
            DoctorRepository doctorRepository, PatientRepository patientRepository) {
        RegistrationValidationHandler common = new CommonRegistrationValidationHandler();
        RegistrationValidationHandler profile = new ProfileRegistrationValidationHandler();
        RegistrationValidationHandler unique =
                new UniqueRegistrationValidationHandler(doctorRepository, patientRepository);
        common.setNext(profile);
        profile.setNext(unique);
        return common;
    }
}
