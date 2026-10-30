package com.mainbreak.view.action;

import com.mainbreak.controller.AppointmentController;
import com.mainbreak.model.Appointment;

import java.util.List;
import java.util.Scanner;

/**
 * Lista as consultas agendadas no sistema.
 *
 * @note Papel no Padrão: Concrete Command (Command)
 */
public class ListAppointmentsAction implements MenuAction {

    private final AppointmentController appointmentController;

    public ListAppointmentsAction(AppointmentController appointmentController) {
        this.appointmentController = appointmentController;
    }

    @Override
    public void execute(Scanner scanner) {
        List<Appointment> appointments = appointmentController.list();

        System.out.println("\n=== CONSULTAS AGENDADAS ===");
        if (appointments.isEmpty()) {
            System.out.println("Nenhuma consulta cadastrada.");
            return;
        }

        for (Appointment appointment : appointments) {
            System.out.printf("ID: %s | Médico: %s | Paciente: %s | Data: %s | Status: %s%n",
                    appointment.getID(),
                    appointment.getDoctor().getName(),
                    appointment.getPatient().getName(),
                    appointment.getDataTime(),
                    appointment.getStatus().getLabel());
        }
    }
}
