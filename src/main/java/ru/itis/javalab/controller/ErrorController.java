package ru.itis.javalab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {
    @GetMapping("/error")
    public ModelAndView errorPage(@RequestParam String error) {
        ModelAndView errorPage = new ModelAndView("error");
        switch (error) {
            case "confirmation":
                errorPage.addObject("content", "Ошибка подтверждения. Указан неверный или устаревший код ");
                break;
        }
        return errorPage;
    }
}
