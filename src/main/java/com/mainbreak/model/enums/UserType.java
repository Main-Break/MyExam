package com.mainbreak.model.enums;

public enum UserType {
    DOCTOR("Médico"),
    NURSE("Enfermeiro"),
    PATIENT("Paciente");

    private final String label;

    UserType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}