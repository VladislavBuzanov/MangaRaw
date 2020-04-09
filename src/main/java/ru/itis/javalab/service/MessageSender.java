package ru.itis.javalab.service;

public interface MessageSender {
    void sendMessage(String target, String topic, String message);
}
