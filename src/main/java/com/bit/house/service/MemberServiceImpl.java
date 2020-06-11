package com.bit.house.service;

import com.bit.house.domain.MemberVO;
import com.bit.house.mapper.MemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberMapper memberMapper;


    @Override
    public void insertMember(MemberVO memberVO) {

        memberMapper.insertMember(memberVO);
    }

    @Override
    public void insertSocialToMember(MemberVO memberVO) {

        memberMapper.insertSocialToMember(memberVO);
    }
}
