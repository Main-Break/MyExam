package com.mainbreak.model;

import java.time.LocalDate;

// como o User é uma classe genérica, o Patient herda dela, e adiciona apenas o atributo de data de nascimento.
public class Patient extends User{
    private LocalDate birthDate;
    public Patient(String name, String CPF, String email, String passwordHash, LocalDate birthDate) {
        super(name, CPF, email, passwordHash, "patient");
        this.birthDate = birthDate;
    }

}
