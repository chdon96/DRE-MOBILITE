package com.example.chadi.Email;

public interface EmailSender {
    void send(String to, String email);
    void sendMail2(String to, String subject, String body);
}

