package com.bit.house.controller;

import com.bit.house.domain.MemberVO;
import com.bit.house.domain.OrderListVO;
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
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Log4j2
public class StatAdminController {

    @Autowired
    AdminMapper adminMapper;

    @GetMapping("/statAdmin")
    public String statAdmin() {
        return "th/admin/statAdmin/statAdmin";
    }

    @GetMapping("/graph")
    public String graph(Model model, MemberVO user) {
        //판매량, 품목

        List<ProductVO> houseProductList = adminMapper.getProduct();
        //List<ProductVO> salesVol = adminMapper.getSalesVolume();
        //log.info("salesVol = "+salesVol);
        List<OrderListVO> spendingPattern = adminMapper.getSpendingPattern();
        List<String> yearList = adminMapper.getYear();
        List<OrderListVO> totalPrice = adminMapper.getTotalPrice();
        log.info("totalPrice : "+ totalPrice.toString());
        log.info("productOptionVOList : " +spendingPattern.toString());
        log.info("안녕하세요 yearList입니다" + yearList);
        ObjectMapper mapper = new ObjectMapper();
        String jsonText;

        try {
            jsonText = mapper.writeValueAsString(spendingPattern);
            model.addAttribute("jsonText", jsonText);
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
        //System.out.println("houseProductList : ? " + productOptionVOList);
        return "th/admin/statAdmin/productSalesVolume";
    }

    @GetMapping("/mem")
    public String memManageMent() {
        return "th/admin/statAdmin/memberManagement";
    }


    @RequestMapping(value = "/yearGraphAjax", method = RequestMethod.POST)
    public @ResponseBody Object yearAjaxGraph(String product, String year) {
        //List<OrderListVO> spendingPattern = adminMapper.getMonthData(year, product);

        //ObjectMapper mapper = new ObjectMapper();
        //String jsonText;
        //jsonText = mapper.writeValueAsString(house통계 );
        // return jsonText  Map으로 보낼지 어떻게 보낼지
        ArrayList<String> graph2ArrList = new ArrayList<String>();
        System.out.println("ajax ===> productNo : "+ product + " year ====> " + year);
        //Service.getSelectedYear(year);
        return graph2ArrList;
    }

    @RequestMapping(value = "/monthGraphAjax", method = RequestMethod.POST)
    public @ResponseBody Object monthAjaxGraph( String month, String product, String year) {
        //List<OrderListVO> spendingPattern = adminMapper.getDayData(year,month, product);

        ArrayList<String> graph2ArrList = new ArrayList<String>();
        System.out.println("ajax ===> productNo : "+ product + " year ====> " + year + " month : " + month);
        //Service.getSelectedYear(year);

        String test = "이히히";
        return year;
    }

    @RequestMapping(value = "/sellerGraphAjax", method = RequestMethod.POST)
    public @ResponseBody Object sellerGraphAjax(String sellerName){

        log.info("sellerAjax 호출" + sellerName);
        //회사 이름으로 찾기
        //adminMapper.getSellerStat(sellerName);

        //전체 판매량 순으로 정렬
        //adminMapper.getSpendingPattern();
        return  "";
    }

    @GetMapping("/spendingPattern")
    public String spendingPattern(){
        // 가장 많이 구매한 회원 목록 (당일)
        SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        Date time = new Date();
        String time1 = format1.format(time);
        System.out.println(time1);

        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());

        Date currentTime = Calendar.getInstance().getTime();
        String year = yearFormat.format(currentTime);
        String month = monthFormat.format(currentTime);
        String day = dayFormat.format(currentTime);
        System.out.println(year + "/" + month + "/" + day );
        return "th/admin/statAdmin/spendingPattern";
    }
}
