package com.comma.shelter.service;

import com.comma.shelter.domain.Addon;
import com.comma.shelter.repository.AddonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddonService {

    @Autowired
    private AddonRepository addonRepository;

    public List<Addon> findByShelterKey(Long shelterKey) {
        return addonRepository.findByShelterKey(shelterKey);
    }
}
