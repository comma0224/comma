package com.comma.shelter.repository;

import com.comma.shelter.domain.Addon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddonRepository extends JpaRepository<Addon, Long> {
    List<Addon> findByShelterKey(Long shelterKey);
}
