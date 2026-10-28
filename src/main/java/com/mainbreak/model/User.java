package com.mainbreak.model;

import com.mainbreak.security.PasswordHasher;

import java.util.UUID;

public class User {
    private final String id;
    private final String name;
    private final String CPF;
    private final String email;
    private final String passwordHash;
    private final UserRole role;

    // Inicializa os dados compartilhados por todos os perfis e gera um identificador UUID.
    protected User(String name, String CPF, String email, String passwordHash, UserRole role) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.CPF = CPF;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    // Retorna o UUID usado para identificar a conta sem depender de um número incremental.
    public String getId() {
        return id;
    }

    // Retorna o nome para exibição em menus e listagens administrativas.
    public String getName() {
        return name;
    }

    // Retorna o CPF para validação de unicidade e administração da conta.
    public String getCPF() {
        return CPF;
    }

    // Retorna o e-mail usado para localizar a conta durante o login.
    public String getEmail() {
        return email;
    }

    // Retorna o perfil que determina quais operações o usuário pode acessar.
    public UserRole getRole() {
        return role;
    }

    // Compara uma senha digitada com o hash sem expor o hash ao controller ou à tela.
    public boolean passwordMatches(String password) {
        return PasswordHasher.matches(password, passwordHash);
    }
}
