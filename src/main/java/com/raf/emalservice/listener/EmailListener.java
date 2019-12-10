package com.raf.emalservice.listener;

import com.raf.emalservice.dto.MatchesDto;
import com.raf.emalservice.listener.helper.MessageHelper;
import com.raf.emalservice.service.EmailService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class EmailListener {

    private MessageHelper messageHelper;
    private EmailService emailService;

    public EmailListener(MessageHelper messageHelper, EmailService emailService) {
        this.messageHelper = messageHelper;
        this.emailService = emailService;
    }

    @JmsListener(destination = "${destination.sendEmails}", concurrency = "5-10")
    public void addOrder(Message message) throws JMSException {
        MatchesDto matchesDto = messageHelper.getMessage(message, MatchesDto.class);
        emailService.sendSimpleMessage("mradovic01011111@gmail.com", "subject", matchesDto.toString());
    }
}
