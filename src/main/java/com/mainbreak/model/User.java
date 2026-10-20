package com.mainbreak.model;

import java.util.UUID;

public class User {
    // UUID-v4, não utilizar um ID incremental!
    private String ID;

    // Informações básicas;
    private String name;

    // Salvar apenas com números, em pontuações.
    private String CPF;
    private String email;

    // Só salvar em Hash, a mesma não deve ser salva pura.
    private String passwordHash;

    // "doctor" ou "patient" é usado pela Factory.
    private String type;

    protected User(String name, String CPF, String email, String passwordHash, String type) {
        this.ID = UUID.randomUUID().toString();
        this.name = name;
        this.CPF = CPF;
        this.email = email;
        this.passwordHash = passwordHash;
        this.type = type;
    }

    public String getId() {
        return ID;
    }

}

