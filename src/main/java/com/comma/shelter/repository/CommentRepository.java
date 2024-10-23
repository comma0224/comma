package com.comma.shelter.repository;


import com.comma.shelter.domain.Comment;
import com.comma.shelter.domain.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT new com.comma.shelter.domain.Comments(c.commentKey, c.userKey, c.parentCommentKey, c.content, c.createdAt, COUNT(cl.commentLikeKey) AS commentLikeCount, u.nickname) " +
            "FROM Comment c " +
            "LEFT JOIN User u ON c.userKey = u.userKey " +
            "LEFT JOIN CommentLike cl ON c.commentKey = cl.commentKey " +
            "WHERE c.postKey = :postKey " +
            "GROUP BY c.commentKey, c.userKey, c.parentCommentKey, c.content, c.createdAt, u.nickname " +
            "ORDER BY c.parentCommentKey ASC ")
    List<Comments> findCommentsByPostKey(Long postKey);
}
