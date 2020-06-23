package com.bit.house.mapper;

import com.bit.house.domain.MemberVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MemberInfoMapper {

    @Select("select * from member where memberId=#{memberId}")
    MemberVO getInfoMemberById(String memberId);

    @Update("update member set memberName=#{memberName}, memberEmail=#{memberEmail}, memberTel=#{memberTel}, memberAddr=#{memberAddr}, nickName=#{nickName} where memberId=#{memberId}")
    void updateInfoMemberById(MemberVO memberVO);

//    @Insert("INSERT INTO allmember (userid, userpw, authCode, enabled)\n" +
//            "    VALUES (#{memberId}, #{memberpw}, '1', '0')")
//    void updatePw(MemberVO memberVO);

}
