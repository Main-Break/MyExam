package com.mainbreak.service;

import com.mainbreak.exception.ValidationException;
import com.mainbreak.model.Doctor;
import com.mainbreak.model.Patient;
import com.mainbreak.model.UserFactory;
import com.mainbreak.model.UserRegistration;
import com.mainbreak.repository.DoctorRepository;
import com.mainbreak.repository.PatientRepository;
import com.mainbreak.security.PasswordHasher;
import com.mainbreak.service.validation.RegistrationValidationHandler;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;

public class RegistrationService {
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final UserFactory<Doctor> doctorFactory;
    private final UserFactory<Patient> patientFactory;
    private final PasswordHasher passwordHasher;
    private final RegistrationValidationHandler validationChain;

    // Recebe repositórios, factories, hasher e cadeia para manter dependências explícitas.
    public RegistrationService(DoctorRepository doctorRepository,
                               PatientRepository patientRepository,
                               UserFactory<Doctor> doctorFactory,
                               UserFactory<Patient> patientFactory,
                               PasswordHasher passwordHasher,
                               RegistrationValidationHandler validationChain) {
        this.doctorRepository = Objects.requireNonNull(doctorRepository);
        this.patientRepository = Objects.requireNonNull(patientRepository);
        this.doctorFactory = Objects.requireNonNull(doctorFactory);
        this.patientFactory = Objects.requireNonNull(patientFactory);
        this.passwordHasher = Objects.requireNonNull(passwordHasher);
        this.validationChain = Objects.requireNonNull(validationChain);
    }

    // Monta, valida e salva um médico usando o Builder, a CoR e o Factory Method.
    public Doctor createDoctor(String name, String CPF, String email, String password,
                               String crm, String specialty) {
        UserRegistration registration = UserRegistration.builder()
                .name(normalize(name))
                .CPF(normalize(CPF))
                .email(normalizeEmail(email))
                .passwordHash(hashPassword(password))
                .crm(normalize(crm))
                .specialty(normalize(specialty))
                .buildDoctor();
        validationChain.validate(registration);
        Doctor doctor = doctorFactory.createUser(registration);
        doctorRepository.add(doctor);
        return doctor;
    }

    // Monta, valida e salva um paciente usando o Builder, a CoR e o Factory Method.
    public Patient createPatient(String name, String CPF, String email, String password,
                                 LocalDate birthDate) {
        UserRegistration registration = UserRegistration.builder()
                .name(normalize(name))
                .CPF(normalize(CPF))
                .email(normalizeEmail(email))
                .passwordHash(hashPassword(password))
                .birthDate(birthDate)
                .buildPatient();
        validationChain.validate(registration);
        Patient patient = patientFactory.createUser(registration);
        patientRepository.add(patient);
        return patient;
    }

    // Valida a senha original antes de passar ao hasher e guardar somente o resultado.
    private String hashPassword(String password) {
        if (password == null || password.length() < 8) {
            throw new ValidationException("A senha deve ter pelo menos 8 caracteres.");
        }
        return passwordHasher.hash(password);
    }

    // Remove espaços externos dos campos textuais que não têm normalização própria.
    private String normalize(String value) {
        return value == null ? null : value.trim();
    }

    // Normaliza e-mail para que buscas e verificações de duplicidade ignorem a caixa.
    private String normalizeEmail(String email) {
        return email == null ? null : email.trim().toLowerCase(Locale.ROOT);
    }
}
