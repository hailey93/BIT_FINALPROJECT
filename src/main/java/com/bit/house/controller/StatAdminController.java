package com.bit.house.controller;

import com.bit.house.domain.MemberVO;
import com.bit.house.domain.ProductOptionVO;
import com.bit.house.domain.ProductVO;
import com.bit.house.mapper.AdminMapper;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@Controller
@Log4j2
public class StatAdminController {

    @Autowired
    AdminMapper adminMapper;

    @GetMapping("/statAdmin")
    public String statAdmin(){
        return "th/admin/statAdmin/statAdmin";
    }

    @GetMapping("/graph")
    public String graph(Model model, MemberVO user) {
        //판매량, 품목

        List<ProductVO> houseProductList = adminMapper.getProduct();
        List<ProductOptionVO> productOptionVOList = adminMapper.getProductOption();
        List<String> yearList = adminMapper.getYear();
        log.info("안녕하세요 yearList입니다" + yearList);
        ObjectMapper mapper = new ObjectMapper();
        String jsonText;

        try {
            jsonText = mapper.writeValueAsString(productOptionVOList);
            model.addAttribute("jsonText", jsonText );
            model.addAttribute("yearList", yearList);
            log.info(jsonText);
            System.out.println("jsonText는 : ? : " + jsonText);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("houseProductList : ? " + productOptionVOList);
        return "th/admin/statAdmin/productSalesVolume";
    }

    @GetMapping("/mem")
    public String memManageMent(){
        return "th/admin/statAdmin/memberManagement";
    }

}
