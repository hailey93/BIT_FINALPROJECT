package com.bit.house.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasketController {
    @GetMapping("/basket")
    public String cart(){
        return "th/member/basket/basket";
    }

    //productDetail
    @GetMapping("/product")
    public String product(){
        return "ljhproduct";
    }

}
