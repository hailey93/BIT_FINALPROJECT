package com.bit.house.controller;

import com.bit.house.domain.MemberVO;
import com.bit.house.domain.ProductVO;
import com.bit.house.mapper.MainMapper;
import com.bit.house.recommenderProcess.RecommendProcess;
import com.bit.house.service.RecommenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;

@Slf4j
@Controller
public class MainController {

    @Autowired(required = false)
    MainMapper mainMapper;

    @Autowired(required = false)
    RecommenderService recommenderService;

    @GetMapping(value = {"/storeMain", "/"})
    public String main(Model model, HttpSession session) {

        log.info(String.valueOf(session.getAttribute("memberVO")));

        model.addAttribute("mainList", mainMapper.selectMainList());

        MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
        if(memberVO!=null){
            String memberId = memberVO.getMemberId();
            //추천상품
            String clickProduct=recommenderService.selectClickProduct(memberId);

            if(clickProduct==null){//회원이 최근 조회한 아이템이 없으면 추천아이템 보여주지 않기
                model.addAttribute("showRecommend", null);
            } else{
                //회원이 최근 조회한 아이템이 있으면 추천시스템에 돌리기
                RecommendProcess recommend=new RecommendProcess();
                Collection<String> recommendNos=recommend.recommender(clickProduct);
                //추천받은 아이템 디테일 가져오기
                List<ProductVO> recommendItems=recommenderService.selectProductList(recommendNos);

                model.addAttribute("recommendList", recommendItems);
                model.addAttribute("showRecommend", clickProduct);
            }

        }

        return "th/main/storeMain";
    }

    @GetMapping("/storeBest")
    public String storeBest(Model model) {
        //model.addAttribute("bestList", mainService.selectBestList());
       model.addAttribute("bestList", mainMapper.selectbestLists());
        return "th/main/storeBest";
    }

    @PostMapping("/infiniteScrollDown")
    public @ResponseBody List<ProductVO> scrollDown(@RequestBody ProductVO vo){
        log.info("무한스크롤");
        String productNoStart=vo.getProductNo();
        return mainMapper.selectMainList();
    }

}
