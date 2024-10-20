package com.comma.user.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.PublicKey;

@Controller
public class UserController {

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/signup-terms")
    public String signupTerms() {
        return "user/signup-terms";
    }

    @GetMapping("/signup-verify")
    public String signupVerify() {
        return "user/signup-verify";
    }

    @GetMapping("/signup-credentials")
    public String signupCredentials() {
        return "user/signup-credentials";
    }

    @GetMapping("/signup-complete")
    public String signupComplete() {
        return "user/login";
    }

    @GetMapping("/addon-request")
    public String addonRequest() {
        return "user/addon-request";
    }

    @GetMapping("/shelter-request")
    public String shelterRequest() {
        return "user/shelter-request";
    }

    @GetMapping("/find-user-info")
    public String findUserInfo() {
        return "user/find-user-info";
    }

    @GetMapping("/info")
    public String info() {
        return "user/info";
    }

}
