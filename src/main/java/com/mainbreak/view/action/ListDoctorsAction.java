package com.mainbreak.view.action;

import com.mainbreak.controller.DoctorController;
import com.mainbreak.model.Doctor;

import java.util.List;
import java.util.Scanner;

/**
 * Lista os médicos cadastrados no sistema.
 *
 * @note Papel no Padrão: Concrete Command (Command)
 */
public class ListDoctorsAction implements MenuAction {

    private final DoctorController doctorController;

    public ListDoctorsAction(DoctorController doctorController) {
        this.doctorController = doctorController;
    }

    @Override
    public void execute(Scanner scanner) {
        List<Doctor> doctors = doctorController.list();

        System.out.println("\n=== MÉDICOS CADASTRADOS ===");
        if (doctors.isEmpty()) {
            System.out.println("Nenhum médico cadastrado.");
            return;
        }

        for (Doctor doctor : doctors) {
            System.out.printf("ID: %s | Nome: %s | CRM: %s | Especialidade: %s | Email: %s%n",
                    doctor.getID(), doctor.getName(), doctor.getCrm(), doctor.getSpecialty(), doctor.getEmail());
        }
    }
}
