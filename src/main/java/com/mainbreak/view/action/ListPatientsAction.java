package com.mainbreak.view.action;

import com.mainbreak.controller.PatientController;
import com.mainbreak.model.Patient;

import java.util.List;
import java.util.Scanner;

/**
 * Lista os pacientes cadastrados no sistema.
 *
 * @note Papel no Padrão: Concrete Command (Command)
 */
public class ListPatientsAction implements MenuAction {

    private final PatientController patientController;

    public ListPatientsAction(PatientController patientController) {
        this.patientController = patientController;
    }

    @Override
    public void execute(Scanner scanner) {
        List<Patient> patients = patientController.list();

        System.out.println("\n=== PACIENTES CADASTRADOS ===");
        if (patients.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
            return;
        }

        for (Patient patient : patients) {
            System.out.printf("ID: %s | Nome: %s | CPF: %s | Nascimento: %s | Email: %s%n",
                    patient.getID(), patient.getName(), patient.getCPF(), patient.getBirthDate(), patient.getEmail());
        }
    }
}
