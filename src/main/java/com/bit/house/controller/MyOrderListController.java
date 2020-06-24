package com.bit.house.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class MyOrderListController {

    @GetMapping("order_list")
    public String myOrderList(){
        return "th/member/mypage/shopping/myOrderList";
    }

}
