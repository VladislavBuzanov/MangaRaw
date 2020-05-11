package ru.itis.javalab.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.javalab.security.details.UserDetailsImpl;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class RootController {

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView rootPage(HttpSession session) {
        String login = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getLogin();
        ModelAndView modelAndView = new ModelAndView("root_page");
        modelAndView.addObject("content", Optional.ofNullable(session.getAttribute("session")).orElse("noting"));
        modelAndView.addObject("user", login);
        return modelAndView;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/profile")
    public String addInSession(@RequestParam(name = "content") String content, HttpSession session) {
        session.setAttribute("session", content);
        return "redirect:/profile";
    }
}
