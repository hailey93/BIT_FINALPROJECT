package com.bit.house.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;
@ToString
@Setter
@Getter
public class ChatRoomVO implements Serializable {
    //redis에 저장되는 객체들은 serialize가 가능해야한다.
    private static final long serialVersionUID=6494678977089006639L;
    private String chatId;
    private String memberId;
    private String adminId;

    public static ChatRoomVO create(String memberId){
        ChatRoomVO chatRoomVO=new ChatRoomVO();
        chatRoomVO.chatId= UUID.randomUUID().toString();
        chatRoomVO.memberId=memberId;
        return chatRoomVO;
    }
}
