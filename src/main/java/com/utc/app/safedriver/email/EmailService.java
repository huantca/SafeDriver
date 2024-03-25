package com.utc.app.safedriver.email;

public interface EmailService {
    String sendSimpleMail(EmailDetails details);

    String sendMailWithAttachment(EmailDetails details);
}
