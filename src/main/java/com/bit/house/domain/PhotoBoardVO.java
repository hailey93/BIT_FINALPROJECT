package com.bit.house.domain;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
public class PhotoBoardVO {
    private int photoBoardNo;
    private String memberId;
    private String photoTitle;
    private String photoContent;
    private int likeCount;
    private int scrapCount;
    private Date photoDate;
    private String photothumb;
    protected String photoImg1;
    private String photoImg2;
    private String photoImg3;
    private String photoImg4;
    private String photoImg5;
    private String houseCode;
    private String placeCode;
    private String areaCode;
    private String styleCode;
}
