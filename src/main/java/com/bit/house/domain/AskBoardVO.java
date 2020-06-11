package com.bit.house.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
public class AskBoardVO {
    private int askBoardno;
    private String memberId;
    private String askTitle;
    private String askContent;
    private Date askDate;
    private int askGroupNo;
    private int askIndent;
    private int askStep;

    private String askImg1;
    private String askImg2;
    private String askImg3;
    private String askImg4;
    private String askImg5;

}
