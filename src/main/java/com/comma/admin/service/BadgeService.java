package com.comma.admin.service;

import com.comma.admin.repository.BadgeRepository;
import com.comma.admin.domain.Badge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BadgeService {
    @Autowired
    private BadgeRepository badgeRepository;

    public void saveBadge(String name,Integer price, Integer tier, String file) {
        Badge badge = new Badge();
        badge.setName(name);
        badge.setPrice(price);
        badge.setTier(tier);
        badge.setFile(file);

        badgeRepository.save(badge);
    }


}
