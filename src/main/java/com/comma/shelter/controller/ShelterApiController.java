package com.comma.shelter.controller;

import com.comma.shelter.service.ShelterLikeService;
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

    @PostMapping("/post-save")
    public HashMap<String, Object> postSave(@RequestBody Map<String, String> request, HttpSession session) {
        HashMap<String, Object> response = new HashMap<>();
        Long shelterKey = Long.valueOf(request.get("shelterKey"));
        Long userKey = (Long) session.getAttribute("userKey");

        if (userKey == null) {
            response.put("status", false);
            response.put("message", "로그인이 필요합니다.");
            return response;
        }

        //유저키, 카테고리키, 태그키, 제목, 내용 전달

        try {
            response.put("status", true);
            response.put("message", "게시글이 등록되었습니다.");
        } catch (Exception e) {
            response.put("status", false);
            response.put("message", e.getMessage());
        }

        return response;
    }

}
