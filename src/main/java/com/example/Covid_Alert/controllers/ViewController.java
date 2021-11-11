package com.example.Covid_Alert.controllers;

import com.example.Covid_Alert.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class ViewController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping( {"/index"})
    public String home() { return "index"; }

    @GetMapping({"/login"})
    public String login() {	return "login"; }

    @GetMapping({"/register"})
    public String register() {return "register";}

    @GetMapping({"/changeUser"})
    public String changeUser() { return "changeUser"; }

    @GetMapping({"/listUsers"})
    public String listUsers() { return "listUsers"; }

}
