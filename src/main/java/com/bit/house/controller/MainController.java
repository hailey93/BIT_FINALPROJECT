package com.bit.house.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value={"/storeMain", "/"})
    public String main(){
        return "th/main/storeMain";
    }

    @GetMapping("/storeBest")
    public String storeBest(){
        return "th/main/storeBest";
    }
}
