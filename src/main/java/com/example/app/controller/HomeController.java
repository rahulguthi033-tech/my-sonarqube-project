package com.example.app.controller;

import com.example.app.model.Message;
import com.example.app.service.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final MessageService messageService;

    public HomeController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/")
    public Message home() {
        return messageService.getMessage();
    }
}
