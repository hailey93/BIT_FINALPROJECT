package com.bit.house.controller.kakao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bit.house.domain.kakao.KakaoPayApprovalVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Controller
public class KakaoPayController {
    
	@Autowired(required = false)
    KakaoPayApprovalVO kakaoInfo;
	
	/*@Autowired
	HouseUser houseUser;

	@Autowired
	UserDAO userDAO;*/
	
    @Setter(onMethod_ = @Autowired)
    private KakaoPay kakaopay;
    
    
    @GetMapping("/kakaoPay")
    public void kakaoPayGet(Model model) {
    	log.info("KakaoPayGet 호출............................................");
    	log.info("PayGet에서 호출되는지 ? : ");
    	/*model.addAttribute("housePayment", userDAO.getPayment());
		model.addAttribute("houseProduct", userDAO.getProduct());
		log.info("model payment : "+ userDAO.getPayment());*/
    }
    /*@ModelAttribute("houseProduct") HouseProduct houseProduct,@ModelAttribute("housePayment") HousePayment housePayment*/
    // 서버에서 값을 보내주면 @ModelAttribute 써서 받아와서 씀
    // form ~post 
    // <div> 안에 <a href> button false js에서 선택한게 있으면 submit 아니면 되돌아가기
    @PostMapping("/kakaoPay")
    public String kakaoPay(/*@ModelAttribute("housePayment") HousePayment housePayment, @ModelAttribute("houseProduct") HouseProduct houseProduct , HttpServletRequest request, HouseUser houseUser*/) {
        log.info("kakaoPay post 호출............................................");
        /*houseUser = (HouseUser) request.getSession().getAttribute("houseUser");
        log.info("@ModelAttribute 실행 되는지 ////////////////////" );
        log.info("houseProduct  :: " + houseProduct);
        log.info("housePayment  :: " + housePayment);*/
        
        /*log.info("userId :: " + houseUser.getUserId());*/
        //houseUser = (HouseUser) userDAO.getHouseUser(houseUser);
        return "redirect:" + kakaopay.kakaoPayReady(/*houseUser, housePayment, houseProduct*/);
 
    }
    /*, HouseProduct houseProduct, List<HouseUser> houseUser*/
    @GetMapping("/kakaoPaySuccess")
    public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model,HttpSession session) {
        log.info("kakaoPaySuccess get 호출............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);
         //HouseUser houseUser = (HouseUser) session.getAttribute("houseUser");
       // System.out.println(houseUser);
        //houseUser = userDAO.getHouseUser(houseUser);
        //System.out.println(houseUser);
        kakaoInfo = kakaopay.kakaoPayInfo(pg_token, /*((HouseUser) session.getAttribute("houseUser")).getUserId()*/"hihi");
        //이렇게 하는 이유
        log.info("kakaoInfo : " + kakaoInfo);
        System.out.println("//////////");
        System.out.println("kakaoInfo : " + kakaoInfo);
        //System.out.println("houseUser : " + houseUser);
        //houseUser = userDAO.getUser();
        model.addAttribute("kakaoInfo", kakaoInfo);
       // model.addAttribute("house",houseUser);
        session.getAttribute(pg_token);
        // 추가 해야할 것  insert ~info
        // update ~~info
        
        // 원래 있던거 model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token, pg_token));
        
        //수정 return "redirect:/myPage";
        return "th/member/payment/kakaoPaySuccess";
    }
    
}