package com.mainbreak.factory;

import com.mainbreak.model.Patient;
import com.mainbreak.model.User;

public class PatientCreator extends UserCreator {
    // Constrói somente pacientes a partir dos dados de cadastro já validados.
    @Override
    public User createUser(UserRegistration registration) {
        return new Patient(
                registration.getName(),
                registration.getCPF(),
                registration.getEmail(),
                registration.getPasswordHash(),
                registration.getBirthDate()
        );
    }
}
