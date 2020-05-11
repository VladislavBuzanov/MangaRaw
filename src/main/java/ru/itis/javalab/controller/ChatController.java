package ru.itis.javalab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.javalab.model.Chat;
import ru.itis.javalab.repository.ChatRepository;
import ru.itis.javalab.security.details.UserDetailsImpl;
import ru.itis.javalab.service.interfaces.ChatService;

@Controller
public class ChatController {

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    ChatService chatService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public ModelAndView getChatPage() {

        Chat chat = chatService.getChatByUserId(
                ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                        .getUser().getId());
        ModelAndView maw = new ModelAndView("chat");
        maw.addObject("history", chat.getHistory());
        maw.addObject("pageId", chat.getChatId());
        maw.addObject("userId", chat.getUser().getId());
        return maw;

    }

    @PreAuthorize("hasAnyAuthority('MODER', 'ADMIN')")
    @GetMapping("/chat/{id}")
    public ModelAndView getChatPage(@PathVariable Long id) {
        Chat chat = chatService.getChatByUserId(id);
        ModelAndView maw = new ModelAndView("chat");
        maw.addObject("history", chat.getHistory());
        maw.addObject("pageId", chat.getChatId());
        maw.addObject("userId", ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getId());
        return maw;
    }
}
