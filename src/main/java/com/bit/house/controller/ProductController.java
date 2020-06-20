package com.bit.house.controller;

import com.bit.house.domain.*;
import com.bit.house.mapper.ProductMapper;
import com.bit.house.mapper.RecommenderMapper;
import com.bit.house.service.RecommenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
@Slf4j
@Controller
@RequestMapping("productions")
public class ProductController {

    @Autowired
    private ProductMapper productMapper;

    @Autowired(required =false)
    RecommenderService recommenderService;

    @Autowired(required =false)
    RecommenderMapper recommenderMapper;

    @GetMapping("/searchlist")
    public String findProduct(@RequestParam(required = false, defaultValue = "") String index, Model model) {
        List<ProductVO> productList = productMapper.selectAllProduct();

        if (index != null && !index.isEmpty()) {
            productList = productMapper.selectProduct(index);
        } else {
            productList = productMapper.selectAllProduct();
        }

        model.addAttribute("productList", productList);
        model.addAttribute("index", index);
        return "th/main/searchList";
    }


    @GetMapping("/category")
    public String findByCategory(@RequestParam(value = "categoryCode", required = false, defaultValue = "") String category, Model model) {
        List<ProductVO> categoryList = productMapper.selectProductByCategory(category);
        model.addAttribute("productList", categoryList);
        return "th/main/categoryList";
    }

    @GetMapping("/productDetails")
    public String getProductVODetails(@ModelAttribute("basketVO") BasketVO basketVO , String productNo, Model model, HttpSession session){
        ProductVO product = productMapper.getProductVOByProductNo(productNo);
        List<String> colorCodeVOList = productMapper.getProductVOByProductColorCode(productNo);
        product.setColorCodeVOList(colorCodeVOList);


        SellerVO seller = productMapper.getProductVOBySellerName(productNo);

        model.addAttribute("product", product);
        model.addAttribute("seller",seller);

        MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
        if(memberVO!=null){
            String memberId = memberVO.getMemberId();
            //상품페이지 들어갈때 clickproduct테이블에 insert or update 조건1. 회원이 같은 상품을 조회한 이력이 있으면 날짜는 오늘날짜로, clickCount +1 update 없으면 insert
            recommenderService.checkClickHistory(memberId, productNo);
        }
        //product테이블 clickTotalcount컬럼+1
        recommenderService.updateClickTotalCount(productNo);
        return "th/main/productDetails";
    }



}
