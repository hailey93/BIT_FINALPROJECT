package com.bit.house.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/*
채팅 내용 db저장용 vo
 */

@Setter
@Getter
@NoArgsConstructor
public class ChatMsgVO implements Serializable {
    private static final long serialVersionUID=6494678977089006639L;
    private String sender;
    private String msg;
    private String time;

    @Override
    public String toString() {
        return "ChatMsgVO{" +
                "sender='" + sender + '\'' +
                ", msg='" + msg + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
