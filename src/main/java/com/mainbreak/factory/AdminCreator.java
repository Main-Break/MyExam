package com.mainbreak.factory;

import com.mainbreak.model.Admin;
import com.mainbreak.model.User;

public class AdminCreator extends UserCreator {
    // Constrói a conta administrativa inicial sem expor criação de admin no cadastro público.
    @Override
    public User createUser(UserRegistration registration) {
        return new Admin(
                registration.getName(),
                registration.getCPF(),
                registration.getEmail(),
                registration.getPasswordHash()
        );
    }
}
