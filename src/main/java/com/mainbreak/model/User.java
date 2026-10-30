package com.mainbreak.model;

import com.mainbreak.interfaces.iUser;
import com.mainbreak.model.enums.UserType;

import java.util.UUID;

/**
 * Dados e regras comuns a todo usuário do sistema.
 *
 * @note Papel no Padrão: Product (Factory Method)
 */
public abstract class User implements iUser {
    // UUID-v4, não utilizar um ID incremental!
    protected String ID;

    // Informações básicas;
    protected String name;

    // Salvar apenas com números, em pontuações.
    protected String CPF;
    protected String email;

    // Só salvar em Hash, a mesma não deve ser salva pura.
    protected String passwordHash;

    // "doctor" ou "patient" é usado pela Factory.
    protected UserType type;

    protected User(String name, String CPF, String email, String passwordHash, UserType type) {
        this.ID = UUID.randomUUID().toString();
        this.name = name;
        this.CPF = CPF;
        this.email = email;
        this.passwordHash = passwordHash;
        this.type = type;
    }

    @Override
    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getCPF() {
        return CPF;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public void edit() {

    }
}
