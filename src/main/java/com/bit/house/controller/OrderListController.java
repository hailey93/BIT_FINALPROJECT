package com.bit.house.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderListController {

    @GetMapping("/orderList")
    public String orderList(){
        return "th/main/orderList";
    }
}
