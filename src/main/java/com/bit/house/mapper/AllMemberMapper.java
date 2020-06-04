package com.bit.house.mapper;

import com.bit.house.domain.AllMemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AllMemberMapper {

    AllMemberVO read(String userid);

}
