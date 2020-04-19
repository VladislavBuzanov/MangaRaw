package ru.itis.javalab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.javalab.service.interfaces.ConfirmationService;

@Controller
public class ConfirmController {
    @Autowired
    ConfirmationService confirmationService;

    @GetMapping(value = "/confirm")
    public String confirm(@RequestParam String code) {
        if (confirmationService.confirm(code))
            return "redirect:/profile";
        else
            return "redirect:/error" + "?content=confirmation";
    }
}
