package com.mainbreak.security;

public interface PasswordHasher {
    // Gera e valida hashes sem acoplar serviços à implementação criptográfica.
    String hash(String password);

    boolean matches(String password, String encodedHash);
}
