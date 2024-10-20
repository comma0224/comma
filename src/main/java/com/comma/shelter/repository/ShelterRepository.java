package com.comma.shelter.repository;

import com.comma.shelter.domain.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelterRepository extends JpaRepository<Shelter, Long> {
    Shelter findByUrl(String url);

}
