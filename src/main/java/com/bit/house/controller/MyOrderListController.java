package com.bit.house.controller;

import com.bit.house.domain.MemberVO;
import com.bit.house.domain.OrderListVO;
import com.bit.house.mapper.MyOrderListMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
public class MyOrderListController {

    @Autowired
    private MyOrderListMapper myOrderListMapper;

    @GetMapping("/order_list")
    public String myOrderList(HttpSession session, Model model){
        MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
        List<OrderListVO> orderListVOS = myOrderListMapper.getMyOrderListById(memberVO.getMemberId());
        model.addAttribute("orderListVOS", orderListVOS);
        return "th/member/mypage/shopping/myOrderList";
    }

}
