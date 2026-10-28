package com.mainbreak.service;

import com.mainbreak.exception.ValidationException;
import com.mainbreak.factory.AdminCreator;
import com.mainbreak.factory.DoctorCreator;
import com.mainbreak.factory.PatientCreator;
import com.mainbreak.factory.UserRegistration;
import com.mainbreak.model.User;
import com.mainbreak.repository.UserRepository;
import com.mainbreak.security.PasswordHasher;
import com.mainbreak.service.validation.CommonRegistrationValidationHandler;
import com.mainbreak.service.validation.ProfileRegistrationValidationHandler;
import com.mainbreak.service.validation.RegistrationValidationHandler;
import com.mainbreak.service.validation.UniqueRegistrationValidationHandler;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public class AuthService {
    private final UserRepository userRepository;
    private final PatientCreator patientCreator = new PatientCreator();
    private final DoctorCreator doctorCreator = new DoctorCreator();
    private final AdminCreator adminCreator = new AdminCreator();
    private final RegistrationValidationHandler registrationValidation;

    // Recebe o repositório para manter regras de conta independentes do armazenamento.
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.registrationValidation = createRegistrationValidationChain(userRepository);
    }

    // Valida e registra um paciente; esse é o único tipo permitido pelo cadastro público.
    public User registerPatient(String name, String CPF, String email, String password,
                                LocalDate birthDate) {
        validatePassword(password);
        String normalizedEmail = normalizeEmail(email);
        String normalizedCpf = CPF == null ? null : CPF.trim();
        UserRegistration registration = UserRegistration.builder()
                .name(name == null ? null : name.trim())
                .CPF(normalizedCpf)
                .email(normalizedEmail)
                .passwordHash(PasswordHasher.hash(password))
                .birthDate(birthDate)
                .buildPatient();
        registrationValidation.validate(registration);
        User patient = patientCreator.createUser(registration);
        userRepository.save(patient);
        return patient;
    }

    // Autentica pelo e-mail e senha, retornando o perfil para direcionar ao menu correto.
    public User login(String email, String password) {
        if (email == null || password == null) {
            throw new ValidationException("E-mail ou senha inválidos.");
        }
        User user = userRepository.findByEmail(normalizeEmail(email))
                .orElseThrow(() -> new ValidationException("E-mail ou senha inválidos."));
        if (!user.passwordMatches(password)) {
            throw new ValidationException("E-mail ou senha inválidos.");
        }
        return user;
    }

    // Registra um médico após a autorização do controller administrativo.
    User registerDoctor(String name, String CPF, String email, String password,
                         String crm, String specialty) {
        validatePassword(password);
        String normalizedEmail = normalizeEmail(email);
        String normalizedCpf = CPF == null ? null : CPF.trim();
        UserRegistration registration = UserRegistration.builder()
                .name(name == null ? null : name.trim())
                .CPF(normalizedCpf)
                .email(normalizedEmail)
                .passwordHash(PasswordHasher.hash(password))
                .crm(crm == null ? null : crm.trim())
                .specialty(specialty == null ? null : specialty.trim())
                .buildDoctor();
        registrationValidation.validate(registration);
        User doctor = doctorCreator.createUser(registration);
        userRepository.save(doctor);
        return doctor;
    }

    // Cria o administrador inicial a partir da configuração protegida de inicialização.
    public User createInitialAdmin(String name, String CPF, String email, String password) {
        validatePassword(password);
        String normalizedEmail = normalizeEmail(email);
        String normalizedCpf = CPF == null ? null : CPF.trim();
        UserRegistration registration = UserRegistration.builder()
                .name(name == null ? null : name.trim())
                .CPF(normalizedCpf)
                .email(normalizedEmail)
                .passwordHash(PasswordHasher.hash(password))
                .buildAdmin();
        registrationValidation.validate(registration);
        User admin = adminCreator.createUser(registration);
        userRepository.save(admin);
        return admin;
    }

    // Lista contas para a visão administrativa sem incluir hashes de senha.
    List<User> listUsers() {
        return userRepository.findAll();
    }

    // Valida a senha em texto de entrada antes de transformá-la em hash.
    private void validatePassword(String password) {
        if (password == null || password.length() < 8) {
            throw new ValidationException("A senha deve ter pelo menos 8 caracteres.");
        }
    }

    // Encadeia regras comuns, validações específicas do perfil e verificações de unicidade.
    private RegistrationValidationHandler createRegistrationValidationChain(
            UserRepository userRepository) {
        RegistrationValidationHandler common = new CommonRegistrationValidationHandler();
        RegistrationValidationHandler profile = new ProfileRegistrationValidationHandler();
        RegistrationValidationHandler unique =
                new UniqueRegistrationValidationHandler(userRepository);
        common.setNext(profile).setNext(unique);
        return common;
    }

    // Normaliza o e-mail para manter buscas e unicidade independentes da caixa.
    private String normalizeEmail(String email) {
        if (email == null) {
            return null;
        }
        return email.trim().toLowerCase(Locale.ROOT);
    }
}
