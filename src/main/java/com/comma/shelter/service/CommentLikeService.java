package com.comma.shelter.service;

import com.comma.shelter.domain.Comments;
import com.comma.shelter.domain.CommentLike;
import com.comma.shelter.repository.CommentLikeRepository;
import com.comma.shelter.repository.PostLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentLikeService {
    @Autowired
    private CommentLikeRepository commentLikeRepository;

    public Boolean existsByCommentKeyAndUserKey(Long commentKey, Long userKey) {
        return commentLikeRepository.existsByCommentKeyAndUserKey(commentKey, userKey);
    }

    @Transactional
    public void deleteByCommentKeyAndUserKey(Long commentKey, Long userKey) {
        commentLikeRepository.deleteByCommentKeyAndUserKey(commentKey, userKey);
    }

    public List<Boolean> existsByCommentKeyAndUserKeyList(List<Comments> comments, Long userKey) {
        List<Boolean> isCommentLikeList = new ArrayList<>();
        comments.forEach(comment ->
    isCommentLikeList.add(existsByCommentKeyAndUserKey(comment.getCommentKey(), userKey))
);

        return isCommentLikeList;
    }

    public void save(Long commentKey, Long userKey) {
        CommentLike commentLike = new CommentLike();
        commentLike.setCommentKey(commentKey);
        commentLike.setUserKey(userKey);

        commentLikeRepository.save(commentLike);
    }
}
