package com.comma.user.service;

import com.comma.user.domain.User;
import com.comma.user.domain.Users;
import com.comma.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean isUserIdDuplicate(String userId) {
        return userRepository.existsByUserId(userId);
    }

    public boolean isNicknameDuplicate(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    public User getUser(Long userKey) {
        return userRepository.findById(userKey).orElse(null);
    }

    public Users getUsers(Long userKey) {
        return userRepository.findUsersByUserKey(userKey);
    }

    public User signup(Map<String, String> request) throws Exception {
        User user = new User();
        user.setUserId(request.get("userId"));
        user.setPassword(request.get("password"));
        user.setNickname(request.get("nickname"));

        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new Exception("회원가입에 실패했습니다.");
        }

        return user;
    }

    public User login(Map<String, String> request) throws Exception {
        User user = userRepository.findByUserId(request.get("userId"));

        if (!user.getPassword().equals(request.get("password"))) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }

        user.setLastLogin(new Timestamp(System.currentTimeMillis()));
        userRepository.save(user);

        return user;
    }
}
