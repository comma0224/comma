package com.comma.shelter.service;

import com.comma.shelter.domain.PostLike;
import com.comma.shelter.repository.PostLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostLikeService {

    @Autowired
    private PostLikeRepository postLikeRepository;

    public Boolean existsByPostKeyAndUserKey(Long postKey, Long userKey) {
        return postLikeRepository.existsByPostKeyAndUserKey(postKey, userKey);
    }

    @Transactional
    public void deleteByPostKeyAndUserKey(Long postKey, Long userKey) {
        postLikeRepository.deleteByPostKeyAndUserKey(postKey, userKey);
    }

    public void save(Long postKey, Long userKey) {
        PostLike postLike = new PostLike();
        postLike.setPostKey(postKey);
        postLike.setUserKey(userKey);

        postLikeRepository.save(postLike);
    }
}
