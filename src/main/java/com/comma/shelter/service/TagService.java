package com.comma.shelter.service;

import com.comma.shelter.domain.Tag;
import com.comma.shelter.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public List<Tag> findByCategoryKey(Long categoryKey) {
        return tagRepository.findByCategoryKey(categoryKey);
    }



}
