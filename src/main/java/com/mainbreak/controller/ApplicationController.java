package com.mainbreak.controller;

import com.mainbreak.exception.ValidationException;
import com.mainbreak.model.User;
import com.mainbreak.model.UserRole;
import com.mainbreak.view.Menu;

import java.time.LocalDate;
import java.util.List;

public class ApplicationController {
    private final Menu menu;
    private final AuthController authController;
    private final AdminController adminController;
    private User currentUser;

    // Conecta a tela de terminal aos controllers responsáveis por autenticação e administração.
    public ApplicationController(Menu menu, AuthController authController,
                                 AdminController adminController) {
        this.menu = menu;
        this.authController = authController;
        this.adminController = adminController;
    }

    // Mantém a aplicação no menu público ou no menu correspondente à role autenticada.
    public void run() {
        boolean running = true;
        while (running) {
            try {
                if (currentUser == null) {
                    running = showPublicMenu();
                } else {
                    showRoleMenu();
                }
            } catch (ValidationException exception) {
                menu.showMessage("Não foi possível concluir: " + exception.getMessage());
            }
        }
        menu.showMessage("Aplicação encerrada.");
    }

    // Exibe login, cadastro público de paciente e opção para encerrar o programa.
    private boolean showPublicMenu() {
        int option = menu.readOption(
                "\n1 - Entrar\n2 - Criar conta de paciente\n0 - Sair\nEscolha: ", 0, 2);
        switch (option) {
            case 1 -> login();
            case 2 -> registerPatient();
            case 0 -> {
                return false;
            }
            default -> throw new IllegalStateException("Opção pública fora do intervalo validado.");
        }
        return true;
    }

    // Solicita credenciais e guarda a sessão para encaminhar a pessoa ao menu da sua role.
    private void login() {
        String email = menu.readText("E-mail: ");
        String password = menu.readPassword("Senha: ");
        currentUser = authController.login(email, password);
        menu.showMessage("Login realizado. Bem-vindo(a), " + currentUser.getName() + ".");
    }

    // Recolhe dados de cadastro e chama a operação que cria exclusivamente pacientes.
    private void registerPatient() {
        String name = menu.readText("Nome: ");
        String cpf = menu.readText("CPF (11 números): ");
        String email = menu.readText("E-mail: ");
        String password = menu.readPassword("Senha (mínimo 8 caracteres): ");
        LocalDate birthDate = menu.readDate("Data de nascimento (AAAA-MM-DD): ");
        User patient = authController.registerPatient(name, cpf, email, password, birthDate);
        menu.showMessage("Conta de paciente criada para " + patient.getName() + ".");
    }

    // Escolhe as ações disponíveis com base na role armazenada na sessão autenticada.
    private void showRoleMenu() {
        if (currentUser.getRole() == UserRole.ADMIN) {
            showAdminMenu();
        } else if (currentUser.getRole() == UserRole.DOCTOR) {
            showDoctorMenu();
        } else {
            showPatientMenu();
        }
    }

    // Apresenta ao administrador cadastro de médicos, listagem de contas e encerramento de sessão.
    private void showAdminMenu() {
        int option = menu.readOption(
                "\nADMIN - " + currentUser.getName()
                        + "\n1 - Cadastrar médico\n2 - Listar contas\n0 - Sair da conta\nEscolha: ",
                0, 2);
        switch (option) {
            case 1 -> registerDoctor();
            case 2 -> listUsers();
            case 0 -> logout();
            default -> throw new IllegalStateException("Opção administrativa fora do intervalo validado.");
        }
    }

    // Recolhe os dados profissionais e solicita ao controller a criação autorizada de um médico.
    private void registerDoctor() {
        String name = menu.readText("Nome do médico: ");
        String cpf = menu.readText("CPF (11 números): ");
        String email = menu.readText("E-mail: ");
        String password = menu.readPassword("Senha inicial (mínimo 8 caracteres): ");
        String crm = menu.readText("CRM: ");
        String specialty = menu.readText("Especialidade: ");
        User doctor = adminController.registerDoctor(
                currentUser, name, cpf, email, password, crm, specialty);
        menu.showMessage("Médico cadastrado: " + doctor.getName() + ".");
    }

    // Mostra nome, e-mail e role de cada conta sem revelar dados de autenticação.
    private void listUsers() {
        List<User> users = adminController.listUsers(currentUser);
        menu.showMessage("\nContas cadastradas:");
        for (User user : users) {
            menu.showMessage(user.getName() + " | " + user.getEmail()
                    + " | " + user.getRole());
        }
    }

    // Informa ao médico que funções clínicas ainda não foram implementadas e oferece logout.
    private void showDoctorMenu() {
        menu.showMessage("\nMÉDICO - " + currentUser.getName()
                + "\nFunções de consultas ainda não estão implementadas.");
        menu.readOption("0 - Sair da conta\nEscolha: ", 0, 0);
        logout();
    }

    // Informa ao paciente que funções de consulta ainda não foram implementadas e oferece logout.
    private void showPatientMenu() {
        menu.showMessage("\nPACIENTE - " + currentUser.getName()
                + "\nFunções de consultas ainda não estão implementadas.");
        menu.readOption("0 - Sair da conta\nEscolha: ", 0, 0);
        logout();
    }

    // Encerra a sessão atual para retornar ao menu público.
    private void logout() {
        currentUser = null;
        menu.showMessage("Sessão encerrada.");
    }
}
