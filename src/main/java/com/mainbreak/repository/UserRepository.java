package com.mainbreak.repository;

import com.mainbreak.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    // Busca uma conta pelo e-mail normalizado para validar login e impedir duplicidade.
    Optional<User> findByEmail(String email);

    // Busca uma conta pelo CPF para impedir que o mesmo documento crie várias contas.
    Optional<User> findByCPF(String CPF);

    // Busca uma conta pelo CRM para impedir registros profissionais duplicados.
    Optional<User> findDoctorByCrm(String crm);

    // Persiste o usuário na implementação de armazenamento escolhida.
    void save(User user);

    // Retorna uma cópia da lista de contas para a tela administrativa.
    List<User> findAll();
}
