package com.mainbreak.view.action;

import com.mainbreak.controller.AppointmentController;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Lê os dados da consulta pelo terminal e aciona o agendamento.
 *
 * @note Papel no Padrão: Concrete Command (Command)
 */
public class CreateAppointmentAction implements MenuAction {

    private final AppointmentController appointmentController;

    public CreateAppointmentAction(AppointmentController appointmentController) {
        this.appointmentController = appointmentController;
    }

    @Override
    public void execute(Scanner scanner) {

        System.out.println("\n=== CADASTRO DE CONSULTA ===");
        System.out.println("Use \"Listar Medicos\" e \"Listar Pacientes\" para ver os IDs disponíveis.");

        System.out.print("ID do médico: ");
        String doctorId = scanner.nextLine();

        System.out.print("ID do paciente: ");
        String patientId = scanner.nextLine();

        LocalDateTime dataTime = readDataTime(scanner);

        System.out.print("Observações: ");
        String notes = scanner.nextLine();

        appointmentController.schedule(doctorId, patientId, dataTime, notes);

        System.out.println("Consulta cadastrada com sucesso!");
    }

    // Repete a leitura para que uma data fora do formato não interrompa o cadastro.
    private LocalDateTime readDataTime(Scanner scanner) {
        while (true) {
            System.out.print("Data e hora (AAAA-MM-DDTHH:MM): ");
            try {
                return LocalDateTime.parse(scanner.nextLine());
            } catch (DateTimeParseException exception) {
                System.out.println("Data inválida. Use o formato AAAA-MM-DDTHH:MM.");
            }
        }
    }
}
