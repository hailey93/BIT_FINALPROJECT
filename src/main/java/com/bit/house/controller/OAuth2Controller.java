package com.bit.house.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class OAuth2Controller {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/customLogin")
    public String customLogin() {

        return "customLogin";
    }

    @GetMapping("/customLogout")
    public String customLogout() {
        return "customLogout";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(Authentication auth) {

        log.info(String.valueOf(auth));
        return "loginSuccess";
    }

    @GetMapping("/loginFailure")
    public String loginFailure() {
        return "loginFailure";
    }
}
