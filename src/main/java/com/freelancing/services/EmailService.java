package com.freelancing.services;

import com.freelancing.services.interfaces.IEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author Alaa Jawhar
 */
@Service
@Slf4j
public class EmailService implements IEmailService {

    @Value("${email.from}")
    private String emailFrom;
    @Value("${email.test}")
    private boolean isEmailTest;

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        if (this.isEmailTest == Boolean.TRUE) {
            return;
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(this.emailFrom);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    @PostConstruct
    public void init() {
        this.sendSimpleMessage("alaa.jawhar.eng@gmail.com", "Personal resume server is up!", "Personal Resume Server is up and running!");
    }

}
