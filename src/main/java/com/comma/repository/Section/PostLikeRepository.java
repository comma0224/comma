package com.comma.repository.Section;

import com.comma.domain.section.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Long countByPostKey(Long postKey);
}