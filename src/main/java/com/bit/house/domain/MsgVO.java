package com.bit.house.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
public class MsgVO {

    private int msgNo;
    private String memberId;
    private String recieveId;
    private String msgContents;
    private Date msgDate;
}
