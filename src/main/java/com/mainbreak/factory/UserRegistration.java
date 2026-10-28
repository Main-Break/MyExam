package com.mainbreak.factory;

import com.mainbreak.model.UserRole;

import java.time.LocalDate;

public class UserRegistration {
    private final String name;
    private final String CPF;
    private final String email;
    private final String passwordHash;
    private final LocalDate birthDate;
    private final String crm;
    private final String specialty;
    private final UserRole role;

    // Guarda os dados já reunidos pelo Builder para validar e criar um perfil concreto.
    private UserRegistration(Builder builder, UserRole role) {
        this.name = builder.name;
        this.CPF = builder.CPF;
        this.email = builder.email;
        this.passwordHash = builder.passwordHash;
        this.birthDate = builder.birthDate;
        this.crm = builder.crm;
        this.specialty = builder.specialty;
        this.role = role;
    }

    // Inicia a montagem dos dados comuns antes de escolher o tipo de conta a construir.
    public static Builder builder() {
        return new Builder();
    }

    // Fornece o nome para que o creator construa o modelo de usuário.
    public String getName() {
        return name;
    }

    // Fornece o CPF para validação de unicidade e criação do modelo.
    public String getCPF() {
        return CPF;
    }

    // Fornece o e-mail usado como identificador de login.
    public String getEmail() {
        return email;
    }

    // Fornece ao modelo apenas a senha já protegida por hash.
    public String getPasswordHash() {
        return passwordHash;
    }

    // Fornece a data de nascimento necessária para criar um paciente.
    public LocalDate getBirthDate() {
        return birthDate;
    }

    // Fornece o CRM necessário para criar um médico.
    public String getCrm() {
        return crm;
    }

    // Fornece a especialidade necessária para criar um médico.
    public String getSpecialty() {
        return specialty;
    }

    // Identifica o perfil para que a cadeia aplique suas validações específicas.
    public UserRole getRole() {
        return role;
    }

    public static class Builder {
        private String name;
        private String CPF;
        private String email;
        private String passwordHash;
        private LocalDate birthDate;
        private String crm;
        private String specialty;

        private Builder() {
        }

        // Define o nome compartilhado por todos os perfis de usuário.
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        // Define o CPF compartilhado por todos os perfis de usuário.
        public Builder CPF(String CPF) {
            this.CPF = CPF;
            return this;
        }

        // Define o e-mail usado para login e busca da conta.
        public Builder email(String email) {
            this.email = email;
            return this;
        }

        // Define somente o hash, nunca a senha original, no objeto de cadastro.
        public Builder passwordHash(String passwordHash) {
            this.passwordHash = passwordHash;
            return this;
        }

        // Define a data de nascimento usada no cadastro de paciente.
        public Builder birthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        // Define o registro profissional usado no cadastro de médico.
        public Builder crm(String crm) {
            this.crm = crm;
            return this;
        }

        // Define a especialidade usada no cadastro de médico.
        public Builder specialty(String specialty) {
            this.specialty = specialty;
            return this;
        }

        // Constrói os dados de paciente; a cadeia verificará data de nascimento e campos comuns.
        public UserRegistration buildPatient() {
            return new UserRegistration(this, UserRole.PATIENT);
        }

        // Constrói os dados de médico; a cadeia verificará CRM e especialidade.
        public UserRegistration buildDoctor() {
            return new UserRegistration(this, UserRole.DOCTOR);
        }

        // Constrói os dados do admin inicial sem permitir escolhê-lo no cadastro público.
        public UserRegistration buildAdmin() {
            return new UserRegistration(this, UserRole.ADMIN);
        }
    }
}
