package com.hcd.websketch.security;

import com.hcd.websketch.domain.User;
import com.hcd.websketch.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

@Service
public class FacebookConnectionSignup implements ConnectionSignUp {

    private final UserRepository userRepository;

    public FacebookConnectionSignup(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String execute(Connection<?> connection) {
        final User user = new User();
        user.setUsername(connection.getDisplayName());
        user.setPassword(RandomStringUtils.randomAlphabetic(8));

        userRepository.save(user);

        return user.getUsername();
    }
}
