package com.comma.shelter.controller;

import com.comma.shelter.domain.Comment;
import com.comma.shelter.domain.Post;
import com.comma.shelter.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/shelter")
public class ShelterApiController {

    @Autowired
    private ShelterLikeService shelterLikeService;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private PostLikeService postLikeService;
    @Autowired
    private CommentLikeService commentLikeService;

    @PostMapping("/updateShelterLike")
    public HashMap<String, Object> updateShelterLike(@RequestBody Map<String, String> request, HttpSession session) {
        HashMap<String, Object> response = new HashMap<>();
        Long shelterKey = Long.valueOf(request.get("shelterKey"));
        Long userKey = (Long) session.getAttribute("userKey");

        if (userKey == null) {
            response.put("status", false);
            response.put("message", "로그인이 필요합니다.");
            return response;
        }
        try {
            boolean isShelterLike = Boolean.parseBoolean(request.get("isShelterLike"));

            if (isShelterLike) {
                shelterLikeService.deleteByShelterKeyAndUserKey(shelterKey, userKey);
                response.put("message", "♡");
            } else {
                shelterLikeService.save(shelterKey, userKey);
                response.put("message", "♥");
            }

            response.put("status", true);


        } catch (Exception e) {
            response.put("status", false);
            response.put("message", e.getMessage());
        }
        return response;
    }
    @PostMapping("/updatePostLike")
    public HashMap<String, Object> updatePostLike(@RequestBody Map<String, String> request, HttpSession session) {
        HashMap<String, Object> response = new HashMap<>();
        Long postKey = Long.valueOf(request.get("postKey"));
        Long userKey = (Long) session.getAttribute("userKey");

        if (userKey == null) {
            response.put("status", false);
            response.put("message", "로그인이 필요합니다.");
            return response;
        }
        try {
            boolean isPostLike = Boolean.parseBoolean(request.get("isShelterLike"));

            if (isPostLike) {
                postLikeService.deleteByPostKeyAndUserKey(postKey, userKey);
                response.put("message", "♡");
            } else {
                postLikeService.save(postKey, userKey);
                response.put("message", "♥");
            }

            response.put("status", true);


        } catch (Exception e) {
            response.put("status", false);
            response.put("message", e.getMessage());
        }
        return response;
    }
    @PostMapping("/updateCommentLike")
    public HashMap<String, Object> updateCommentLike(@RequestBody Map<String, String> request, HttpSession session) {
        HashMap<String, Object> response = new HashMap<>();
        Long commentKey = Long.valueOf(request.get("commentKey"));
        Long userKey = (Long) session.getAttribute("userKey");

        if (userKey == null) {
            response.put("status", false);
            response.put("message", "로그인이 필요합니다.");
            return response;
        }
        try {
            boolean isCommentLike = Boolean.parseBoolean(request.get("isCommentLike"));

            if (isCommentLike) {
                commentLikeService.deleteByCommentKeyAndUserKey(commentKey, userKey);
                response.put("message", "♡");
            } else {
                commentLikeService.save(commentKey, userKey);
                response.put("message", "♥");
            }

            response.put("status", true);


        } catch (Exception e) {
            response.put("status", false);
            response.put("message", e.getMessage());
        }
        return response;
    }

    @PostMapping("/post-save")
    public HashMap<String, Object> postSave(@RequestBody Map<String, String> request, HttpSession session) {
        HashMap<String, Object> response = new HashMap<>();

        Long userKey = (Long) session.getAttribute("userKey");

        if (userKey == null) {
            response.put("status", false);
            response.put("message", "로그인이 필요합니다.");
            return response;
        }

        //유저키, 카테고리키, 태그키, 제목, 내용 전달
        Post post = new Post();
        post.setUserKey(userKey);
        post.setCategoryKey(Long.valueOf(request.get("categoryKey")));
        post.setTagKey(Long.valueOf(request.get("tagKey")));
        post.setTitle(request.get("title"));
        post.setContent(request.get("content"));

        try {
            postService.save(post);

            response.put("status", true);
            response.put("message", "게시글이 등록되었습니다.");
        } catch (Exception e) {
            response.put("status", false);
            response.put("message", e.getMessage());
        }

        return response;
    }

    @PostMapping("/comment-save")
    public HashMap<String, Object> commentSave(@RequestBody Map<String, String> request, HttpSession session) {
        HashMap<String, Object> response = new HashMap<>();

        Long userKey = (Long) session.getAttribute("userKey");

        if (userKey == null) {
            response.put("status", false);
            response.put("message", "로그인이 필요합니다.");
            return response;
        }

        //유저키, 게시글키, 내용 전달
        Comment comment = new Comment();
        comment.setUserKey(userKey);
        comment.setPostKey(Long.valueOf(request.get("postKey")));
        comment.setContent(request.get("content"));
        comment.setParentCommentKey(Long.valueOf(request.get("parentCommentKey")));

        try {
            commentService.save(comment);

            response.put("status", true);
            response.put("message", "댓글이 등록되었습니다.");
        } catch (Exception e) {
            response.put("status", false);
            response.put("message", e.getMessage());
        }

        return response;
    }

}
