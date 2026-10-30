package com.mainbreak.model;

/**
 * Define o método de fábrica que cada tipo de usuário implementa.
 *
 * @note Papel no Padrão: Creator (Factory Method)
 */
public abstract class UserFactory<T extends User> {

    // Metodo fábrica: cada Creator concreto decide qual Product instanciar.
    public abstract T createUser(UserRegistration registration);
}