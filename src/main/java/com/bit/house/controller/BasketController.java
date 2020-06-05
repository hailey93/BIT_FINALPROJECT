package com.bit.house.controller;

import com.bit.house.domain.AllMemberVO;
import com.bit.house.domain.BasketVO;
import com.bit.house.domain.MemberVO;
import com.bit.house.domain.ProductVO;
import com.bit.house.mapper.AdminMapper;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.JsonSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
public class BasketController {
    private HttpServletRequest request;


    @Autowired
    AdminMapper adminMapper;

    @GetMapping("/basket")
    public String cart(String MemberId, Model model,AllMemberVO allMemberVO, BasketVO basketVO) {
        System.out.println("goBasket!");
        //UserId 있으면 (html에서 세션 검증)
        // basketVO = MemberService.getMemberBasket(MemberId);
        List<AllMemberVO> allMemberVOList = adminMapper.getMember();
        //System.out.println(allMemberVO.getUserid());
        System.out.println(allMemberVOList);
        model.addAttribute("allMemberVOList", allMemberVOList);
        return "th/member/basket/basket";
    }

    //productDetail
    @GetMapping("/productDetails")
    public String product() {
        return "th/main/productDetailsLJH";
    }

    @GetMapping("/basketPop")
    public String basketPop(HttpServletRequest request) {
        /*String sessionId = (String) request.getSession().getAttribute("memberId");
        if(sessionId != null){
            adminMapper.getBasket();
            model~

            }
            값이 없으면 그냥 보낸 후 페이지에서 ajax처리


        */

        return "th/member/basket/basketPop";
    }

    @RequestMapping(value = "/basketSession")
    public @ResponseBody void basket(HttpServletRequest request, String value, String[] hoho) { // 상품 detail에서 장바구니 저장 누르면 ajax로 session arr에 있는 장바구니 리스트 받아옴 ->
        System.out.println("ajax!");
        //String[] ajax = request.getParameterValues("hoho");
        System.out.println(hoho);
        System.out.println(value);
        for(int i=0; i<hoho.length; i++) {
            System.out.println("addFind:::"+hoho[i]);
        }
        System.out.println("빌드테스트1234567890");

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

    @GetMapping("/btest")
    public String btest(){
        return "th/member/basket/btest";
    }

    @GetMapping("/loginPost")
    public String loginPost(HttpServletRequest request, Model model, HttpSession session, MemberVO houseUser){
        /*System.out.println("//////////////");
        houseUser = adminMapper.getUser();
        System.out.println(houseUser);
        session.setAttribute("houseUser", houseUser);
        System.out.println("hUser ID  :: "+ houseUser.getMemberId());
        model.addAttribute("house", houseUser);
        //model.addAttribute("payment", adminMapper.getPayment());
        model.addAttribute("product", adminMapper.getProduct());
        //log.info("model payment : "+ adminMapper.getPayment());*/
        return "th/main/productDetailsLJH";
    }
}
