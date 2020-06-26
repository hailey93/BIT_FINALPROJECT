package com.bit.house.controller.kakao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bit.house.domain.*;
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
    public String kakaoPay(HttpSession session, String[] productNo,
                           int[] orderQty, String[] colorName,String productName,int[] totalPrice) { // 리스트로?
        log.info("kakaoPay post 호출............................................");
        List<BasketVO> basketMember = (List<BasketVO>) session.getAttribute("basketMember");
        log.info("orderlist? : " + basketMember + "totalPrice : ?" + totalPrice);
        for(int i=0; i < productNo.length; i++){
            System.out.println(i+"번쨰 : " +productNo[i]);
        }

        for(int i=0; i < colorName.length; i++){
            System.out.println(i+"번째 : "+colorName[i]);
        }
        int totalP = 0;
        for(int i=0; i < orderQty.length; i++){
            System.out.println(i+"번째 : "+orderQty[i]);
        }
        for(int i=0; i < totalPrice.length; i++){
            System.out.println(i+"번째 : "+totalPrice[i]);
            totalP += totalPrice[i];
        }

        System.out.println("VO :: "+ productName);
        //배열이라
        session.setAttribute("productNo", productNo);
        session.setAttribute("colorName", colorName);
        session.setAttribute("orderQty", orderQty);
        session.setAttribute("orderListVO", orderListVO);

        System.out.println("수량 입력"+session.getAttribute("orderQty"));
        return "redirect:" + kakaopay.kakaoPayReady(basketMember,productName,orderQty,totalP);
 
    }
    List<OrderListVO> orderListVO = new ArrayList<OrderListVO>();


    /*, HouseProduct houseProduct, List<HouseUser> houseUser*/
    @GetMapping("/kakaoPaySuccess")
    public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model,HttpSession session) {
        log.info("kakaoPaySuccess get 호출............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);
         //HouseUser houseUser = (HouseUser) session.getAttribute("houseUser");
       // System.out.println(houseUser);
        //houseUser = userDAO.getHouseUser(houseUser);
        //System.out.println(houseUser);
        MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
        String memberId = memberVO.getMemberId();
        kakaoInfo = kakaopay.kakaoPayInfo(pg_token, memberId);

        //이렇게 하는 이유
        log.info("kakaoInfo : " + kakaoInfo);
        System.out.println("//////////");
        System.out.println("kakaoInfo : " + kakaoInfo);
        model.addAttribute("kakaoInfo", kakaoInfo);
        session.getAttribute(pg_token);

        // 원래 있던거 model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token, pg_token));
        
        //수정 return "redirect:/myPage";
        return "th/member/payment/kakaoPaySuccess";
    }
    
}