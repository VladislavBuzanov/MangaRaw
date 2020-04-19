package ru.itis.javalab.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.itis.javalab.model.Chat;
import ru.itis.javalab.model.User;

import java.util.List;
import java.util.Optional;

public interface ChatRepository {
    Optional<Chat> findChatById(Long id);

    Optional<Chat> findChatByUserId(Long user_id);

    Optional<Chat> findChatByUserLogin(String login);

    Chat save(Chat chat);

    void update(Chat chat);

}
