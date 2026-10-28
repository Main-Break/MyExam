package com.mainbreak.controller;

import com.mainbreak.model.User;
import com.mainbreak.service.AdminService;

import java.util.List;

public class AdminController {
    private final AdminService adminService;

    // Recebe o serviço que aplica as regras de acesso às operações administrativas.
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Permite que somente um administrador atribua o perfil médico ao criar uma conta de médico.
    public User registerDoctor(User currentUser, String name, String CPF, String email,
                               String password, String crm, String specialty) {
        return adminService.registerDoctor(currentUser, name, CPF, email, password, crm, specialty);
    }

    // Permite que somente um administrador consulte a lista de contas e seus perfis.
    public List<User> listUsers(User currentUser) {
        return adminService.listUsers(currentUser);
    }
}
