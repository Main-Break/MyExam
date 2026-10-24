package com.mainbreak.model;

public abstract class UserFactory {

    // Metodo fábrica: cada Creator concreto decide qual Product instanciar.
    public abstract User createUser(String name, String CPF, String email, String passwordHash);
}