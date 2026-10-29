package com.mainbreak.model;

public abstract class UserFactory<T extends User> {

    // Metodo fábrica: cada Creator concreto decide qual Product instanciar.
    public abstract T createUser(UserRegistration registration);
}