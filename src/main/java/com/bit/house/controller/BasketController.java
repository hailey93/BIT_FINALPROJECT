package com.bit.house.controller;

import com.bit.house.domain.AllMemberVO;
import com.bit.house.domain.BasketVO;
import com.bit.house.domain.MemberVO;
import com.bit.house.domain.ProductVO;
import com.bit.house.mapper.AdminMapper;
import com.bit.house.mapper.BasketMapper;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.JsonSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.*;

@Controller
public class BasketController {
    private HttpServletRequest request;


    @Autowired
    AdminMapper adminMapper;

    @Autowired
    BasketMapper basketMapper;

    @GetMapping("/basket")
    public String cart(String MemberId, Model model, AllMemberVO allMemberVO, BasketVO basketVO) {
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

    @RequestMapping(value = "/basketSession", method = RequestMethod.POST)
    public @ResponseBody
    void basket(HttpSession session, ProductVO productVO, BasketVO basketVO, String value, String[] hoho, Model model) { // 상품 detail에서 장바구니 저장 누르면 ajax로 session arr에 있는 장바구니 리스트 받아옴 ->
        System.out.println("ajax!");
        for (String a : hoho) {
            System.out.println("호호 출력 ArrayList");
            System.out.println(a);
        }
        // 비회원 장바구니 시작
        //String[] ajax = request.getParameterValues("hoho");
        String[] han;
        String[] hoho2 = new String[100];
        String[] split;
        List<String> hoho3 = new ArrayList<>();
        int count = 0;
        int k = 0;
        int arrNum = 0;
        System.out.println("호호 길이 :: " + hoho.length);


        // System.out.println("split : " + split);
        for (String string2 : hoho) {
            split = hoho[arrNum].split("[\"\\[\\]]");
            for (String string : split) {
                System.out.print("k : " + k);
                System.out.print(" " + string);
                System.out.println("");
                //System.out.println("in Null? : " +string.isEmpty());
                if (string.isEmpty() == false) {
                    System.out.println("hoho" + k + " 저장합니다 : " + string);
                    hoho2[count] = string;
                    hoho3.add(string);
                    System.out.println("저장된 값 ? :  " + hoho2[k]);
                    count++;
                    System.out.println(count);
                }
                k++;
            }
            arrNum++;
        }
        System.out.println("hoho2 : ");
        //hoho2는 배열 hoho에 기본 길이가 100으로 돼있어서 hoho2에 다시 저장
        for (int i = 0; i < count; i++) {
            System.out.println(hoho2[i]);

        }
        System.out.println(Arrays.toString(hoho2));
        List<String> list2 = Arrays.asList(hoho2);
        System.out.println("리스트 전체 출력 : " + hoho3.toString());
        System.out.println("hoho3 출력");
        //hoho3는 리스트
        for (String item : hoho3) {
            System.out.print(item);
        }
        System.out.println("setAttribute");
        session.setAttribute("hoho3", hoho3);
        String hohoSession = "";
        List<String> hohoSession2 = new ArrayList<>();
        System.out.println("getAttribute");
        hohoSession2 = (List<String>) session.getAttribute("hoho3");
        System.out.println("호호세션 : " + hohoSession2);
        //List로 쓰자
        System.out.println("Mapper 실행 ");

        List<BasketVO> basketVOList = basketMapper.getNonMemberBasketList(hohoSession2); // 아무튼 상품정보 가져옴
        System.out.println(basketVOList.get(0));

        // 여기까지 비회원 장바구니

        // 회원 장바구니 시작
        // String userId = (String) session.getAttribute("userId");
        // List<BasketVO> basketVOListMember = (List<BasketVO>) basketMapper.getBasket(userId);

        // model.addAttribute("memberBasket", basketVOListMember);


        //List<BasketVO> basketVOList = basketMapper.getArray(hoho2);
        //System.out.println(basketVOList);

        //basketVO =  basketMapper.getBasket();
        //System.out.println("basketVO : :: : : "+basketVO);

        /*System.out.println("Split Test ! ");
        String hana = "hey mr, [aomo] to";
        han = hana.split("[\\,\\[\\]]");
        System.out.println("han : " +han);
        for (String string : han) {

            System.out.print(string);
        }*/
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
        /*for(int i=0; i<hoho.length; i++) {
            System.out.print("split 전 : "+hoho[i]);


            System.out.print("split 후 : " + hoho[i] + " ");
            System.out.println("");
        }*/
        //List<ProductVO> productVOList = adminMapper.getProduct();
        // model.addAttribute("list", productVOList);
    }


    @GetMapping("/goBasket")
    public String gobasket(HttpSession session, ProductVO productVO, BasketVO basketVO, Model model) {
        List<String> hohoSession2 = new ArrayList<>();
        System.out.println("getAttribute");
        hohoSession2 = (List<String>) session.getAttribute("hoho3");
        System.out.println("호호세션 : " + hohoSession2);
        //List로 쓰자
        System.out.println("Mapper 실행 ");

        List<BasketVO> basketVOList = basketMapper.getNonMemberBasketList(hohoSession2);
        System.out.println("리스트 : " + basketVOList.get(0).getProductNo());
        model.addAttribute("basketList", basketVOList);
        // 여기까지 비회원 장바구니 

        // 회원 장바구니 시작  Mapper 만들고 실행해야 오류 안남
        //String userId = (String) session.getAttribute("userId");
        //List<BasketVO> basketVOListMember = (List<BasketVO>) basketMapper.getBasket(userId);

        //model.addAttribute("memberBasket", basketVOListMember);

        return "th/member/basket/basket";
    }

    @GetMapping("/btest")
    public String btest() {
        return "th/member/basket/btest";
    }

    @GetMapping("/loginPost")
    public String loginPost(HttpServletRequest request, Model model, HttpSession session, MemberVO houseUser) {
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
