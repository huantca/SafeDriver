package com.utc.app.safedriver.email;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/api/v1/email")
public class EmailController {

    @Autowired
    private EmailServiceImpl emailServiceImpl;

    @PostMapping("/sendMail")
    public int sendMail(@RequestBody String email) throws UnsupportedEncodingException, MessagingException {
        Integer status = emailServiceImpl.sendOTP(email);
        return status;
    }
}
