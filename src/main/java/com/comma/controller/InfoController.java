package com.comma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InfoController {


    @GetMapping("/introduce")
    public String introduce() {
        return "info/introduce";
    }

    @GetMapping("/ad-guide")
    public String adGuide() {
        return "info/ad-guide";
    }

    @GetMapping("/terms")
    public String terms() {
        return "info/terms";

    }@GetMapping("/privacy")
    public String privacy() {
        return "info/privacy";
    }

    @GetMapping("/youth")
    public String youth() {
        return "info/youth";
    }

}
