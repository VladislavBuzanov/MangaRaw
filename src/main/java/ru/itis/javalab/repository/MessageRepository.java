package ru.itis.javalab.repository;

import ru.itis.javalab.model.Chat;
import ru.itis.javalab.model.Message;

import java.util.List;
import java.util.Optional;

public interface MessageRepository {
    List<Message> findMessagesByChatId(Long id);
    
    void save(Message message);

}
