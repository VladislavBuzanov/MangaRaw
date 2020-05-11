package ru.itis.javalab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.model.User;
import ru.itis.javalab.repository.UserRepository;

import javax.servlet.http.HttpSession;

@Controller
public class ProfileController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("profile/{userId}")
    public ResponseEntity<?> getProfile(@PathVariable("userId") Long userId) {
        User user = userRepository.findUserById(userId).get();
        return ResponseEntity.ok(user.getDto());
    }


}
