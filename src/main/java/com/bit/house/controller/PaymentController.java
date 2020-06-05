package com.bit.house.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaymentController {


    @GetMapping("/goPayment")
    public String gopayment(){
        return "th/member/payment/payment";
    }

    @GetMapping("/paymentSuccess")
    public String paymentSuccess(){
        return "th/member/payment/paymentSuccess";
    }
}
