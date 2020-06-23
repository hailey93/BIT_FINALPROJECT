package com.bit.house.controller;

import com.bit.house.domain.OrderListProVO;
import com.bit.house.domain.OrderStatusVO;
import com.bit.house.service.OrderListProService;
import com.bit.house.service.OrderStatusService;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
public class OrderListController {

    @Autowired
    OrderListProService orderListProService;

    @Autowired
    OrderStatusService orderStatusService;

    @GetMapping("/orderList")
    public String orderList() {
        return "th/main/orderList";
    }



    @GetMapping("/orderListTest")
    public String orderListTest(@AuthenticationPrincipal Principal principal, Model model) {

        String id = principal.getName();
        List<OrderListProVO> orderListProVO = orderListProService.searchOrderList(id);

        model.addAttribute("orderListPro", orderListProVO);

        ObjectMapper mapper = new ObjectMapper();
        String jsonOrderStatus;
        List<OrderStatusVO> orderStatus = orderStatusService.searchOrderStatus();


        try {
            jsonOrderStatus = mapper.writeValueAsString(orderStatus);
            model.addAttribute("jsonOrderStatus", jsonOrderStatus);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "th/seller/orderList";
    }

    @PostMapping("/orderListChange")
    public String orderListChange(String orderStatus, String orderNo){

        log.info(orderNo);
        log.info(orderStatus);


        return "redirect:/orderListTest";
    }
}
