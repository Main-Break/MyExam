package com.mainbreak;

import com.mainbreak.controller.AdminController;
import com.mainbreak.controller.ApplicationController;
import com.mainbreak.controller.AuthController;
import com.mainbreak.repository.InMemoryUserRepository;
import com.mainbreak.repository.UserRepository;
import com.mainbreak.service.AdminService;
import com.mainbreak.service.AuthService;
import com.mainbreak.view.Menu;

public class Main {
    // Inicializa dependências, configura o admin opcional e inicia a aplicação de terminal.
    public static void main(String[] args) {
        UserRepository userRepository = new InMemoryUserRepository();
        AuthService authService = new AuthService(userRepository);
        Menu menu = new Menu();

        if (bootstrapAdmin(authService)) {
            menu.showMessage("Conta administrativa inicial configurada.");
        } else {
            menu.showMessage("Admin não configurado. Defina MYEXAM_ADMIN_NAME, "
                    + "MYEXAM_ADMIN_CPF, MYEXAM_ADMIN_EMAIL e MYEXAM_ADMIN_PASSWORD.");
        }

        ApplicationController applicationController = new ApplicationController(
                menu,
                new AuthController(authService),
                new AdminController(new AdminService(authService))
        );
        applicationController.run();
    }

    // Cria o administrador inicial a partir de variáveis de ambiente, nunca do cadastro público.
    private static boolean bootstrapAdmin(AuthService authService) {
        String name = System.getenv("MYEXAM_ADMIN_NAME");
        String cpf = System.getenv("MYEXAM_ADMIN_CPF");
        String email = System.getenv("MYEXAM_ADMIN_EMAIL");
        String password = System.getenv("MYEXAM_ADMIN_PASSWORD");
        boolean allMissing = isMissing(name) && isMissing(cpf)
                && isMissing(email) && isMissing(password);
        if (allMissing) {
            return false;
        }
        if (isMissing(name) || isMissing(cpf) || isMissing(email) || isMissing(password)) {
            throw new IllegalStateException(
                    "Configure MYEXAM_ADMIN_NAME, MYEXAM_ADMIN_CPF, "
                            + "MYEXAM_ADMIN_EMAIL e MYEXAM_ADMIN_PASSWORD.");
        }
        authService.createInitialAdmin(name, cpf, email, password);
        return true;
    }

    // Trata variáveis ausentes ou vazias como não configuradas.
    private static boolean isMissing(String value) {
        return value == null || value.isBlank();
    }
}
