package com.comma.shelter.controller;



import com.comma.shelter.domain.*;
import com.comma.shelter.service.*;
import com.comma.user.domain.UserBadge;
import com.comma.user.domain.UserBadges;
import com.comma.user.domain.Users;
import com.comma.user.service.UserBadgeService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.comma.user.domain.User;
import com.comma.user.service.UserService;

import java.util.List;
import java.util.Map;

@Controller
public class ShelterController {

    private static final Logger log = LoggerFactory.getLogger(ShelterController.class);
    @Autowired
    private ShelterService shelterService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AddonService addonService;

    @Autowired
    private UserService userService;

    @Autowired
    private ShelterLikeService shelterLikeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private PostService postService;

    @Autowired
    private PostLikeService postLikeService;

    @Autowired
    private UserBadgeService userBadgeService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentLikeService commentLikeService;

    @GetMapping("/shelter/{url}")
    public ModelAndView shelter(@PathVariable String url, HttpSession session) {
        ModelAndView mv = shelterCommon(url, session);
        mv.setViewName("shelter/shelter");

        //쉘터 메인 기능 리스트

        return mv;
    }

    @GetMapping("/shelter/{url}/{categoryKey}")
    public ModelAndView postList(@PathVariable String url,
                                 @PathVariable Long categoryKey,
                                 @RequestParam(required = false, defaultValue = "1") int page,
                                 @RequestParam(required = false, defaultValue = "0") Long tagKey,
                                 @RequestParam(required = false, defaultValue = "") String type,
                                 @RequestParam(required = false, defaultValue = "") String text,
                                 HttpSession session) {
        ModelAndView mv = shelterCommon(url, session);
        mv.setViewName("shelter/post-list");


        //태그리스트
        List<Tag> tag = tagService.findByCategoryKey(categoryKey);
        mv.addObject("tag", tag);

        //글 리스트 가져오기
        // categoryKey, tagKey, page를 넘겨서 가져온다.
        List<Posts> posts = postService.findPostsByParameters(categoryKey, tagKey, type, text, page);
        mv.addObject("posts", posts);

        mv.addObject("categoryKey", categoryKey);

        //페이징 처리
        long countPosts = postService.countPostsByParameters(categoryKey, tagKey, type, text);
        mv.addObject("countPosts", countPosts);

        mv.addObject("tagKey", tagKey);
        mv.addObject("type", type);
        mv.addObject("text", text);
        mv.addObject("page", page);

        return mv;

    }
    @GetMapping("/shelter/{url}/addon/{addonUrl}")
    public ModelAndView addon(@PathVariable String url,@PathVariable String addonUrl, HttpSession session) {
        ModelAndView mv = shelterCommon(url, session);
        mv.setViewName("addon/"+addonUrl);

        //추가기능
        return mv;

    }

    @GetMapping("/shelter/{url}/{categoryKey}/{postKey}")
    public ModelAndView write(@PathVariable String url,@PathVariable Long categoryKey,@PathVariable Long postKey, HttpSession session) {
        ModelAndView mv = shelterCommon(url, session);
        mv.setViewName("shelter/post-content");

        mv.addObject("postKey", postKey);

        //글 내용
        Posts posts = postService.getPosts(postKey);
        mv.addObject("posts", posts);

        //작성자 상세 정보
        Users users = userService.getUsers(posts.getUserKey());
        mv.addObject("users", users);

        //작성자 벳지 리스트
        List<UserBadges> userBadges = userBadgeService.getUserBadges(posts.getUserKey());
        mv.addObject("userBadges", userBadges);

        // 댓글 리스트
        List<Comments> comments = commentService.getComments(postKey);
        mv.addObject("comments", comments);

        if (session.getAttribute("userKey") != null) {
            //로그인 유저 게시글 좋아요 여부
            Boolean isPostLike = postLikeService.existsByPostKeyAndUserKey(postKey, (Long) session.getAttribute("userKey"));
            mv.addObject("isPostLike", isPostLike);

            // 로그인 유저 댓글 좋아요 여부
            List<Boolean> isCommentLikeList = commentLikeService.existsByCommentKeyAndUserKeyList(comments, (Long) session.getAttribute("userKey"));
            mv.addObject("isCommentLikeList", isCommentLikeList);
        }





        return mv;
    }

    @GetMapping("/shelter/{url}/{categoryKey}/write")
    public ModelAndView write(@PathVariable String url,@PathVariable Long categoryKey, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        if (session.getAttribute("userKey") == null) {
            mv.setViewName("redirect:/login");
            return mv;
        }

        mv = shelterCommon(url, session);
        mv.setViewName("shelter/post-write");
        //태그리스트
        List<Tag> tag = tagService.findByCategoryKey(categoryKey);
        mv.addObject("tag", tag);

        mv.addObject("categoryKey", categoryKey);


        return mv;

    }


    private ModelAndView shelterCommon(String url, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        // 쉘터 타이틀 추가
        Shelter shelter = shelterService.findByUrl(url);
        mv.addObject("shelter", shelter);

        // 쉘터의 카테고리 리스트 추가
        List<Category> category = categoryService.findByShelterKey(shelter.getShelterKey());
        mv.addObject("category", category);

        // 쉘터의 addon 리스트 추가
        List<Addon> addon = addonService.findByShelterKey(shelter.getShelterKey());
        mv.addObject("addon", addon);

        if (session.getAttribute("userKey") != null) {
            Users users = userService.getUsers((Long) session.getAttribute("userKey"));
            Boolean isShelterLike = shelterLikeService.existsByShelterKeyAndUserKey(shelter.getShelterKey(), users.getUserKey());
            mv.addObject("users", users);
            mv.addObject("isShelterLike", isShelterLike);
        }

        return mv;
    }

}
