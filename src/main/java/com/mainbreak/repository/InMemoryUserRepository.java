package com.mainbreak.repository;

import com.mainbreak.exception.ValidationException;
import com.mainbreak.model.Doctor;
import com.mainbreak.model.User;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public class InMemoryUserRepository implements UserRepository {
    private final Map<String, User> usersByEmail = new LinkedHashMap<>();

    // Localiza uma conta sem diferenciar letras maiúsculas de minúsculas no e-mail.
    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(usersByEmail.get(normalize(email)));
    }

    // Procura uma conta pelo CPF para validar a unicidade no cadastro.
    @Override
    public Optional<User> findByCPF(String CPF) {
        return usersByEmail.values().stream()
                .filter(user -> user.getCPF().equals(CPF))
                .findFirst();
    }

    // Procura entre médicos o CRM informado para validar sua unicidade.
    @Override
    public Optional<User> findDoctorByCrm(String crm) {
        for (User user : usersByEmail.values()) {
            if (user instanceof Doctor doctor && doctor.getCrm().equalsIgnoreCase(crm)) {
                return Optional.of(doctor);
            }
        }
        return Optional.empty();
    }

    // Guarda a conta indexada pelo e-mail e impede colisões de e-mail ou CPF.
    @Override
    public synchronized void save(User user) {
        String emailKey = normalize(user.getEmail());
        User emailOwner = usersByEmail.get(emailKey);
        if (emailOwner != null && !emailOwner.getId().equals(user.getId())) {
            throw new ValidationException("Já existe uma conta com este e-mail.");
        }
        Optional<User> cpfOwner = findByCPF(user.getCPF());
        if (cpfOwner.isPresent() && !cpfOwner.get().getId().equals(user.getId())) {
            throw new ValidationException("Já existe uma conta com este CPF.");
        }
        if (user instanceof Doctor doctor) {
            Optional<User> crmOwner = findDoctorByCrm(doctor.getCrm());
            if (crmOwner.isPresent() && !crmOwner.get().getId().equals(user.getId())) {
                throw new ValidationException("Já existe um médico com este CRM.");
            }
        }
        usersByEmail.put(emailKey, user);
    }

    // Fornece as contas em ordem de cadastro sem permitir alteração do mapa interno.
    @Override
    public List<User> findAll() {
        return new ArrayList<>(usersByEmail.values());
    }

    // Normaliza espaços e caixa para que pesquisas e gravações usem a mesma chave.
    private String normalize(String email) {
        return email.trim().toLowerCase(Locale.ROOT);
    }
}
