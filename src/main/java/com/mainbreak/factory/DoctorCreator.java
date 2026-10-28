package com.mainbreak.factory;

import com.mainbreak.model.Doctor;
import com.mainbreak.model.User;

public class DoctorCreator extends UserCreator {
    // Constrói médicos quando o cadastro é autorizado pelo fluxo administrativo.
    @Override
    public User createUser(UserRegistration registration) {
        return new Doctor(
                registration.getName(),
                registration.getCPF(),
                registration.getEmail(),
                registration.getPasswordHash(),
                registration.getCrm(),
                registration.getSpecialty()
        );
    }
}
