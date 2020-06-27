package com.bit.house.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatMapper {
    void insertMsg(String chatRoomId, String memberId, String msg, String time);
}
