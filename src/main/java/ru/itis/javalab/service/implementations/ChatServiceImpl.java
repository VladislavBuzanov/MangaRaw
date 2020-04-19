package ru.itis.javalab.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ru.itis.javalab.model.Chat;
import ru.itis.javalab.model.User;
import ru.itis.javalab.repository.ChatRepository;
import ru.itis.javalab.repository.UserRepository;
import ru.itis.javalab.service.interfaces.ChatService;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class ChatServiceImpl implements ChatService {
    @Autowired
    ChatRepository chatRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Chat getChatByUserId(Long id) {
        Optional<Chat> chatOptional = chatRepository.findChatByUserId(id);
        if (chatOptional.isPresent()) {
            return chatOptional.get();
        } else {
            Optional<User> userOptional = userRepository.findUserById(id);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                Chat chat = chatRepository.save(Chat.builder()
                        .user(User.builder().userId(user.getUserId()).build())
                        .history(new ArrayList<>())
                        .build());
                return chat;
            } else {
                throw new IllegalArgumentException("There is no user with such ID");
            }
        }
    }

    @Nullable
    @Override
    public Chat getChatByChatId(Long id) {
        return chatRepository.findChatById(id).get();
    }
}
