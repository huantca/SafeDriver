package com.utc.app.safedriver.email;

import java.io.UnsupportedEncodingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl {

    @Value("${spring.mail.username}")
    private String sender;
    private final JavaMailSender mailSender;

    public int sendOTP(String email) throws UnsupportedEncodingException, MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(sender, "Safe Driver");
        helper.setTo(email);
        System.out.println(sender);

        String subject = "Here's your One Time Password (OTP) - Expire in 5 minutes!";

        int otp = (int) (Math.random() * (999999 - 100000) + 100000);

        String content = "<p>Hello <b>" + email + "</b>,</p>"
                + "<p>For security reason, you're required to use the following "
                + "One Time Password to authenticate:</p>"
                + "<p><b>" + otp + "</b></p>"
                + "<br>"
                + "<p>Note: this OTP is set to expire in 5 minutes.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
        return otp;
    }
}
