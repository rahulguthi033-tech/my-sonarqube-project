package com.example.app.service;

import com.example.app.model.Message;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    public Message getMessage() {
        return new Message("Hello from SonarQube Jenkins Docker Project");
    }
}
