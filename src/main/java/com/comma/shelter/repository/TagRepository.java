package com.comma.shelter.repository;

import com.comma.shelter.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByCategoryKey(Long categoryKey);
}
