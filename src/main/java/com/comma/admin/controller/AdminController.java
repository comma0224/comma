package com.comma.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String admin() {
        return "admin/admin";
    }

    @GetMapping("/admin/badge-management")
    public String badgeManagement() {
        return "admin/badge-management";
    }
    @GetMapping("/admin/shelter-management")
    public String shelterManagement() {
        return "admin/shelter-management";
    }
    @GetMapping("/admin/user-management")
    public String userManagement() {
        return "admin/user-management";
    }
    @GetMapping("/admin/report-management")
    public String reportManagement() {
        return "admin/report-management";
    }
    @GetMapping("/admin/admin-management")
    public String adminManagement() {
        return "admin/admin-management";
    }
}
