package ru.itis.javalab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import ru.itis.javalab.model.User;

import java.util.List;
import java.util.Optional;

@Component
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByLogin(String login);

    User save(User user);

    @Modifying
    @Query("update User set isConfirmed = true where id = ?1")
    void confirmById(Long id);

    Optional<User> findUserByCode(String code);

    Optional<User> findUserById(Long id);

}

