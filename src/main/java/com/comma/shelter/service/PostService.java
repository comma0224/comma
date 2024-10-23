package com.comma.shelter.service;

import com.comma.shelter.domain.Post;
import com.comma.shelter.domain.Posts;
import com.comma.shelter.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    private static final int PAGE_SIZE = 30;

    public void save(Post post) {
        postRepository.save(post);
    }

    public Posts getPosts(Long postKey) {
        Post post = postRepository.findById(postKey).orElse(null);

        if (post != null) {
            post.setViews(post.getViews() + 1);
            postRepository.save(post);
        }

        return postRepository.findPostByPostKey(postKey);
    }

    public List<Posts> findPostsByParameters(Long categoryKey, Long tagKey, String type, String text, int page) {
        int limit = PAGE_SIZE;
        int offset = (page - 1) * PAGE_SIZE;

        return postRepository.findPostsByCategoryKeyAndTagKeyAndTypeAndText(categoryKey, tagKey, type, text, limit, offset);
    }

    public Long countPostsByParameters(Long categoryKey, Long tagKey, String type, String text) {

        long totalPosts = postRepository.countPostsByCategoryKeyAndTagKeyAndTypeAndText(categoryKey, tagKey, type, text);
        return (totalPosts + PAGE_SIZE - 1) / PAGE_SIZE;
    }



}
