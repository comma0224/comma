package com.comma.shelter.repository;

import com.comma.shelter.domain.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    Boolean existsByCommentKeyAndUserKey(Long commentKey, Long userKey);
    void deleteByCommentKeyAndUserKey(Long commentKey, Long userKey);
}
