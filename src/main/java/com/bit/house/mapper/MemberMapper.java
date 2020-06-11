package com.bit.house.mapper;

import com.bit.house.domain.MemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    public void insertMember(MemberVO memberVO);
    public void insertSocialToMember(MemberVO memberVO);
}