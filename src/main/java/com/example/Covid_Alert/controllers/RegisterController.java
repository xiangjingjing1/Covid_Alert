package com.example.Covid_Alert.controllers;

import com.example.Covid_Alert.models.User;
import com.example.Covid_Alert.models.VerificationToken;
import com.example.Covid_Alert.repositories.AuthorityRepository;
import com.example.Covid_Alert.repositories.UserRepository;
import com.example.Covid_Alert.repositories.VerificationTokenRepository;
import com.example.Covid_Alert.util.OnCreateUserEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;
import java.util.Date;

public class RegisterController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @PostMapping("doRegister")
    public String register(@Validated @ModelAttribute("user")
                                   User user, BindingResult result) {
        // check for errors ...
        // verify that username does not exist:
        if(userRepository.existsUserByUsername(user.getUsername())) {
            return "register.jsp?user=true";
        }
        else {	// encrypt password:
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            // save user object:
            userRepository.saveAndFlush(user);
            // create/save an Authority obj ...
            eventPublisher.publishEvent(new OnCreateUserEvent("/",user));
            return "login";
        }
    }

    @GetMapping({"/userConfirm"})
    public String confirmUser(@RequestParam("token") String token) {
        VerificationToken verifToken = verificationTokenRepository.getOne(token);
        if (verifToken != null) {
            Date date = Calendar.getInstance().getTime();
            if (date.before(verifToken.getExpiryDate())) {
                verificationTokenRepository.delete(verifToken);
                User user = userRepository.findByUsername(verifToken.getUsername());
                user.setEnabled(true);
                userRepository.saveAndFlush(user);
                return "login.jsp?confirm=true";
            }
            else {
                verificationTokenRepository.delete(verifToken);
                return "register.jsp?expired=true";
            }
        }
        else { return "register.jsp?confirm=false"; }
    }
}
