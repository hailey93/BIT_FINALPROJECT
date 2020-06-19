package com.bit.house.controller;

import com.bit.house.domain.OrderListVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PaymentController {


    @GetMapping("/goPayment")
    public String memberPayment(){
        /*
        if(회원){
        BasketMapper.getMemberBasket(String userId);
        model~

        }else{
        List<String> hohoSession2 = new ArrayList<>();
        System.out.println("getAttribute");
        hohoSession2 = (List<String>) session.getAttribute("hoho3");
        System.out.println("호호세션 : "+hohoSession2);
        basketMapper.getNonMemberBasketList(hohoSession2);
        List<BasketVO> basketVOList = basketMapper.getNonMemberBasketList(hohoSession2); // 아무튼 상품정보 가져옴
        model~
        }
        return~



 */

        return "th/member/payment/payment";
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
