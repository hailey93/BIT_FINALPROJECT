package com.bit.house.controller;

import com.bit.house.domain.BasketVO;
import com.bit.house.domain.MemberVO;
import com.bit.house.mapper.BasketMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class PaymentController {

    @Autowired
    BasketMapper basketMapper;

    @GetMapping("/goPayment")
    public String memberPayment(HttpSession session, Model model){
        String memberId = "";

        MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
        if(memberVO != null){

            memberId = memberVO.getMemberId();
            log.info(memberId);
            List<BasketVO> basketMember = basketMapper.getMemberBasketList(memberId);
            System.out.println("qty :: " + basketMember);
            model.addAttribute("memberBasketList", basketMember);
            return "th/member/payment/memberPayment";
        }else{
            List<String> hohoSession2 = new ArrayList<>();
            hohoSession2 = (List<String>) session.getAttribute("hoho3");

            List<BasketVO> basketVOList = basketMapper.getNonMemberBasketList(hohoSession2);
            model.addAttribute("nonMemberBasketList", basketVOList);
            return "th/member/payment/nonMemberPayment";
        }
    }




    @GetMapping("/nonMemberPayment")
    public String nonMemberPayment(){

        /*

        List<String> hohoSession2 = new ArrayList<>();
        System.out.println("getAttribute");
        hohoSession2 = (List<String>) session.getAttribute("hoho3");
        System.out.println("호호세션 : "+hohoSession2);
        basketMapper.getNonMemberBasketList(hohoSession2);
        List<BasketVO> basketVOList = basketMapper.getNonMemberBasketList(hohoSession2); // 아무튼 상품정보 가져옴
        model~
 */

        return "th/member/payment/nonMemberPayment";
    }

    @GetMapping("/paymentSuccess")
    public String paymentSuccess(){
        return "th/member/payment/paymentSuccess";
    }
}
