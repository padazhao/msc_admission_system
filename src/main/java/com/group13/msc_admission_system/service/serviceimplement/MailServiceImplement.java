package com.group13.msc_admission_system.service.serviceimplement;

import com.group13.msc_admission_system.service.serviceinterface.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class MailServiceImplement implements MailService {
    @Autowired
    JavaMailSender javaMailSender;
    @Override
    public void sendSimpleMail(String from, String to, String subject, String context){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(context);
        javaMailSender.send(simpleMailMessage);
    }
}
