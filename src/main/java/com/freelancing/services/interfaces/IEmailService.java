package com.freelancing.services.interfaces;

import org.springframework.scheduling.annotation.Async;

/**
 * @author Alaa Jawhar
 */
public interface IEmailService {

    @Async
    void sendSimpleMessage(String to, String subject, String text);

}
