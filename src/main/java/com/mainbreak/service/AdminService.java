package com.mainbreak.service;

import com.mainbreak.exception.ValidationException;
import com.mainbreak.model.User;
import com.mainbreak.model.UserRole;

import java.util.List;

public class AdminService {
    private final AuthService authService;

    // Recebe o serviço de autenticação para centralizar operações protegidas de administração.
    public AdminService(AuthService authService) {
        this.authService = authService;
    }

    // Cria um médico somente após confirmar que o solicitante tem a role ADMIN.
    public User registerDoctor(User actor, String name, String CPF, String email,
                               String password, String crm, String specialty) {
        requireAdmin(actor);
        return authService.registerDoctor(name, CPF, email, password, crm, specialty);
    }

    // Retorna a lista de contas somente para uma sessão administrativa válida.
    public List<User> listUsers(User actor) {
        requireAdmin(actor);
        return authService.listUsers();
    }

    // Impede que contas de paciente ou médico executem operações administrativas.
    private void requireAdmin(User actor) {
        if (actor == null || actor.getRole() != UserRole.ADMIN) {
            throw new ValidationException("Acesso permitido somente para administradores.");
        }
    }
}
