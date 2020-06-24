package com.bit.house.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@ToString
@Setter
@Getter
@NoArgsConstructor
public class ChatVO {

    public enum MessageType {
        ENTER, LEAVE, TALK
    }

    private MessageType type;
    private String chatId;
    private String sender;
    private String msg;
}
