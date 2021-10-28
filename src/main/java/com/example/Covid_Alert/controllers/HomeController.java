package com.example.Covid_Alert.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @Value("${app.version}")
    private String appVersion;

    @GetMapping
    public String get(){
        return appVersion;
    }
}
