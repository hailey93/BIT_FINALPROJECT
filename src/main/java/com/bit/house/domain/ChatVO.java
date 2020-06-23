package com.bit.house.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
@ToString
@Setter
@Getter
@NoArgsConstructor
public class ChatVO {

    public enum MessageType {
        ENTER, QUIT, TALK
    }

    private MessageType type;
    private String chatId;
    private String sender;
    private String msg;
    private String sessionId;
    private long count;
    private String memberId;
    private String adminId;
    private Timestamp time;
}
