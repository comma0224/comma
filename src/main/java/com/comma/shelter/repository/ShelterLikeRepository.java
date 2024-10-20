package com.comma.shelter.repository;

import com.comma.shelter.domain.ShelterLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelterLikeRepository extends JpaRepository<ShelterLike, Long> {

    Boolean existsByShelterKeyAndUserKey(Long shelterKey, Long userKey);
    void deleteByShelterKeyAndUserKey(Long shelterKey, Long userKey);

}