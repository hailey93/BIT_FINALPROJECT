package com.bit.house.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatAdminController {
    @GetMapping("/statAdmin")
    public String statAdmin(){
        return "th/statAdmin/statAdmin";
    }
}
