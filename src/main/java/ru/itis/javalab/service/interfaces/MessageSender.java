package ru.itis.javalab.service.interfaces;

public interface MessageSender {
    void sendMessage(String target, String topic, String message);
}
