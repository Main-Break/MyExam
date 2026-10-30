package com.mainbreak.controller;

import com.mainbreak.model.Doctor;
import com.mainbreak.model.Patient;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Classe responsável por centralizar o acesso às operações
 * de cadastro de médicos e pacientes.
 *
 * Utiliza o padrão estrutural Facade para oferecer uma
 * interface simplificada entre as ações do menu e os
 * controladores responsáveis pelos cadastros.
 *
 * A classe recebe os controladores pelo construtor e
 * encaminha as solicitações para cada um deles.
 *
 * @author Stephani
 * @see DoctorController
 * @see PatientController
 * @note Papel no Padrão: Facade (Facade)
 */
public class CadastroFacade {
    
    /**
     * Controlador responsável pelas operações de médicos.
     */
    private final DoctorController doctorController;
    
    /**
     * Controlador responsável pelas operações de pacientes.
     */
    private final PatientController patientController;

    /**
     * Construtor responsável por receber os controladores.
     *
     * @param doctorController controlador de médicos.
     * @param patientController controlador de pacientes.
     */
    public CadastroFacade(DoctorController doctorController, PatientController patientController) {
        this.doctorController = Objects.requireNonNull(doctorController, "O controlador dos médicos é obrigatório");
        this.patientController = Objects.requireNonNull(patientController, "O controlador dos pacientes é obrigatório");
    }

    /**
     * Encaminha os dados recebidos para o cadastro de um médico.
     *
     * @param name nome do médico.
     * @param cpf CPF do médico.
     * @param email e-mail do médico.
     * @param password senha original do médico.
     * @param crm registro profissional do médico.
     * @param specialty especialidade médica.
     * @return objeto Doctor criado pelo serviço de cadastro.
     */
    public Doctor cadastrarMedico(String name, String cpf, String email, String password, String crm, String specialty) {
        System.out.println("[Facade] Encaminhando cadastro de médico para o DoctorController.");
        return doctorController.create(name, cpf, email, password, crm, specialty);
    }

    /**
     * Encaminha os dados recebidos para o cadastro de um paciente.
     *
     * @param name nome do paciente.
     * @param cpf CPF do paciente.
     * @param email e-mail do paciente.
     * @param password senha original do paciente.
     * @param birthDate data de nascimento do paciente.
     * @return objeto Patient criado pelo serviço de cadastro.
     */
    public Patient cadastrarPaciente(String name, String cpf, String email, String password, LocalDate birthDate) {
        System.out.println("[Facade] Encaminhando cadastro de paciente para o PatientController.");
        return patientController.create(name, cpf, email, password, birthDate);
    }
}
