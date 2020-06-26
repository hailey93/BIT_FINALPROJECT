package com.bit.house.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
    private String time;

    public static ChatRoomVO create(String memberId){
        SimpleDateFormat currentTime = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        String time=currentTime.format(System.currentTimeMillis());

        ChatRoomVO chatRoomVO=new ChatRoomVO();
        chatRoomVO.chatId= UUID.randomUUID().toString();
        chatRoomVO.memberId=memberId;
        chatRoomVO.time=time;
        return chatRoomVO;
    }
}
