package com.mainbreak.model;

import com.mainbreak.model.enums.UserType;

import java.time.LocalDate;

/**
 * Imutável com os dados necessários para criar um usuário.
 *
 * @note Papel no Padrão: Product montado pelo Builder e consumido pelo Factory Method.
 */
public final class UserRegistration {
    private final String name;
    private final String CPF;
    private final String email;
    private final String passwordHash;
    private final UserType type;
    private final LocalDate birthDate;
    private final String crm;
    private final String specialty;

    /**
     * Copia os dados reunidos pelo Builder para um registro imutável.
     *
     * @note Papel no Padrão: Director do objeto construído, finalizado pelo Builder aninhado.
     */
    private UserRegistration(Builder builder, UserType type) {
        this.name = builder.name;
        this.CPF = builder.CPF;
        this.email = builder.email;
        this.passwordHash = builder.passwordHash;
        this.type = type;
        this.birthDate = builder.birthDate;
        this.crm = builder.crm;
        this.specialty = builder.specialty;
    }

    // Inicia a montagem dos dados sem exigir um construtor grande com campos opcionais.
    public static Builder builder() {
        return new Builder();
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public UserType getType() {
        return type;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getCrm() {
        return crm;
    }

    public String getSpecialty() {
        return specialty;
    }

    /**
     * Monta um registro de usuário passo a passo.
     *
     * @note Papel no Padrão: Builder concreto que configura os campos comuns e opcionais do cadastro.
     */
    public static final class Builder {
        private String name;
        private String CPF;
        private String email;
        private String passwordHash;
        private LocalDate birthDate;
        private String crm;
        private String specialty;

        private Builder() {
        }

        // Define o nome compartilhado por todos os perfis.
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        // Define o CPF compartilhado por todos os perfis.
        public Builder CPF(String CPF) {
            this.CPF = CPF;
            return this;
        }

        // Define o e-mail usado para identificar o cadastro.
        public Builder email(String email) {
            this.email = email;
            return this;
        }

        // Define apenas o hash da senha para não guardar a senha original no registro.
        public Builder passwordHash(String passwordHash) {
            this.passwordHash = passwordHash;
            return this;
        }

        // Define a data de nascimento opcional, obrigatória para pacientes.
        public Builder birthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        // Define o CRM opcional, obrigatório para médicos.
        public Builder crm(String crm) {
            this.crm = crm;
            return this;
        }

        // Define a especialidade opcional, obrigatória para médicos.
        public Builder specialty(String specialty) {
            this.specialty = specialty;
            return this;
        }

        // Finaliza os campos comuns e profissionais com o tipo médico.
        public UserRegistration buildDoctor() {
            logBuild(UserType.DOCTOR);
            return new UserRegistration(this, UserType.DOCTOR);
        }

        // Finaliza os campos comuns e a data de nascimento com o tipo paciente.
        public UserRegistration buildPatient() {
            logBuild(UserType.PATIENT);
            return new UserRegistration(this, UserType.PATIENT);
        }

        // Finaliza os campos comuns com o tipo enfermeiro já suportado pela Factory Method.
        public UserRegistration buildNurse() {
            logBuild(UserType.NURSE);
            return new UserRegistration(this, UserType.NURSE);
        }

        // Registra no terminal qual perfil está sendo montado sem imprimir dados pessoais.
        private void logBuild(UserType type) {
            System.out.println("[Builder] Montando cadastro: " + type.getLabel() + ".");
        }
    }
}
