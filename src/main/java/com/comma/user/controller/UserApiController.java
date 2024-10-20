package com.comma.user.controller;

import com.comma.user.service.UserService;
import com.comma.user.domain.User;
import static com.comma.user.service.Validator.*;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;



@RestController
@RequestMapping("/api/user")
public class UserApiController {

    @Autowired
    private UserService userService;

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
    public HashMap<String, Object> signup(@RequestBody Map<String, String> request, HttpSession session) {
        HashMap<String, Object> response = new HashMap<>();

        try {
            isValidateSignupUserId(request.get("userId"), userService);
            isValidateSignupPassword(request.get("password"));
            isValidateSignupNickname(request.get("nickname"), userService);

        } catch (Exception e) {
            response.put("status", false);
            response.put("message", e.getMessage());

            return response;
        }

        try {
            User user = userService.signup(request);

            session.setAttribute("userKey", user.getUserKey());
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("role", user.getRole());

            response.put("status", true);
            response.put("message", "회원가입이 완료되었습니다.");

        } catch (Exception e) {
            response.put("status", false);
            response.put("message", e.getMessage());
        }

        return response;
    }

    @PostMapping("/login")
    public HashMap<String, Object> login(@RequestBody Map<String, String> request, HttpSession session) {
        HashMap<String, Object> response = new HashMap<>();

        try {
            isValidateLoginUserId(request.get("userId"), userService);
            isValidateLoginPassword(request.get("password"));

        } catch (Exception e) {
            response.put("status", false);
            response.put("message", e.getMessage());

            return response;
        }

        try {
            User user = userService.login(request);

            session.setAttribute("userKey", user.getUserKey());
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("role", user.getRole());

            response.put("status", true);
            response.put("message", "로그인이 완료되었습니다.");

        } catch (Exception e) {
            response.put("status", false);
            response.put("message", e.getMessage());
        }

        return response;
    }

    @PostMapping("/logout")
    public HashMap<String, Object> logout(HttpSession session) {
        HashMap<String, Object> response = new HashMap<>();

        session.invalidate();

        response.put("status", true);
        response.put("message", "로그아웃이 완료되었습니다.");

        return response;
    }


}