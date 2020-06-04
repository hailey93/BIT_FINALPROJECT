package com.bit.house.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("/sample/*") 
@Controller
public class SampleController {


  @GetMapping("/all")
  public void doAll() {

    log.info("do all can access everybody");
  }

  @GetMapping("/member")
  public void doMember(Authentication auth) {
    log.info(String.valueOf(auth));
    log.info("logined member");
  }

  @GetMapping("/admin")
  public void doAdmin(Authentication auth) {
    log.info(String.valueOf(auth));
    log.info("admin only");
  }
}
