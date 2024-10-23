package com.comma.admin.controller;

import com.comma.admin.service.BadgeService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminApiController {

    @Autowired
    private BadgeService badgeService;

    @Value("${file.badgeDir}")
    private String badgeDir;

    @PostMapping("/badge-save")
    public HashMap<String, Object> uploadBadge(@RequestParam("name") String name,
                              @RequestParam("price") Integer price,
                              @RequestParam("tier") Integer tier,
                              @RequestParam("file") MultipartFile file) throws IOException {

        HashMap<String, Object> response = new HashMap<>();
        //String badgeDir = "C:/Users/comma/IdeaProjects/comma/src/main/resources/static/image/badge/";
        String uploadDir = badgeDir + tier + "/";
        String fileName = name + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }


        String filePath = uploadDir + fileName;
        file.transferTo(new File(filePath));

        try {
            badgeService.saveBadge(name, price, tier, fileName);
            response.put("status", true);
            response.put("message", "뱃지가 등록되었습니다.");
        }catch (Exception e) {
            response.put("status", false);
            response.put("message", e.getMessage());
        }


        return response;
    }
}
