package com.mainbreak.view.action;

import com.mainbreak.controller.PatientController;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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

        LocalDate birthDate = readBirthDate(scanner);

        this.patientController.create(name, cpf, email, password, birthDate);
        System.out.println("Paciente cadastrado com sucesso!");
    }

    // Repete a leitura para que uma data fora do formato não interrompa o cadastro.
    private LocalDate readBirthDate(Scanner scanner) {
        while (true) {
            System.out.print("Data de nascimento (AAAA-MM-DD): ");
            try {
                return LocalDate.parse(scanner.nextLine());
            } catch (DateTimeParseException exception) {
                System.out.println("Data inválida. Use o formato AAAA-MM-DD.");
            }
        }
    }
}