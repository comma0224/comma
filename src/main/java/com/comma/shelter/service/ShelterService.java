package com.comma.shelter.service;

import com.comma.shelter.domain.Shelter;
import com.comma.shelter.repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShelterService {

    @Autowired
    private ShelterRepository shelterRepository;

    public Shelter findByUrl(String url) {
        return shelterRepository.findByUrl(url);
    }

}
