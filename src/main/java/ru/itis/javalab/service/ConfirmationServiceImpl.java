package ru.itis.javalab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.javalab.model.User;
import ru.itis.javalab.repository.UserRepository;

import java.util.Optional;

@Component
public class ConfirmationServiceImpl implements ConfirmationService {
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean confirm(String code) {
        Optional<User> userOpt = userRepository.findUserByCode(code);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setConfirmed(true);
            user.setCode(null);
            userRepository.update(user);
            return true;
        } else
            return false;
    }
}
