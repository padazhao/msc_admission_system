package com.group13.msc_admission_system.service.serviceinterface;

import org.springframework.stereotype.Service;

@Service
public interface MailService {
    void sendSimpleMail(String from, String to, String subject, String context);
}
