package com.comma.shelter.service;

import com.comma.shelter.domain.Comment;
import com.comma.shelter.domain.Comments;
import com.comma.shelter.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<Comments> getComments(Long postKey) {
        return commentRepository.findCommentsByPostKey(postKey);
    }

    public void save(Comment comment) {
        commentRepository.save(comment);

        if (comment.getParentCommentKey() == 0L) {
            comment.setParentCommentKey(comment.getCommentKey());
            commentRepository.save(comment);
        }
    }

}
