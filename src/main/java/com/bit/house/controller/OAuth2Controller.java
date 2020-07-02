package com.bit.house.controller;

import com.bit.house.domain.AllMemberVO;
import com.bit.house.domain.MemberVO;
import com.bit.house.security.SocialUser;
import com.bit.house.service.AllMemberService;
import com.bit.house.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Slf4j
@Controller
public class OAuth2Controller {

    @Autowired
    AllMemberService allMemberService;

    @Autowired
    MemberService memberService;

    @GetMapping("/customLogin")
    public String customLogin(){

        return "th/login/customLogin";
    }

    @PostMapping("/customLogout")
    public String customLogout() {
        return "redirect:/storeMain";
    }


    @GetMapping("/loginSuccess")
    public String loginSuccess(@SocialUser MemberVO memberVO, HttpSession session, @AuthenticationPrincipal Principal principal) {

        memberVO = memberService.searchMember(principal.getName());
        session.setAttribute("memberVO", memberVO);
        log.info(String.valueOf(memberVO));


        return "redirect:/storeMain";

    }

    @GetMapping("/signup")
    public String signup() {

        return "th/login/signup";
    }

    @PostMapping("/signupMember")
    public String signupMember(AllMemberVO allMemberVO, MemberVO memberVO) {

        allMemberService.insertUser(allMemberVO);
        memberService.insertMember(memberVO);
        return "redirect:/customLogin";
    }
}
