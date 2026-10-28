package com.mainbreak.factory;

import com.mainbreak.model.User;

// A classe abstrata define o método createUser, implementado pelos creators concretos de cada perfil.
public abstract class UserCreator {
    // Define o ponto de criação que cada factory concreta implementa para seu tipo de usuário.
    public abstract User createUser(UserRegistration registration);
}
