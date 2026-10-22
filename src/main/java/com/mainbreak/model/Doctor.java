package com.mainbreak.model;
// como o User é uma classe genérica, o Doctor herda dela, e adiciona apenas os atributos de CRM e especialidade. 
// o super é usado para chamar o construtor da classe pai.
public class Doctor extends User{
    private String crm;
    private String specialty;
    public Doctor(String name, String CPF, String email, String passwordHash, String crm, String specialty) {
        super(name, CPF, email, passwordHash, "doctor");
        this.crm = crm;
        this.specialty = specialty;
    }
}
