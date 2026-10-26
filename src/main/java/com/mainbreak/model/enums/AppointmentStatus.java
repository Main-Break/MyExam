package com.mainbreak.model.enums;

public enum AppointmentStatus {
    WAITING("Aguardando"),
    IN_PROGRESS("Em consulta"),
    CANCELLED("Cancelado"),
    DELETED("Deletado"),
    NO_SHOW("Ausência");

    private final String label;

    AppointmentStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}