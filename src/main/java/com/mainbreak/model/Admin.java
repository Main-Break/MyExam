package com.mainbreak.model;

public class Admin extends User {
    // Cria o perfil que pode acessar operações administrativas protegidas.
    public Admin(String name, String CPF, String email, String passwordHash) {
        super(name, CPF, email, passwordHash, UserRole.ADMIN);
    }
}
