package com.utc.app.safedriver.auth;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Service;

import com.utc.app.safedriver.email.EmailAlreadyExistedException;
import com.utc.app.safedriver.email.EmailDetails;
import com.utc.app.safedriver.email.EmailService;
import com.utc.app.safedriver.email.EmailServiceImpl;
import com.utc.app.safedriver.user.User;
import com.utc.app.safedriver.user.UserRepository;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final EmailServiceImpl emailService;

    public void sendOtp(RequestOtp request)
            throws UnsupportedEncodingException, MessagingException {
        User user = userRepository.findByEmail(request.getEmail())
                .orElse(User.builder()
                        .email(request.getEmail())
                        .name(request.getName())
                        .build());
        Integer otp = emailService.sendOTP(request.getEmail());
        user.setOtp(otp.toString());
        user.setOtpRequestedTime(System.currentTimeMillis());
        userRepository.save(user);
    }

    public User register(RegisterRequest request) throws OtpAuthenticationException, EmailAlreadyExistedException {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new OtpAuthenticationException("otp authentication failed, please request otp first"));

        if (user.getPassword() != null) {
            throw new EmailAlreadyExistedException(
                    "An account with email '" + request.getEmail() + "' already existed");
        }

        if (request.getOtp() == null) {
            throw new OtpAuthenticationException("otp is missing, please provide otp to register");
        } else if (user.getOtp() == null) {
            throw new OtpAuthenticationException("otp authentication failed, please request otp first");
        } else {
            System.out.println(user.getOtp() + "lplpl" + request.getOtp());
            if (System.currentTimeMillis() - user.getOtpRequestedTime() < (5 * 60 * 60 * 1000)) {

                if (request.getOtp().equals(user.getOtp())) {
                    request.fillBlankValue();
                    user.setOtp(null);
                    user.setOtpRequestedTime(null);
                    user.setName(request.getName());
                    user.setEmail(request.getEmail());
                    user.setPassword(request.getPassword());
                    user.setPhone(request.getPhone());
                    user.setCreatedTime(System.currentTimeMillis());
                    user.setLastModified(System.currentTimeMillis());

                    return userRepository.save(user);
                } else {
                    throw new OtpAuthenticationException("incorrect otp");
                }
            } else {
                throw new OtpAuthenticationException("otp is expired");
            }
        }
    }

}
