package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * I used here the UserService from Lesson 6 Final Review (chat example).
 * This is a good solution for me.
 */
@Service
public class UserService {

    private final UserMapper userMapper;
    private final HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public User getUserById(Integer userId) {
        return userMapper.getUserByUserId(userId);
    }

    public void updateUser(Integer userId, String newUsername, String newPassword, String newFirstName, String newLastName) {
        //wip
        //password+slat -> hashedPassword
    }

    public User getUser(String username) {
        return userMapper.getUser(username);
    }

    public boolean isUserExisting(String username) {
        // userMapper.getUser(username) is null, also empty -> TRUE
        // userMapper.getUser(username) is not null, also existing -> FALSE
        return userMapper.getUser(username) == null;
    }

    public int createUser(User user) {
        // source https://www.geeksforgeeks.org/securerandom-nextbytes-method-in-java-with-examples/
        SecureRandom secRandom = new SecureRandom();
        byte[] saltByte = new byte[16];
        secRandom.nextBytes(saltByte);
        String saltEncoded = Base64.getEncoder().encodeToString(saltByte);
        String passwordHashed = hashService.getHashedValue(user.getPassword(), saltEncoded);
        return userMapper.insert(new User(null, user.getUsername(), saltEncoded, passwordHashed, user.getFirstName(), user.getLastName()));
    }
}
