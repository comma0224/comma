package com.comma.shelter.service;

import com.comma.shelter.domain.Category;
import com.comma.shelter.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findByShelterKey(Long shelterKey) {
        return categoryRepository.findByShelterKey(shelterKey);
    }
}

