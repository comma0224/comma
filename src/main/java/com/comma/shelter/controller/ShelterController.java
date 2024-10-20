package com.comma.shelter.controller;



import com.comma.shelter.domain.*;
import com.comma.shelter.service.*;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.comma.user.domain.User;
import com.comma.user.service.UserService;

import java.util.List;

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


    @GetMapping("/shelter/{url}")
    public ModelAndView shelter(@PathVariable String url, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("shelter/shelter");

        //쉘터 타이틀 추가
        Shelter shelter = shelterService.findByUrl(url);
        mv.addObject("shelter", shelter);

        // 쉘터의 카테고리 리스트 추가
        List<Category> category = categoryService.findByShelterKey(shelter.getShelterKey());
        mv.addObject("category", category);

        // 쉘터의 addon 리스트 추가
        List<Addon> addon = addonService.findByShelterKey(shelter.getShelterKey());
        mv.addObject("addon", addon);

        if (session.getAttribute("userKey") != null) {
            User user = userService.getUser((Long) session.getAttribute("userKey"));
            Boolean isShelterLike = shelterLikeService.existsByShelterKeyAndUserKey(shelter.getShelterKey(), user.getUserKey());
            mv.addObject("user", user);
            mv.addObject("isShelterLike", isShelterLike);
        }
        
        //쉘터 메인 기능 리스트

        return mv;
    }

    @GetMapping("/shelter/{url}/{categoryKey}")
    public ModelAndView category(@PathVariable String url,@PathVariable Long categoryKey, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("shelter/post-list");

        //쉘터 타이틀 추가
        Shelter shelter = shelterService.findByUrl(url);
        mv.addObject("shelter", shelter);

        // 쉘터의 카테고리 리스트 추가
        List<Category> category = categoryService.findByShelterKey(shelter.getShelterKey());
        mv.addObject("category", category);

        // 쉘터의 addon 리스트 추가
        List<Addon> addon = addonService.findByShelterKey(shelter.getShelterKey());
        mv.addObject("addon", addon);

        if (session.getAttribute("userKey") != null) {
            User user = userService.getUser((Long) session.getAttribute("userKey"));
            Boolean isShelterLike = shelterLikeService.existsByShelterKeyAndUserKey(shelter.getShelterKey(), user.getUserKey());
            mv.addObject("user", user);
            mv.addObject("isShelterLike", isShelterLike);
        }

        //태그리스트
        List<Tag> tag = tagService.findByCategoryKey(categoryKey);

        //글 리스트

        return mv;

    }

    @GetMapping("/shelter/{url}/write")
    public ModelAndView category(@PathVariable String url, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("shelter/post-write");

        //쉘터 타이틀 추가
        Shelter shelter = shelterService.findByUrl(url);
        mv.addObject("shelter", shelter);

        // 쉘터의 카테고리 리스트 추가
        List<Category> category = categoryService.findByShelterKey(shelter.getShelterKey());
        mv.addObject("category", category);

        // 쉘터의 addon 리스트 추가
        List<Addon> addon = addonService.findByShelterKey(shelter.getShelterKey());
        mv.addObject("addon", addon);

        if (session.getAttribute("userKey") != null) {
            User user = userService.getUser((Long) session.getAttribute("userKey"));
            Boolean isShelterLike = shelterLikeService.existsByShelterKeyAndUserKey(shelter.getShelterKey(), user.getUserKey());
            mv.addObject("user", user);
            mv.addObject("isShelterLike", isShelterLike);
        }

        //태그리스트

        //글 리스트

        return mv;

    }
    @GetMapping("/shelter/{url}/addon/{addonUrl}")
    public ModelAndView category(@PathVariable String url,@PathVariable String addonUrl, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("addon/"+addonUrl);

        //쉘터 타이틀 추가
        Shelter shelter = shelterService.findByUrl(url);
        mv.addObject("shelter", shelter);

        // 쉘터의 카테고리 리스트 추가
        List<Category> category = categoryService.findByShelterKey(shelter.getShelterKey());
        mv.addObject("category", category);

        // 쉘터의 addon 리스트 추가
        List<Addon> addon = addonService.findByShelterKey(shelter.getShelterKey());
        mv.addObject("addon", addon);

        if (session.getAttribute("userKey") != null) {
            User user = userService.getUser((Long) session.getAttribute("userKey"));
            Boolean isShelterLike = shelterLikeService.existsByShelterKeyAndUserKey(shelter.getShelterKey(), user.getUserKey());
            mv.addObject("user", user);
            mv.addObject("isShelterLike", isShelterLike);
        }

        //추가기능
        

        return mv;

    }

}
