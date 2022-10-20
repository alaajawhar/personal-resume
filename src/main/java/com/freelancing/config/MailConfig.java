package com.freelancing.config;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.annotation.PostConstruct;
import java.util.Properties;

/**
 * @author Alaa Jawhar
 */
@Configuration
@ToString
public class MailConfig {

    @Value("${email.host}")
    private String emailHost;
    @Value("${email.port}")
    private int emailPort;
    @Value("${email.username}")
    private String emailUsername;
    @Value("${email.password}")
    private String emailPassword;
    @Value("${email.transport.protocol}")
    private String emailProtocol;
    @Value("${email.smtp.auth}")
    private String emailSmtpAuth;
    @Value("${email.smtp.starttls.enable}")
    private String isEmailTlsEnabled;
    @Value("${email.debug}")
    private String isEmailDebugEnabled;
    @Value("${email.smtp.ssl.protocol}")
    private String smtpSslProtocol;


    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(emailHost);
        mailSender.setPort(emailPort);

        mailSender.setUsername(emailUsername);
        mailSender.setPassword(emailPassword);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", emailProtocol);
        props.put("mail.smtp.auth", emailSmtpAuth);
        props.put("mail.smtp.starttls.enable", isEmailTlsEnabled);
        props.put("mail.debug", isEmailDebugEnabled);
        props.put("mail.smtp.ssl.protocols", smtpSslProtocol);

        return mailSender;
    }

    @PostConstruct
    public void init(){
        System.out.println("MailConfigs: [" + this.toString() + "]");
    }
}
