package com.comma.user.controller;

import com.comma.user.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class ApiController {

    @Autowired
    private Service userService;

    @PostMapping("/check-duplicate-userId")
    public HashMap<String, Object> checkDuplicateUserId(@RequestBody Map<String, String> request) {
        HashMap<String, Object> response = new HashMap<>();
        boolean isDuplicate = userService.isUserIdDuplicate(request.get("userId"));

        if (isDuplicate) {
            response.put("status", false);
            response.put("message", "사용 중인 아이디 입니다.");
        }else {
            response.put("status", true);
            response.put("message", "사용 가능한 아이디 입니다.");
        }

        return response;
    }

    @PostMapping("/check-duplicate-nickname")
    public HashMap<String, Object> checkDuplicateNickname(@RequestBody Map<String, String> request) {
        HashMap<String, Object> response = new HashMap<>();
        boolean isDuplicate = userService.isNicknameDuplicate(request.get("nickname"));

        if (isDuplicate) {
            response.put("status", false);
            response.put("message", "사용 중인 닉네임 입니다.");
        }else {
            response.put("status", true);
            response.put("message", "사용 가능한 닉네임 입니다.");
        }

        return response;
    }

    @PostMapping("/signup")
    public HashMap<String, Object> signup(@RequestBody Map<String, String> request) {
        HashMap<String, Object> response = new HashMap<>();

        try {
            userService.signup(request);

            response.put("status", true);
            response.put("message", "회원가입이 완료되었습니다.");
        } catch (Exception e) {
            response.put("status", false);
            response.put("message", e.getMessage());
        }

        return response;
    }


}