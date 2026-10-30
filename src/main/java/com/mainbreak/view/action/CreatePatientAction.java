package com.mainbreak.view.action;

import com.mainbreak.controller.CadastroFacade;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Lê os dados do paciente pelo terminal e aciona o cadastro.
 *
 * @note Papel no Padrão: Concrete Command (Command)
 */
public class CreatePatientAction implements MenuAction {

    private final CadastroFacade cadastroFacade;

    public CreatePatientAction(CadastroFacade cadastroFacade) {
        this.cadastroFacade = cadastroFacade;
    }

    @Override
    public void execute(Scanner scanner) {

        System.out.println("\n=== CADASTRO DE PACIENTE ===");

        System.out.print("Nome: ");
        String name = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String password = scanner.nextLine();

        LocalDate birthDate = readBirthDate(scanner);

        cadastroFacade.cadastrarPaciente(name, cpf, email, password, birthDate);

        System.out.println("Paciente cadastrado com sucesso!");
    }

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
