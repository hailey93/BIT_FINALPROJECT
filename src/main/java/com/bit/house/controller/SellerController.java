package com.bit.house.controller;

import com.bit.house.domain.SellerVO;
import com.bit.house.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class SellerController {

    @Autowired
    SellerService sellerService;

    @GetMapping("/signupSeller")
    public String signupSeller(){


        return "th/login/signupSeller";

    }

    @PostMapping("/signupSellerToSU")
    public String signupSellerToSU(SellerVO sellerVO){

        sellerService.insertSellerToUser(sellerVO);
        sellerService.insertSeller(sellerVO);


        return "th/login/signupSeller";
    }

    @GetMapping("/testSeller")
    @ResponseBody
    public SellerVO testSeller(@AuthenticationPrincipal Principal principal){

        String id = principal.getName();
        sellerService.searchSeller(id);

        return sellerService.searchSeller(id);
    }

    @GetMapping("/seller/sellerInfo")
    public String sellerInfo(@AuthenticationPrincipal Principal principal, Model model){

        String id = principal.getName();
        SellerVO sellerInfo = sellerService.searchSellerInfo(id);

        model.addAttribute("sellerInfo", sellerInfo);

        return "th/seller/sellerInfo";
    }


}
