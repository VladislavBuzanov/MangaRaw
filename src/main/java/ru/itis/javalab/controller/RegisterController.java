package ru.itis.javalab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.javalab.dto.SignUpDto;
import ru.itis.javalab.model.User;
import ru.itis.javalab.model.enumerated.Role;
import ru.itis.javalab.service.interfaces.SignUpService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class RegisterController {

    @Autowired
    SignUpService signUpService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView("register_page");
        modelAndView.addObject("signUpDto", new SignUpDto());
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(@Valid SignUpDto signUpDto, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("register_page");
//            modelAndView.addObject("error", result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
            return modelAndView;
        }
        signUpService.signUp(User.builder()
                .login(signUpDto.getLogin())
                .hashPassword(passwordEncoder.encode(signUpDto.getPassword()))
                .role(Role.USER)
                .email(signUpDto.getEmail())
                .isConfirmed(false)
                .code(UUID.randomUUID().toString())
                .build());
        return new ModelAndView("redirect:/login");
    }
}
