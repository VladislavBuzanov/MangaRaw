package ru.itis.javalab.service.implementations;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import ru.itis.javalab.model.User;
import ru.itis.javalab.repository.UserRepository;
import ru.itis.javalab.service.interfaces.ConfirmationService;
import ru.itis.javalab.service.interfaces.MessageSender;
import ru.itis.javalab.service.interfaces.SignUpService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ConfirmationService confirmationService;

    @Autowired
    MessageSender messageSender;

    @Autowired
    FreeMarkerConfigurer configurer;

    @Override
    public void signUp(User user) {
        Map<String, Object> model = new HashMap<>();
        model.put("username", user.getLogin());
        model.put("code", user.getCode());
        new Thread(() -> {
            try {
                Template template = configurer.getConfiguration().getTemplate("email.ftl");
                String message = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
                messageSender.sendMessage(user.getEmail(), "Email confirmation", message);
            } catch (IOException | TemplateException e) {
                throw new IllegalArgumentException(e);
            }
        }).start();
        userRepository.save(user);
    }
}
