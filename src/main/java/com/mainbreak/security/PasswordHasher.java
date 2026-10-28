package com.mainbreak.security;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Objects;

public final class PasswordHasher {
    private static final int ITERATIONS = 120_000;
    private static final int SALT_LENGTH = 16;
    private static final int HASH_LENGTH = 256;
    private static final SecureRandom RANDOM = new SecureRandom();

    // Impede instâncias porque todas as operações de hash são utilitários estáticos.
    private PasswordHasher() {
    }

    // Gera um hash PBKDF2 com salt aleatório para que senhas não sejam armazenadas em texto puro.
    public static String hash(String password) {
        Objects.requireNonNull(password, "A senha não pode ser nula.");
        byte[] salt = new byte[SALT_LENGTH];
        RANDOM.nextBytes(salt);
        byte[] hash = derive(password, salt, ITERATIONS);
        return ITERATIONS + ":" + Base64.getEncoder().encodeToString(salt)
                + ":" + Base64.getEncoder().encodeToString(hash);
    }

    // Confere a senha usando o salt e o número de iterações gravados junto ao hash.
    public static boolean matches(String password, String encodedHash) {
        Objects.requireNonNull(password, "A senha não pode ser nula.");
        Objects.requireNonNull(encodedHash, "O hash não pode ser nulo.");
        String[] parts = encodedHash.split(":", -1);
        if (parts.length != 3) {
            throw new IllegalArgumentException("Formato de hash de senha inválido.");
        }

        try {
            int iterations = Integer.parseInt(parts[0]);
            byte[] salt = Base64.getDecoder().decode(parts[1]);
            byte[] expectedHash = Base64.getDecoder().decode(parts[2]);
            if (iterations <= 0 || salt.length == 0 || expectedHash.length == 0) {
                throw new IllegalArgumentException("Formato de hash de senha inválido.");
            }
            byte[] actualHash = derive(password, salt, iterations);
            return MessageDigest.isEqual(expectedHash, actualHash);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Formato de hash de senha inválido.", exception);
        }
    }

    // Deriva o valor PBKDF2 e transforma falhas de configuração da JVM em erro explícito.
    private static byte[] derive(String password, byte[] salt, int iterations) {
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, HASH_LENGTH);
        try {
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
                    .generateSecret(spec)
                    .getEncoded();
        } catch (GeneralSecurityException exception) {
            throw new IllegalStateException("Não foi possível gerar ou verificar o hash da senha.", exception);
        } finally {
            spec.clearPassword();
        }
    }
}
