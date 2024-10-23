package com.comma.shelter.repository;

import com.comma.shelter.domain.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    Boolean existsByPostKeyAndUserKey(Long postKey, Long userKey);
    void deleteByPostKeyAndUserKey(Long postKey, Long userKey);
}
