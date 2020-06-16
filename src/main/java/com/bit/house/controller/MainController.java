package com.bit.house.controller;

import com.bit.house.mapper.MainMapper;
import com.bit.house.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired(required = false)
    MainMapper mainMapper;

    @Autowired(required = false)
    MainService mainService;

    @GetMapping(value = {"/storeMain", "/"})
    public String main(Model model) {
        model.addAttribute("mainList", mainMapper.selectMainList());
        return "th/main/storeMain";
    }

    @GetMapping("/storeBest")
    public String storeBest(Model model) {
        model.addAttribute("bestList", mainService.selectBestList());
        return "th/main/storeBest";
    }

}
