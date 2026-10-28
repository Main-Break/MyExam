package com.mainbreak.controller;

import com.mainbreak.model.User;
import com.mainbreak.service.AuthService;

import java.time.LocalDate;

public class AuthController {
    private final AuthService authService;

    // Recebe o serviço que executa as regras de cadastro e autenticação.
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Encaminha o formulário público ao serviço, que sempre cria um paciente.
    public User registerPatient(String name, String CPF, String email, String password,
                                LocalDate birthDate) {
        return authService.registerPatient(name, CPF, email, password, birthDate);
    }

    // Encaminha as credenciais e retorna a conta autenticada para decidir o menu por role.
    public User login(String email, String password) {
        return authService.login(email, password);
    }
}
