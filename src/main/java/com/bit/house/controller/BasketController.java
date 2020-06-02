package com.bit.house.controller;

import com.bit.house.domain.ProductVO;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.JsonSerializer;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
public class BasketController {
    private HttpServletRequest request;

    @GetMapping("/basket")
    public String cart() {
        return "th/member/basket/basket";
    }

    //productDetail
    @GetMapping("/productDetails")
    public String product() {
        return "th/main/productDetails";
    }

    @RequestMapping(value = "/basketSession")
    @ResponseBody
    public void basket(HttpServletRequest request, String value, String[] hoho) {
        System.out.println("ajax!");
        //String[] ajax = request.getParameterValues("hoho");
        System.out.println(hoho);
        System.out.println(value);
        for(int i=0; i<hoho.length; i++) System.out.println("addFind:::"+hoho[i]);
/*
        String[] ajaxMsg = request.getParameterValues("arrValue");
        int size = ajaxMsg.length;
        for(int i=0; i<size; i++) {
            System.out.println("받은 MSG : "+ajaxMsg[i]);
        }*/

        /*for(String data : list){

            System.out.println("data = " + data);

        }*/
        System.out.println("왱ㅁ나옴ㅁㄴㅇㅁㄴ니ㅏ운미ㅏㅇ미ㅏㄴ아ㅣㄴㅁ웁주이ㅏㅂㅈㅇ");
        //System.out.println(list);
        //List<ProductVO> analist = new ArrayList<ProductVO>();
        //System.out.println(analist);

        /*;
        List<Map<String,Object>> resultMap = new ArrayList<Map<String,Object>>();
        // resultMap = JSONArray.fromObject(JsonSerializer.toJSON(paramData));
        try {
            JSONArray ja = (JSONArray) JSONValue.parse(Objects.requireNonNull(ServletRequestUtils.getStringParameter(request, "arrValue")));
            System.out.println(ja);
        } catch (ServletRequestBindingException e) {
            e.printStackTrace();
        }
*/
    }
}
