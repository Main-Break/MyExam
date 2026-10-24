package com.mainbreak.view.action;

import com.mainbreak.controller.PatientController;
import java.util.Scanner;

public class CreatePatientAction implements MenuAction {

    private final PatientController patientController;

    public CreatePatientAction(PatientController patientController) {
        this.patientController = patientController;
    }

    @Override
    public void execute(Scanner scanner) {
        System.out.print("Nome: ");
        String name = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String password = scanner.nextLine();

        this.patientController.create(name, cpf, email, password);
        System.out.println("Paciente cadastrado com sucesso!");
    }
}