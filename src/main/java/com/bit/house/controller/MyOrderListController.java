package com.bit.house.controller;

import com.bit.house.domain.MemberVO;
import com.bit.house.domain.OrderListVO;
import com.bit.house.domain.ReviewVO;
import com.bit.house.mapper.MyOrderListMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
public class MyOrderListController {

    @Autowired
    private MyOrderListMapper myOrderListMapper;


    public static String uploadPath = System.getProperty("user.dir") + "/src/main/webapp/image/reviewImg";

    @GetMapping("/order_list")
    public String myOrderList(HttpSession session, Model model) {
        MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
        List<OrderListVO> orderListVOS = myOrderListMapper.getMyOrderListById(memberVO.getMemberId());
        List<ReviewVO> reviewList = myOrderListMapper.showUserReview(memberVO.getMemberId());
        model.addAttribute("orderListVOS", orderListVOS);
        model.addAttribute("reviewList", reviewList);
        return "th/member/mypage/shopping/myOrderList";
    }

    @PostMapping("/returnReason")
    public String addReturnReason(OrderListVO orderListVO) {
        myOrderListMapper.addReturnReason(orderListVO);
        return "redirect:/order_list";
    }

    @PostMapping("/cancelReason")
    public String addCancelReason(OrderListVO orderListVO) {
        myOrderListMapper.addCancelReason(orderListVO);
        return "redirect:/order_list";
    }

    @PostMapping("/addReview")
    public String addReview(HttpSession session, ReviewVO reviewVO, OrderListVO orderListVO, @RequestParam("file") MultipartFile file, @RequestParam("file2") MultipartFile file2, @RequestParam("file3") MultipartFile file3) throws IOException {
        MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
        reviewVO.setMemberId(memberVO.getMemberId());
        orderListVO.setMemberId(memberVO.getMemberId());
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));
            reviewVO.setReviewImg1(resultFilename);
        }

        if (file2 != null && !file2.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file2.getOriginalFilename();

            file2.transferTo(new File(uploadPath + "/" + resultFilename));
            reviewVO.setReviewImg2(resultFilename);
        }

        if (file3 != null && !file3.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file3.getOriginalFilename();

            file3.transferTo(new File(uploadPath + "/" + resultFilename));
            reviewVO.setReviewImg3(resultFilename);
        }
        myOrderListMapper.addReview(reviewVO);
        myOrderListMapper.addConfirmOrderType(orderListVO);
        return "redirect:/order_list";
    }

    @PostMapping("/delete")
    private String delete(@RequestParam("orderNo") String[] orderNo){
        for(String order : orderNo) {
            myOrderListMapper.deleteReviewById(order);
        }
        return "redirect:/order_list";
    }






}
