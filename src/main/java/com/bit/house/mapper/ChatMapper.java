package com.bit.house.mapper;

import com.bit.house.domain.ChatVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatMapper {
    void insertMsg(String chatRoomId, String memberId, String msg, String time);
    List<ChatVO> selectChatMsg();
    List<ChatVO> selectChatMsgById(String memberId);
}
