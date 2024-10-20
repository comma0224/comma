package com.comma.shelter.service;

import com.comma.shelter.domain.ShelterLike;
import com.comma.shelter.repository.ShelterLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;

@Service
public class ShelterLikeService {

    @Autowired
    private ShelterLikeRepository shelterLikeRepository;

    public Boolean existsByShelterKeyAndUserKey(Long shelterKey, Long userKey) {
        return shelterLikeRepository.existsByShelterKeyAndUserKey(shelterKey, userKey);
    }

    public void save(Long shelterKey, Long userKey) {
        ShelterLike shelterLike = new ShelterLike();
        shelterLike.setShelterKey(shelterKey);
        shelterLike.setUserKey(userKey);

        shelterLikeRepository.save(shelterLike);
    }

    @Transactional
    public void deleteByShelterKeyAndUserKey(Long shelterKey, Long userKey) {
        shelterLikeRepository.deleteByShelterKeyAndUserKey(shelterKey, userKey);
    }


}
