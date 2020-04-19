package ru.itis.javalab.service.interfaces;

import ru.itis.javalab.model.Chat;

public interface ChatService {
    Chat getChatByUserId(Long id);

    Chat getChatByChatId(Long id);
}
