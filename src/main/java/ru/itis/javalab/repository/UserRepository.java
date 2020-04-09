package ru.itis.javalab.repository;

import org.springframework.stereotype.Component;
import ru.itis.javalab.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findUserByLogin(String login);

    void save(User user);

    void update(User user);

    Optional<User> findUserByCode(String code);

}

