package com.comma.user.service;

import com.comma.user.domain.User;
import com.comma.user.repository.Repository;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private Repository userRepository;

    public boolean isUserIdDuplicate(String userId) {
        return userRepository.existsByUserId(userId);
    }

    public boolean isNicknameDuplicate(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    public void signup(Map<String, String> request) throws Exception {
        User user = new User();
        user.setUserId(request.get("userId"));
        user.setPassword(request.get("password"));
        user.setStatus("0");
        user.setRole("user");
        user.setNickname(request.get("nickname"));

        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new Exception("회원가입에 실패했습니다.");
        }

    }
}
