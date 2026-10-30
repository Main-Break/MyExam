package com.mainbreak.view.action;

import com.mainbreak.controller.DoctorController;
import java.util.Scanner;

/**
 * Lê os dados do médico pelo terminal e aciona o cadastro.
 *
 * @note Papel no Padrão: Concrete Command (Command)
 */
public class CreateDoctorAction implements MenuAction {

    private final DoctorController doctorController;

    public CreateDoctorAction(DoctorController doctorController) {
        this.doctorController = doctorController;
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

        System.out.print("CRM: ");
        String crm = scanner.nextLine();

        System.out.print("Especialidade: ");
        String specialty = scanner.nextLine();

        this.doctorController.create(name, cpf, email, password, crm, specialty);
        System.out.println("Médico cadastrado com sucesso!");
    }
}