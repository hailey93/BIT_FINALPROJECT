package com.bit.house.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TestController {

    @GetMapping("/jsp")
    public String jsp(){
        return "/jspTest";
    }

    @GetMapping("/th")
    public String th(){
        return "th/thTest";
    }





}
