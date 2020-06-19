package com.bit.house.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
public class ChatVO {
    private MessageType type;
    private int chatId;
    private String memberId;
    private String msg;
    private Timestamp time;
}
