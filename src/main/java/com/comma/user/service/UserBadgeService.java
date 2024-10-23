package com.comma.user.service;

import com.comma.user.domain.UserBadge;
import com.comma.user.domain.UserBadges;
import com.comma.user.repository.UserBadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBadgeService {

    @Autowired
    private UserBadgeRepository userBadgeRepository;

    public List<UserBadge> getUserBadge(Long userKey) {
        return userBadgeRepository.findByUserKey(userKey);
    }

    public List<UserBadges> getUserBadges(Long userKey) {
        return userBadgeRepository.findUserBadgesByUserKey(userKey);
    }


}
