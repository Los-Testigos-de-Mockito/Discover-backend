package com.example.discoverbackend.services;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
