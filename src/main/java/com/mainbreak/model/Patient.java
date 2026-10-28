package com.mainbreak.model;

import java.time.LocalDate;

// como o User é uma classe genérica, o Patient herda dela, e adiciona apenas o atributo de data de nascimento.
public class Patient extends User{
    private LocalDate birthDate;

    // Inicializa a conta com a role de paciente e guarda sua data de nascimento.
    public Patient(String name, String CPF, String email, String passwordHash, LocalDate birthDate) {
        super(name, CPF, email, passwordHash, UserRole.PATIENT);
        this.birthDate = birthDate;
    }

    // Retorna a data de nascimento armazenada no perfil do paciente.
    public LocalDate getBirthDate() {
        return birthDate;
    }
}
