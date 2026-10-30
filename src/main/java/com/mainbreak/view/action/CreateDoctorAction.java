package com.mainbreak.view.action;

import com.mainbreak.controller.CadastroFacade;
import java.util.Scanner;


public class CreateDoctorAction implements MenuAction {

    private final CadastroFacade cadastroFacade;

    public CreateDoctorAction(CadastroFacade cadastroFacade) {
        this.cadastroFacade = cadastroFacade;
    }

    @Override
    public void execute(Scanner scanner) {

        System.out.println("\n=== CADASTRO DE MÉDICO ===");

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

        cadastroFacade.cadastrarMedico(name, cpf, email, password, crm, specialty);

        System.out.println("Médico cadastrado com sucesso!");
    }
}
