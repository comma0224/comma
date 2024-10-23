package com.comma.shelter.repository;

import com.comma.shelter.domain.Post;
import com.comma.shelter.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCategoryKey(Long categoryKey);

    @Query("SELECT new com.comma.shelter.domain.Posts(p.postKey, p.title, p.views, p.createdAt, u.nickname, t.title, COUNT(pl.postLikeKey) AS postLikeCount, COUNT(c.commentKey) AS commentCount) " +
            "FROM Post p " +
            "LEFT JOIN User u ON p.userKey = u.userKey " +
            "LEFT JOIN Tag t ON p.tagKey = t.tagKey " +
            "LEFT JOIN PostLike pl ON p.postKey = pl.postKey " +
            "LEFT JOIN Comment c ON p.postKey = c.postKey " +
            "WHERE p.categoryKey = :categoryKey " +
            "AND (:tagKey = 0 OR p.tagKey = :tagKey) " +
            "AND (:type = '' OR (:type = 'nickname' AND u.nickname LIKE %:text%) OR (:type = 'title' AND p.title LIKE %:text%)) " +
            "GROUP BY p.postKey, p.title, p.views, p.createdAt, u.nickname, t.title " +
            "ORDER BY p.createdAt DESC " +
            "LIMIT :limit OFFSET :offset")
    List<Posts> findPostsByCategoryKeyAndTagKeyAndTypeAndText(@Param("categoryKey") Long categoryKey,
                                                              @Param("tagKey") Long tagKey,
                                                              @Param("type") String type,
                                                              @Param("text") String text,
                                                              @Param("limit") int limit,
                                                              @Param("offset") int offset);

    @Query("SELECT COUNT(p) " +
            "FROM Post p " +
            "LEFT JOIN User u ON p.userKey = u.userKey " +
            "LEFT JOIN Tag t ON p.tagKey = t.tagKey " +
            "LEFT JOIN PostLike pl ON p.postKey = pl.postKey " +
            "LEFT JOIN Comment c ON p.postKey = c.postKey " +
            "WHERE p.categoryKey = :categoryKey " +
            "AND (:tagKey = 0 OR p.tagKey = :tagKey) " +
            "AND (:type = '' OR (:type = 'nickname' AND u.nickname LIKE %:text%) OR (:type = 'title' AND p.title LIKE %:text%))")
    Long countPostsByCategoryKeyAndTagKeyAndTypeAndText(@Param("categoryKey") Long categoryKey,
                                                        @Param("tagKey") Long tagKey,
                                                        @Param("type") String type,
                                                        @Param("text") String text);

    @Query("SELECT new com.comma.shelter.domain.Posts(p.postKey, p.title, p.views, p.createdAt, u.nickname, t.title, COUNT(pl.postLikeKey) AS postLikeCount, COUNT(c.commentKey) AS commentCount, p.content, p.userKey) " +
            "FROM Post p " +
            "LEFT JOIN User u ON p.userKey = u.userKey " +
            "LEFT JOIN Tag t ON p.tagKey = t.tagKey " +
            "LEFT JOIN PostLike pl ON p.postKey = pl.postKey " +
            "LEFT JOIN Comment c ON p.postKey = c.postKey " +
            "WHERE p.postKey = :postKey " +
            "GROUP BY p.postKey, p.title, p.views, p.createdAt, u.nickname, t.title, p.content")
    Posts findPostByPostKey(@Param("postKey") Long postKey);

    Long countPostsByCategoryKeyAndTagKey(Long categoryKey, Long tagKey);
    Long countPostsByCategoryKey(Long categoryKey);


}