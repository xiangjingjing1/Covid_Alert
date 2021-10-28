package com.example.Covid_Alert.util;

import com.example.Covid_Alert.models.User;
import com.example.Covid_Alert.models.VerificationToken;
import com.example.Covid_Alert.repositories.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserListener implements ApplicationListener<OnCreateUserEvent> {
    // Ceci peut etre fait dans application.properties
    private String serverUrl = "http://localhost:8080";
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    private void confirmCreateUser(OnCreateUserEvent event) {
        // get the user
        User user = event.getUser();
        // create verification token
        String token = UUID.randomUUID().toString();
        VerificationToken verifToken = new VerificationToken();
        // VerificationToken est une classe qu'on va definir apres
        verifToken.setToken(token);
        verifToken.setUsername(user.getUsername());
        verifToken.setExpiryDate(verifToken.calculateExpiryDate(VerificationToken.EXPIRATION));
        verificationTokenRepository.saveAndFlush(verifToken);
        // ...s
        // get email properties
        String recipientAddesss = user.getEmail();
        String subject = "User Account Confirmation";
        String confirmationUrl = event.getAppUrl()+"userConfirm?token="+token;
        String message = "Please confirm:";
        // send email
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddesss);
        email.setSubject(subject);
        email.setText(message+"\r\n"+serverUrl+confirmationUrl);
        mailSender.send(email);
    }

    @Override
    public void onApplicationEvent(OnCreateUserEvent event) {
        this.confirmCreateUser(event);
    }


}
