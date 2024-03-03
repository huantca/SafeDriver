package com.utc.app.safedriver.user;

import org.springframework.stereotype.Service;

import com.utc.app.safedriver.exceptions.AuthenticationException;
import com.utc.app.safedriver.exceptions.EmailAlreadyRegisteredException;
import com.utc.app.safedriver.exceptions.UserNotExistedException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private EmailAlreadyRegisteredException emailAlreadyRegisteredException = new EmailAlreadyRegisteredException(
            "email already existed");
    private UserNotExistedException userNotExistedException = new UserNotExistedException(
            "user with this email not existed");

    UserDTO login(User user) throws UserNotExistedException, AuthenticationException {
        User savedUser = repository.findByEmail(user.getEmail()).orElseThrow(() -> userNotExistedException);
        if (savedUser.getPassword().equals(user.getPassword())) {
            return UserDTO.fromUser(savedUser);
        } else {
            throw new AuthenticationException("wrong password");
        }
    }

    UserDTO register(User user) throws EmailAlreadyRegisteredException {
        User savedUser = repository.findByEmail(user.getEmail()).orElse(null);
        if (savedUser != null) {
            throw emailAlreadyRegisteredException;
        }
        savedUser = User.builder()
                .name(user.getName())
                .age(user.getAge())
                .address(user.getAddress())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
        return UserDTO.fromUser(repository.save(savedUser));
    }
}
