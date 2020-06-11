package com.bit.house.security;

import com.bit.house.domain.AllMemberVO;
import com.bit.house.domain.MemberVO;
import com.bit.house.domain.SocialType;
import com.bit.house.service.AllMemberService;
import com.bit.house.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static com.bit.house.domain.SocialType.*;

@Component
@Slf4j
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    private AllMemberService allMemberService;
    private MemberService memberService;

    public UserArgumentResolver(AllMemberService allMemberService, MemberService memberService) {
        this.allMemberService = allMemberService;
        this.memberService = memberService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(SocialUser.class) != null && parameter.getParameterType().equals(AllMemberVO.class);

    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
        AllMemberVO allMemberVO = (AllMemberVO) session.getAttribute("allMemberVO");
        return getUser(allMemberVO, session);
    }

    private AllMemberVO getUser(AllMemberVO allMemberVO, HttpSession session) {
        if (allMemberVO == null) {
            try {
                OAuth2AuthenticationToken authentication = (OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
                Map<String, Object> map = authentication.getPrincipal().getAttributes();
                AllMemberVO convertUser = convertUser(authentication.getAuthorizedClientRegistrationId(), map);
                MemberVO convertMember = convertMember(authentication.getAuthorizedClientRegistrationId(), map);

                if (allMemberService.searchSocial(convertUser.getUserid()) == null) {

                    allMemberService.insertSocialToUser(convertUser);
                    memberService.insertSocialToMember(convertMember);
                }

                session.setAttribute("allMemberVO", allMemberVO);


            } catch (ClassCastException e) {
                return allMemberVO;
            }
        }
        return allMemberVO;
    }

    private AllMemberVO convertUser(String authority, Map<String, Object> map) {
        if (GOOGLE.isEquals(authority)) return getGoogleUser(map);
        if (KAKAO.isEquals(authority)) return getKaKaoUser(map);
        if (NAVER.isEquals(authority)) return getNaverUser(map);

        return null;
    }

    private MemberVO convertMember(String authority, Map<String, Object> map) {
        if (GOOGLE.isEquals(authority)) return getGoogleMember(map);
        if (KAKAO.isEquals(authority)) return getKaKaoMember(map);
        if (NAVER.isEquals(authority)) return getNaverMember(map);

        return null;
    }


    private AllMemberVO getGoogleUser(Map<String, Object> map) {

        AllMemberVO allMemberVO = new AllMemberVO();
        allMemberVO.setUserid(String.valueOf(map.get("email")));
        allMemberVO.setSocialType(SocialType.GOOGLE);

        return allMemberVO;
    }


    private AllMemberVO getKaKaoUser(Map<String, Object> map) {
        Map<String, String> propertyMap = (HashMap<String, String>) map.get("properties");
        Map<String, String> accountMap = (HashMap<String, String>) map.get("kakao_account");

        AllMemberVO allMemberVO = new AllMemberVO();
        allMemberVO.setUserid(String.valueOf(accountMap.get("email")));
        allMemberVO.setSocialType(SocialType.KAKAO);

        return allMemberVO;
    }

    private AllMemberVO getNaverUser(Map<String, Object> map) {

        AllMemberVO allMemberVO = new AllMemberVO();
        allMemberVO.setUserid(String.valueOf(map.get("email")));
        allMemberVO.setSocialType(SocialType.NAVER);

        return allMemberVO;
    }

    private MemberVO getGoogleMember(Map<String, Object> map) {
        MemberVO memberVO = new MemberVO();
        memberVO.setUserid(String.valueOf(map.get("email")));
        memberVO.setUserName(String.valueOf(map.get("name")));

        return memberVO;
    }

    private MemberVO getKaKaoMember(Map<String, Object> map) {
        Map<String, String> propertyMap = (HashMap<String, String>) map.get("properties");
        Map<String, String> accountMap = (HashMap<String, String>) map.get("kakao_account");

        MemberVO memberVO = new MemberVO();
        memberVO.setUserid(String.valueOf(accountMap.get("email")));
        memberVO.setUserName(String.valueOf(propertyMap.get("nickname")));

        return memberVO;
    }

    private MemberVO getNaverMember(Map<String, Object> map) {
        MemberVO memberVO = new MemberVO();
        memberVO.setUserid(String.valueOf(map.get("email")));
        memberVO.setUserName(String.valueOf(map.get("name")));

        return memberVO;
    }
}

