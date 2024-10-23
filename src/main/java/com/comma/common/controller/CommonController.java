package com.comma.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/shop")
    public String shop() {
        return "shop/shop";
    }

}
