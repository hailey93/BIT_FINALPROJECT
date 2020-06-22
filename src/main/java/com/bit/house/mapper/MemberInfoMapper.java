package com.bit.house.mapper;

import com.bit.house.domain.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MemberInfoMapper {

    @Select("select * from member where memberId=#{memberId}")
    MemberVO getInfoMemberById(String memberId);

    @Update("update member set memberName=?, memberEmail=?, memberTel=?, memberAddr=? where memberId=#{memberId}")
    MemberVO updateInfoMemberById(String memberId);

}
